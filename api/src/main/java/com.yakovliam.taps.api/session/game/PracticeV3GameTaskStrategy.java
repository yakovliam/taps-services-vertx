package com.yakovliam.taps.api.session.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yakovliam.taps.api.model.GameConfig;
import com.yakovliam.taps.api.model.GameConfigPlayer;
import com.yakovliam.taps.api.model.GameConfigPlayerRepository;
import com.yakovliam.taps.api.model.GameConfigRepository;
import com.yakovliam.taps.api.model.JsonSerializableObject;
import com.yakovliam.taps.api.model.internal.GamePrediction;
import com.yakovliam.taps.api.model.internal.IntermediateScore;
import com.yakovliam.taps.api.network.NetworkClient;
import com.yakovliam.taps.api.network.TriumphRequestBuilder;
import com.yakovliam.taps.api.network.tournaments.CreateTournamentAsyncGroupV3Request;
import com.yakovliam.taps.api.network.tournaments.CreateTournamentAsyncGroupV3RequestBody;
import com.yakovliam.taps.api.network.tournaments.CreateTournamentAsyncGroupV3ResponseBody;
import com.yakovliam.taps.api.network.tournaments.ReportIntermediateScoreRequest;
import com.yakovliam.taps.api.network.tournaments.ReportIntermediateScoreRequestBody;
import com.yakovliam.taps.api.network.tournaments.ReportScoreRequest;
import com.yakovliam.taps.api.network.tournaments.ReportScoreRequestBody;
import com.yakovliam.taps.api.player.StandardPMMLGamePlayer;
import com.yakovliam.taps.api.predictor.GamePredictionChecker;
import okhttp3.Request;
import org.slf4j.Logger;

public class PracticeV3GameTaskStrategy implements GameTaskStrategy {

  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(PracticeV3GameTaskStrategy.class);

  private static final ObjectMapper DEBUG_MAPPER = new ObjectMapper();

  @Override
  public GameTaskResult execute(GameTaskContext context) {
    GameConfigRepository gameConfigRepository = GameConfigRepository.getInstance();
    GameConfig gameConfig = gameConfigRepository.findByGameAndUid(context.getGame(), "practice-v3");

    GameConfigPlayer player = GameConfigPlayerRepository.getInstance().findByGameConfig(gameConfig);

    StandardPMMLGamePlayer gamePlayer = new StandardPMMLGamePlayer(player);

    // TODO implement an algorithm to determine the best possible final score
    GamePrediction prediction = gamePlayer.play(140);
    boolean predictionIsValid = GamePredictionChecker.isGamePredictionValid(prediction);

    if (!predictionIsValid) {
      throw new IllegalStateException("Prediction is not valid");
    }

    LOGGER.info("Prediction final score: {}", prediction.getFinalScore());
    for (IntermediateScore intermediateScore : prediction.getIntermediateScores()) {
      try {
        LOGGER.info("Intermediate score: {}", DEBUG_MAPPER.writeValueAsString(intermediateScore));
      } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
      }
    }

    // Network logic

    //////////////////////////
    CreateTournamentAsyncGroupV3RequestBody body =
        new CreateTournamentAsyncGroupV3RequestBody(gameConfig.getGame().getUid(),
            gameConfig.getUid());
    CreateTournamentAsyncGroupV3Request request =
        new CreateTournamentAsyncGroupV3Request(gameConfig.getGame().getUid(), body,
            context.getIdentity().getDevice(), context.getIdentity());

    Request sendableRequest = TriumphRequestBuilder.getInstance().build(request);

    CreateTournamentAsyncGroupV3ResponseBody response = NetworkClient.getInstance()
        .send(sendableRequest, CreateTournamentAsyncGroupV3ResponseBody.class).join();

    String tournamentId = response.getTournamentId();

    try {
      LOGGER.info("Response: {}", DEBUG_MAPPER.writeValueAsString(response));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    // send intermediate scores every 30 seconds
    sendIntermediateScoresAndWait(tournamentId, context, gameConfig, prediction);

    // finish the game
    ReportScoreRequest reportScoreRequest =
        new ReportScoreRequest(gameConfig.getGame().getUid(),
            new ReportScoreRequestBody(prediction.getFinalScore(), tournamentId,
                gameConfig.getGame().getUid()), context.getIdentity().getDevice(),
            context.getIdentity());

    sendableRequest = TriumphRequestBuilder.getInstance().build(reportScoreRequest);

    JsonSerializableObject resp =
        NetworkClient.getInstance().send(sendableRequest, JsonSerializableObject.class).join();

    LOGGER.info("Final Sc. Response: {}", resp.writeDynamicValues());
    return new GameTaskResult();
  }

  private void sendIntermediateScoresAndWait(String tournamentId, GameTaskContext context,
                                             GameConfig gameConfig, GamePrediction prediction) {
    IntermediateScore scores[] = prediction.getIntermediateScores();

    // send each score, one by one, every 30 seconds
    // first wait 30 seconds, then send the first score, then wait 30 seconds, then send the second score, etc.
    for (IntermediateScore score : scores) {
      try {
        Thread.sleep(30000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      sendIntermediateScore(tournamentId, context, score, gameConfig);
    }
  }

  private void sendIntermediateScore(String tournamentId, GameTaskContext context,
                                     IntermediateScore score, GameConfig gameConfig) {
    // send the intermediate score
    ReportIntermediateScoreRequest request =
        new ReportIntermediateScoreRequest(gameConfig.getGame().getUid(),
            new ReportIntermediateScoreRequestBody(score.getCreatedAt(), score.getSequenceId(),
                score.getValue(), tournamentId, gameConfig.getGame().getUid()),
            context.getIdentity().getDevice(), context.getIdentity());

    Request sendableRequest = TriumphRequestBuilder.getInstance().build(request);

    JsonSerializableObject resp =
        NetworkClient.getInstance().send(sendableRequest, JsonSerializableObject.class).join();

    LOGGER.info("Int. Sc. Response: {}", resp.writeDynamicValues());
  }
}

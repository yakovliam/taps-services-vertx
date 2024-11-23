package com.yakovliam.taps.api.session.game;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yakovliam.taps.api.model.GameConfig;
import com.yakovliam.taps.api.model.GameConfigPlayer;
import com.yakovliam.taps.api.model.GameConfigPlayerRepository;
import com.yakovliam.taps.api.model.GameConfigRepository;
import com.yakovliam.taps.api.model.internal.GamePrediction;
import com.yakovliam.taps.api.model.internal.IntermediateScore;
import com.yakovliam.taps.api.player.StandardPMMLGamePlayer;
import com.yakovliam.taps.api.predictor.GamePredictionChecker;
import com.yakovliam.taps.api.session.GameSessionAuthStateManager;
import org.slf4j.Logger;

public class PracticeV3GameTaskStrategy implements GameTaskStrategy {

  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(PracticeV3GameTaskStrategy.class);

  private static final ObjectMapper DEBUG_MAPPER = new ObjectMapper();

  @Override
  public GameTaskResult execute(GameTaskContext context) {
    GameConfigRepository gameConfigRepository = GameConfigRepository.getInstance();
    GameConfig gameConfig =
        gameConfigRepository.findByGameAndUid(context.getGame(), "practice-v3");

    GameConfigPlayer player =
        GameConfigPlayerRepository.getInstance().findByGameConfig(gameConfig);

    StandardPMMLGamePlayer gamePlayer = new StandardPMMLGamePlayer(player);

    // TODO implement an algorithm to determine the best possible final score
    GamePrediction prediction = gamePlayer.play(500);
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
//
//    //////////////////////////
//    CreateTournamentAsyncGroupV3RequestBody body =
//        new CreateTournamentAsyncGroupV3RequestBody(gameConfig.getGame().getUid(),
//            gameConfig.getUid());
//    CreateTournamentAsyncGroupV3Request request =
//        new CreateTournamentAsyncGroupV3Request(gameConfig.getGame().getUid(), body,
//            context.getIdentity().getDevice(), context.getIdentity());
//
//    Request sendableRequest = TriumphRequestBuilder.getInstance().build(request);
//
//    JsonSerializableObject response =
//        NetworkClient.getInstance().send(sendableRequest, JsonSerializableObject.class)
//            .join();
//
//    try {
//      LOGGER.info("Response: {}", DEBUG_MAPPER.writeValueAsString(response));
//    } catch (JsonProcessingException e) {
//      throw new RuntimeException(e);
//    }

//    // send intermediate scores every 30 seconds
//    ReportIntermediateScoreRequestBody intermediateScoreRequestBody =
//        new ReportIntermediateScoreRequestBody();
//    ReportIntermediateScoreRequest intermediateScoreRequest =
//        new ReportIntermediateScoreRequest(gameConfig.getGame().getUid(), ,
//            context.getIdentity().getDevice(), context.getIdentity());

    return new GameTaskResult();
  }

//  private void sendIntermediateScoresAndWait(GameTaskContext context, GameConfig gameConfig) {
//
//  }
}

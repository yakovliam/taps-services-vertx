package com.yakovliam.taps.api.player;

import static java.lang.Math.round;

import com.yakovliam.taps.api.model.GameConfigPlayer;
import com.yakovliam.taps.api.model.PredictorModel;
import com.yakovliam.taps.api.model.internal.GamePrediction;
import com.yakovliam.taps.api.model.internal.IntermediateScore;
import com.yakovliam.taps.api.predictor.PMMLModelPredictor;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

public class StandardPMMLGamePlayer extends GamePlayer<GamePrediction, Integer> {

  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(StandardPMMLGamePlayer.class);

  private static final String XSCORE_YSECONDS_MODEL_PATH = "xscore_yseconds.pmml";
  private static final String XSCORE_YSECONDS_OUTPUT_FIELD = "predicted_SECONDS";
  private static final String XSECONDS_YSCORE_MODEL_PATH = "xseconds_yscore.pmml";
  private static final String XSECONDS_YSCORE_OUTPUT_FIELD = "predicted_SCORE";

  private static final int SECONDS_BETWEEN_INTERMEDIATE_SCORES = 30;

  private final PMMLModelPredictor<Integer, Double> xScoreYSecondsModelPredictor;

  private final PMMLModelPredictor<Integer, Double> xSecondsYScoreModelPredictor;

  public StandardPMMLGamePlayer(GameConfigPlayer gameConfigPlayer) {
    PredictorModel model = gameConfigPlayer.getPredictorModel();
    String pathDirectory = model.getPath();
    // load the model
    String xScoreYSecondsModelPath = pathDirectory + "/" + XSCORE_YSECONDS_MODEL_PATH;
    PMMLModelPredictor<Integer, Double> xScoreYSecondsModelPredictor =
        new PMMLModelPredictor<>(xScoreYSecondsModelPath, XSCORE_YSECONDS_OUTPUT_FIELD);

    String xSecondsYScoreModelPath = pathDirectory + "/" + XSECONDS_YSCORE_MODEL_PATH;
    PMMLModelPredictor<Integer, Double> xSecondsYScoreModelPredictor =
        new PMMLModelPredictor<>(xSecondsYScoreModelPath, XSECONDS_YSCORE_OUTPUT_FIELD);

    this.xScoreYSecondsModelPredictor = xScoreYSecondsModelPredictor;
    this.xSecondsYScoreModelPredictor = xSecondsYScoreModelPredictor;
  }

  @Override
  public GamePrediction play(Integer finalScore) {
    long createdAtEpochTimeMs = System.currentTimeMillis();
    // calculate time for score
    double waitTimeSeconds = xScoreYSecondsModelPredictor.predict(finalScore);

    IntermediateScore[] intermediateScores =
        generateIntermediateScores((int) waitTimeSeconds, createdAtEpochTimeMs);

    int calculatedFinalScore =
        calculateFinalScoreFromIntermediateScores(intermediateScores, finalScore);

    long waitTimeMillis = (long) (waitTimeSeconds * 1000);

    return GamePrediction.of(calculatedFinalScore, waitTimeMillis, intermediateScores);
  }

  private IntermediateScore[] generateIntermediateScores(int waitTimeSeconds,
                                                         long createdAtEpochTimeMs) {
    int numIntervals = (waitTimeSeconds / SECONDS_BETWEEN_INTERMEDIATE_SCORES) + 1;
    List<IntermediateScore> intermediateScores = new ArrayList<>();

    long time = createdAtEpochTimeMs;

    intermediateScores.add(new IntermediateScore(time, 0, 0));

    for (int i = 1; i < numIntervals; i++) {
      time += SECONDS_BETWEEN_INTERMEDIATE_SCORES * 1000;
      double t = (double) (time - createdAtEpochTimeMs) / 1000;

      double score0 = xSecondsYScoreModelPredictor.predict((int) t);
      double score1 =
          xSecondsYScoreModelPredictor.predict((int) (t + SECONDS_BETWEEN_INTERMEDIATE_SCORES));

      double diff = multRandomRange(score1 - score0, 0.94, 1.08);

      double score = diff + intermediateScores.get(intermediateScores.size() - 1).getValue();
      int intScore = (int) round(score);

      intermediateScores.add(new IntermediateScore(time, intScore, i));
    }

    return intermediateScores.toArray(new IntermediateScore[0]);
  }

  private int calculateFinalScoreFromIntermediateScores(IntermediateScore[] intermediateScores,
                                                        int targetScore) {
    int lastScore = intermediateScores[intermediateScores.length - 1].getValue();

    double randomMultiplier = Math.random() * (1.08 - 1.0) + 1.0;

    if (lastScore == 0) {
      lastScore = targetScore;
    }

    return (int) (lastScore * randomMultiplier);
  }


  private double multRandomRange(double value, double lower, double upper) {
    // multiply by a random number between 0.94 and 1.08
    return value * (Math.random() * (upper - lower) + lower);
  }
}

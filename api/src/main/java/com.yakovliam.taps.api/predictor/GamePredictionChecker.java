package com.yakovliam.taps.api.predictor;

import com.yakovliam.taps.api.model.internal.GamePrediction;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GamePredictionChecker {

  private static final Logger LOGGER = LoggerFactory.getLogger(GamePredictionChecker.class);

  public static boolean isGamePredictionValid(GamePrediction gamePrediction) {
    if (gamePrediction == null) {
      LOGGER.warn("Game prediction is null");
      return false;
    }

    // check to make sure all game prediction values are positive
    if (gamePrediction.getFinalScore() < 0) {
      LOGGER.warn("Final score is negative");
      return false;
    }

    if (Arrays.stream(gamePrediction.getIntermediateScores())
        .anyMatch(score -> score.getValue() < 0)) {
      LOGGER.warn("Intermediate scores contain negative values");
      return false;
    }

    return true;
  }
}

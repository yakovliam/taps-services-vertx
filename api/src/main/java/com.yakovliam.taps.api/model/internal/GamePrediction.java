package com.yakovliam.taps.api.model.internal;

import java.util.Arrays;

public class GamePrediction {

  /**
   * The final score
   */
  private final int finalScore;

  /**
   * The wait time in milliseconds
   */
  private final double waitTimeMillis;

  /**
   * The intermediate scores
   */
  private final IntermediateScore[] intermediateScores;

  /**
   * Constructor
   *
   * @param finalScore         the final score
   * @param waitTimeMillis     the wait time in milliseconds
   * @param intermediateScores the intermediate scores
   */
  public GamePrediction(int finalScore, double waitTimeMillis,
                        IntermediateScore[] intermediateScores) {
    this.finalScore = finalScore;
    this.waitTimeMillis = waitTimeMillis;
    this.intermediateScores = intermediateScores;
  }

  public int getFinalScore() {
    return finalScore;
  }

  public double getWaitTimeMillis() {
    return waitTimeMillis;
  }

  public IntermediateScore[] getIntermediateScores() {
    return intermediateScores;
  }

  @Override
  public String toString() {
    return "GamePrediction{" + "finalScore=" + finalScore + ", waitTimeMillis=" + waitTimeMillis +
        ", intermediateScores=" + Arrays.toString(intermediateScores) + '}';
  }

  public static GamePrediction unknown() {
    return new GamePrediction(-1, -1, new IntermediateScore[0]);
  }

  public static GamePrediction of(int finalScore, double waitTimeMillis,
                                  IntermediateScore[] intermediateScores) {
    return new GamePrediction(finalScore, waitTimeMillis, intermediateScores);
  }
}

package com.yakovliam.taps.api.orchestrator;

import java.util.List;

public class EarnUSDJobGoal extends JobGoal {

  /**
   * Job goal
   *
   * @param expectedReturn the expected return
   */
  public EarnUSDJobGoal(double expectedReturn) {
    super("Earn USD", expectedReturn, List.of(new EarnUSDCriterion(expectedReturn)));
  }
}

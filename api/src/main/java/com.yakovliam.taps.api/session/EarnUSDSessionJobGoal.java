package com.yakovliam.taps.api.session;

import com.yakovliam.taps.api.model.App;
import java.util.List;

public class EarnUSDSessionJobGoal extends SessionJobGoal {

  /**
   * Job goal
   *
   * @param expectedReturn the expected return
   * @param app            the app
   */
  public EarnUSDSessionJobGoal(double expectedReturn, App app) {
    super("Earn USD", expectedReturn, List.of(new EarnUSDSessionCriterion(expectedReturn)),
        app);
  }
}

package com.yakovliam.taps.api.session;

import com.yakovliam.taps.api.model.App;
import java.util.List;

public class SessionJobGoal {

  /**
   * The name of the goal
   */
  private final String name;

  /**
   * The expected return (in USD) of the goal
   */
  private final double expectedReturn;

  /**
   * The criteria to meet
   */
  private final List<SessionJobCriterion> criteria;

  /**
   * App
   */
  private final App app;

  /**
   * Job goal
   *
   * @param name           the name
   * @param expectedReturn the expected return
   * @param criteria       the criteria
   * @param app            the app
   */
  public SessionJobGoal(String name, double expectedReturn, List<SessionJobCriterion> criteria,
                        App app) {
    this.name = name;
    this.expectedReturn = expectedReturn;
    this.criteria = criteria;
    this.app = app;
  }

  public String getName() {
    return name;
  }

  public double getExpectedReturn() {
    return expectedReturn;
  }

  public List<SessionJobCriterion> getCriteria() {
    return criteria;
  }

  public App getApp() {
    return app;
  }
}

package com.yakovliam.taps.api.orchestrator;

import java.util.List;

public class JobGoal {

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
  private final List<JobCriterion> criteria;

  /**
   * Job goal
   *
   * @param name           the name
   * @param expectedReturn the expected return
   * @param criteria       the criteria
   */
  public JobGoal(String name, double expectedReturn, List<JobCriterion> criteria) {
    this.name = name;
    this.expectedReturn = expectedReturn;
    this.criteria = criteria;
  }

  public String getName() {
    return name;
  }

  public double getExpectedReturn() {
    return expectedReturn;
  }

  public List<JobCriterion> getCriteria() {
    return criteria;
  }
}

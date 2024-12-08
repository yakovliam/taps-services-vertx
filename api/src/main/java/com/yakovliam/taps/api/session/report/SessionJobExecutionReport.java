package com.yakovliam.taps.api.session.report;

import com.yakovliam.taps.api.session.SessionJobCriterion;
import com.yakovliam.taps.api.session.SessionJobGoal;
import java.util.List;

public class SessionJobExecutionReport {

  /**
   * The goal of the job
   */
  private final SessionJobGoal goal;

  /**
   * The actions taken during the job
   */
  private final List<SessionJobAction> jobActions;

  /**
   * The total earnings
   */
  private final double totalEarnings;

  /**
   * The total errors
   */
  private final List<SessionJobError> errors;

  /**
   * Job execution report
   *
   * @param goal          the goal
   * @param jobActions    the job actions
   * @param totalEarnings the total earnings
   * @param errors        the errors
   */
  public SessionJobExecutionReport(SessionJobGoal goal, List<SessionJobAction> jobActions,
                                   double totalEarnings,
                                   List<SessionJobError> errors) {
    this.goal = goal;
    this.jobActions = jobActions;
    this.totalEarnings = totalEarnings;
    this.errors = errors;
  }

  public boolean meetsCriteria(List<SessionJobCriterion> criteria) {
    return criteria.stream().allMatch(criterion -> criterion.isMet(this));
  }

  public SessionJobGoal getGoal() {
    return goal;
  }

  public List<SessionJobAction> getJobActions() {
    return jobActions;
  }

  public double getTotalEarnings() {
    return totalEarnings;
  }

  public List<SessionJobError> getErrors() {
    return errors;
  }
}

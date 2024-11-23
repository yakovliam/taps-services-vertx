package com.yakovliam.taps.api.orchestrator.report;

import com.yakovliam.taps.api.orchestrator.JobCriterion;
import com.yakovliam.taps.api.orchestrator.JobGoal;
import com.yakovliam.taps.api.session.report.SessionJobExecutionReport;
import java.util.List;

public class JobExecutionReport {

  /**
   * The goal of the job
   */
  private final JobGoal goal;

  /**
   * The actions taken during the job
   */
  private final List<JobAction> jobActions;

  /**
   * The total earnings
   */
  private final double totalEarnings;

  /**
   * The total errors
   */
  private final List<JobError> errors;

  /**
   * The session job execution reports
   */
  private final List<SessionJobExecutionReport> sessionJobExecutionReports;

  /**
   * Job execution report
   *
   * @param goal                       the goal
   * @param jobActions                 the job actions
   * @param totalEarnings              the total earnings
   * @param errors                     the errors
   * @param sessionJobExecutionReports the session job execution reports
   */
  public JobExecutionReport(JobGoal goal, List<JobAction> jobActions, double totalEarnings,
                            List<JobError> errors,
                            List<SessionJobExecutionReport> sessionJobExecutionReports) {
    this.goal = goal;
    this.jobActions = jobActions;
    this.totalEarnings = totalEarnings;
    this.errors = errors;
    this.sessionJobExecutionReports = sessionJobExecutionReports;
  }

  public boolean meetsCriteria(List<JobCriterion> criteria) {
    return criteria.stream().allMatch(criterion -> criterion.isMet(this));
  }

  public JobGoal getGoal() {
    return goal;
  }

  public List<JobAction> getJobActions() {
    return jobActions;
  }

  public double getTotalEarnings() {
    return totalEarnings;
  }

  public List<JobError> getErrors() {
    return errors;
  }

  public List<SessionJobExecutionReport> getSessionJobExecutionReports() {
    return sessionJobExecutionReports;
  }
}

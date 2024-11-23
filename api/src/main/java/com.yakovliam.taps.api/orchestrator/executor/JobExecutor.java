package com.yakovliam.taps.api.orchestrator.executor;

import com.yakovliam.taps.api.orchestrator.Job;
import com.yakovliam.taps.api.orchestrator.JobGoal;
import com.yakovliam.taps.api.orchestrator.report.JobAction;
import com.yakovliam.taps.api.orchestrator.report.JobError;
import com.yakovliam.taps.api.orchestrator.report.JobExecutionReport;
import java.util.ArrayList;
import java.util.List;

public abstract class JobExecutor<G extends JobGoal> {

  private final List<JobAction> jobActions;

  private final List<JobError> jobErrors;

  public JobExecutor() {
    this.jobActions = new ArrayList<>();
    this.jobErrors = new ArrayList<>();
  }

  /**
   * Executes the job
   */
  public abstract JobExecutionReport execute(Job<G> job);

  /**
   * Returns the goal type
   *
   * @return goal type
   */
  public abstract Class<G> getGoalType();

  protected void addAction(JobAction action) {
    jobActions.add(action);
  }

  protected void addError(JobError error) {
    jobErrors.add(error);
  }

  protected List<JobAction> getJobActions() {
    return jobActions;
  }

  protected List<JobError> getJobErrors() {
    return jobErrors;
  }
}

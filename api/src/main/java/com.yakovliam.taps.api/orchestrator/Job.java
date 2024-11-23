package com.yakovliam.taps.api.orchestrator;

import com.yakovliam.taps.api.orchestrator.executor.JobExecutor;
import com.yakovliam.taps.api.orchestrator.executor.JobExecutorSelector;
import com.yakovliam.taps.api.orchestrator.report.JobExecutionReport;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;

public class Job<G extends JobGoal> {

  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(Job.class);

  /**
   * The goal of the job
   */
  private final G goal;

  /**
   * Job
   *
   * @param goal the goal
   */
  public Job(G goal) {
    this.goal = goal;
  }

  public G getGoal() {
    return goal;
  }

  /**
   * Executes the job
   *
   * @return a future containing the job execution report
   */
  public CompletableFuture<JobExecutionReport> execute() {
    // dynamically select the executor
    JobExecutor<?> unknownExecutor = JobExecutorSelector.selectExecutor(this);

    // check if the executor is of the correct type
    if (!unknownExecutor.getGoalType().isInstance(this.getGoal())) {
      throw new IllegalStateException("Executor is not of the correct type");
    }

    @SuppressWarnings("unchecked") JobExecutor<G> executor = (JobExecutor<G>) unknownExecutor;

    JobExecutionReport report = executor.execute(this);

    boolean meetsCriteria = report.meetsCriteria(goal.getCriteria());

    if (!meetsCriteria) {
      LOGGER.error("Job did not meet criteria");
    }

    return CompletableFuture.completedFuture(report);
  }
}

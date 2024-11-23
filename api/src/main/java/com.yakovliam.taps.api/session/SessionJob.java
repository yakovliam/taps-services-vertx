package com.yakovliam.taps.api.session;

import com.yakovliam.taps.api.session.executor.SessionJobExecutor;
import com.yakovliam.taps.api.session.executor.SessionJobExecutorSelector;
import com.yakovliam.taps.api.session.report.SessionJobExecutionReport;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;

public class SessionJob<G extends SessionJobGoal> {

  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(SessionJob.class);

  /**
   * The goal of the job
   */
  private final G goal;

  /**
   * Job
   *
   * @param goal the goal
   */
  public SessionJob(G goal) {
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
  public CompletableFuture<SessionJobExecutionReport> execute() {
    // dynamically select the executor
    SessionJobExecutor<?> unknownExecutor = SessionJobExecutorSelector.selectExecutor(this);

    // check if the executor is of the correct type
    if (!unknownExecutor.getGoalType().isInstance(this.getGoal())) {
      throw new IllegalStateException("Executor is not of the correct type");
    }

    @SuppressWarnings("unchecked") SessionJobExecutor<G> executor =
        (SessionJobExecutor<G>) unknownExecutor;

    SessionJobExecutionReport report = executor.execute(this);

    boolean meetsCriteria = report.meetsCriteria(goal.getCriteria());

    if (!meetsCriteria) {
      LOGGER.error("Session job did not meet criteria");
    }

    return CompletableFuture.completedFuture(report);
  }
}


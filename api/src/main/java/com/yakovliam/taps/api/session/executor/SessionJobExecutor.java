package com.yakovliam.taps.api.session.executor;

import com.yakovliam.taps.api.session.SessionJob;
import com.yakovliam.taps.api.session.SessionJobGoal;
import com.yakovliam.taps.api.session.report.SessionJobExecutionReport;

public abstract class SessionJobExecutor<G extends SessionJobGoal> {

  /**
   * Executes the job
   */
  public abstract SessionJobExecutionReport execute(SessionJob<G> job);

  /**
   * Returns the goal type
   *
   * @return goal type
   */
  public abstract Class<G> getGoalType();
}

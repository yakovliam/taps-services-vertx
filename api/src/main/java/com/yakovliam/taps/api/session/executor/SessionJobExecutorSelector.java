package com.yakovliam.taps.api.session.executor;

import com.yakovliam.taps.api.session.EarnUSDSessionJobGoal;
import com.yakovliam.taps.api.session.SessionJob;
import com.yakovliam.taps.api.session.executor.earnusd.EarnUSDSessionJobExecutor;
import java.util.Map;
import org.slf4j.Logger;

public class SessionJobExecutorSelector {

  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(SessionJobExecutorSelector.class);

  private static final Map<Class<?>, SessionJobExecutor<?>> JOB_GOAL_EXECUTORS =
      Map.of(EarnUSDSessionJobGoal.class, new EarnUSDSessionJobExecutor());

  /**
   * Selects an executor for the given job
   *
   * @param job job
   * @return executor
   */
  public static SessionJobExecutor<?> selectExecutor(SessionJob<?> job) {
    // select the executor based on the job's goal
    SessionJobExecutor<?> executor = JOB_GOAL_EXECUTORS.get(job.getGoal().getClass());

    if (executor == null) {
      LOGGER.error("No executor found for job goal: {}", job.getGoal().getClass().getSimpleName());
      throw new IllegalStateException("No executor found for job goal");
    }

    return executor;
  }
}

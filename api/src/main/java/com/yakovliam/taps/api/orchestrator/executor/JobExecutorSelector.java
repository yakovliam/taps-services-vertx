package com.yakovliam.taps.api.orchestrator.executor;

import com.yakovliam.taps.api.orchestrator.EarnUSDJobGoal;
import com.yakovliam.taps.api.orchestrator.Job;
import java.util.Map;
import org.slf4j.Logger;

public class JobExecutorSelector {

  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JobExecutorSelector.class);

  private static final Map<Class<?>, JobExecutor<?>> JOB_GOAL_EXECUTORS =
      Map.of(EarnUSDJobGoal.class, new EarnUSDJobExecutor());

  /**
   * Selects an executor for the given job
   *
   * @param job job
   * @return executor
   */
  public static JobExecutor<?> selectExecutor(Job<?> job) {
    // select the executor based on the job's goal
    JobExecutor<?> executor = JOB_GOAL_EXECUTORS.get(job.getGoal().getClass());

    if (executor == null) {
      LOGGER.error("No executor found for job goal: {}", job.getGoal().getClass().getSimpleName());
      throw new IllegalStateException("No executor found for job goal");
    }

    return executor;
  }
}

package com.yakovliam.taps.api.orchestrator;

import com.yakovliam.taps.api.orchestrator.report.JobExecutionReport;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Job orchestrator
 * <p>
 * Responsible for orchestrating jobs.
 * Jobs are exclusively 'play tasks' where phones/identities are dispatched to
 * play games.
 * A job orchestrator is NEVER responsible for orchestrating anything else but 'play tasks.'
 */
public class JobOrchestrator {

  private static final Logger LOGGER = LoggerFactory.getLogger(JobOrchestrator.class);

  /**
   * Orchestrate the job
   */
  public CompletableFuture<JobExecutionReport> orchestrate(Job<?> job) {
    LOGGER.info("Orchestrating job: {}", job.getGoal().getName());
    return job.execute();
  }
}

package com.yakovliam.taps;

import com.yakovliam.taps.api.orchestrator.EarnUSDJobGoal;
import com.yakovliam.taps.api.orchestrator.Job;
import com.yakovliam.taps.api.orchestrator.JobOrchestrator;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;

public class OrchestratorVerticle extends AbstractVerticle {

  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(OrchestratorVerticle.class);

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    LOGGER.info("🚀 Starting Orchestrator...");
    JobOrchestrator jobOrchestrator = new JobOrchestrator();

    Job<EarnUSDJobGoal> earnUSDJob = new Job<>(new EarnUSDJobGoal(100.00));
    new Thread(() -> {
      jobOrchestrator.orchestrate(earnUSDJob).thenAccept(report -> {
        LOGGER.info("Job completed: {}", report.getGoal().getName());
      });
    }).start();

    startPromise.complete();
  }
}

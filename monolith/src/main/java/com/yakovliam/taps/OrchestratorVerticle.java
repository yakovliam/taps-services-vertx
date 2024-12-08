package com.yakovliam.taps;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;

public class OrchestratorVerticle extends AbstractVerticle {

  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(OrchestratorVerticle.class);

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    LOGGER.info("🚀 Starting Orchestrator...");

    new Thread(() -> {
    }).start();

    startPromise.complete();
  }
}

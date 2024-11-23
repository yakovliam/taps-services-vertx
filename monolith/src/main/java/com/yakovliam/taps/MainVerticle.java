package com.yakovliam.taps;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.core.logging.SLF4JLogDelegateFactory;
import org.slf4j.Logger;

public class MainVerticle extends AbstractVerticle {

  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MainVerticle.class);

  @Override
  public Uni<Void> asyncStart() {
    String logFactory = System.getProperty("org.vertx.logger-delegate-factory-class-name");
    if (logFactory == null) {
      System.setProperty("org.vertx.logger-delegate-factory-class-name",
          SLF4JLogDelegateFactory.class.getName());
    }

    // start liquibase verticle
    // then hibernate verticle
    // then orchestrator verticle

    return vertx.deployVerticle(LiquibaseVerticle.class.getName())
        .onItem().transformToUni(ignored -> vertx.deployVerticle(HibernateVerticle.class.getName()))
        .onItem().transformToUni(ignored -> vertx.deployVerticle(OrchestratorVerticle.class.getName()))
        .onItem().transformToUni(ignored -> {
          LOGGER.info("🚀 All verticles deployed successfully");
          return Uni.createFrom().voidItem();
        });
  }
}

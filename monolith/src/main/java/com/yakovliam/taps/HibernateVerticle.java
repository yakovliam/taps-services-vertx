package com.yakovliam.taps;

import com.yakovliam.taps.api.model.AppRepository;
import com.yakovliam.taps.api.model.DeviceRepository;
import com.yakovliam.taps.api.model.GameConfigPlayerRepository;
import com.yakovliam.taps.api.model.GameConfigRepository;
import com.yakovliam.taps.api.model.GameRepository;
import com.yakovliam.taps.api.model.IdentityRepository;
import com.yakovliam.taps.api.model.JobRepository;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;

public class HibernateVerticle extends AbstractVerticle {

  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(HibernateVerticle.class);

  @Override
  public Uni<Void> asyncStart() {
    LOGGER.info("🚀 Starting Hibernate...");

    // build the session factory
    return vertx.executeBlocking(() -> {
      EntityManagerFactory entityManagerFactory =
          Persistence.createEntityManagerFactory("monolith");

      AppRepository.init(entityManagerFactory);
      DeviceRepository.init(entityManagerFactory);
      GameConfigPlayerRepository.init(entityManagerFactory);
      GameConfigRepository.init(entityManagerFactory);
      GameRepository.init(entityManagerFactory);
      IdentityRepository.init(entityManagerFactory);
      JobRepository.init(entityManagerFactory);

      return null;
    }).replaceWithVoid();
  }
}
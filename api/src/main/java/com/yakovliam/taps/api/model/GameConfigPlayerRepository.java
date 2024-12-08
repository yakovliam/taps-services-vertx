package com.yakovliam.taps.api.model;

import jakarta.persistence.EntityManagerFactory;

public class GameConfigPlayerRepository extends GenericDao<GameConfigPlayer, Long> {

  private static GameConfigPlayerRepository instance;

  public static void init(EntityManagerFactory entityManagerFactory) {
    instance = new GameConfigPlayerRepository(entityManagerFactory);
  }

  public static GameConfigPlayerRepository getInstance() {
    return instance;
  }

  public GameConfigPlayerRepository(EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
  }

  public GameConfigPlayer findByGameConfig(GameConfig gameConfig) {
    return withTransaction(em -> em.createQuery(
        "SELECT gcp FROM GameConfigPlayer gcp WHERE gcp.gameConfig = :gameConfig",
        GameConfigPlayer.class).setParameter("gameConfig", gameConfig).getSingleResult());
  }
}

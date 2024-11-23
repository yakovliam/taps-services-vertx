package com.yakovliam.taps.api.model;

import jakarta.persistence.EntityManagerFactory;

public class GameConfigRepository extends GenericDao<GameConfig, Long> {

  private static GameConfigRepository instance;

  public static void init(EntityManagerFactory entityManagerFactory) {
    instance = new GameConfigRepository(entityManagerFactory);
  }

  public static GameConfigRepository getInstance() {
    return instance;
  }

  public GameConfigRepository(EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
  }

  public GameConfig findByUid(String uid) {
    return withTransaction(
        em -> em.createQuery("SELECT gc FROM GameConfig gc WHERE gc.uid = :uid",
                GameConfig.class)
            .setParameter("uid", uid).getSingleResult());
  }


  public GameConfig findByGameAndUid(Game game, String uid) {
    return withTransaction(
        session -> session.createQuery(
                "SELECT gc FROM GameConfig gc WHERE gc.game = :game AND gc.uid = :uid",
                GameConfig.class)
            .setParameter("game", game)
            .setParameter("uid", uid)
            .getSingleResult());
  }
}

package com.yakovliam.taps.api.model;

import jakarta.persistence.EntityManagerFactory;

public class GameRepository extends GenericDao<Game, Long> {

  private static GameRepository instance;

  public static void init(EntityManagerFactory entityManagerFactory) {
    instance = new GameRepository(entityManagerFactory);
  }

  public static GameRepository getInstance() {
    return instance;
  }

  public GameRepository(EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
  }

  public Game findTopByOrderByIdAsc() {
    return withTransaction(
        em -> em.createQuery("select g from Game g order by g.id asc", Game.class)
            .setMaxResults(1).getSingleResult());
  }

  public Game findByUid(String uid) {
    return withTransaction(
        em -> em.createQuery("select g from Game g where g.uid = ?1", Game.class)
            .setParameter(1, uid).getSingleResult());
  }

  public Game findByAppEqualsAndUidIs(App app, String uid) {
    return withTransaction(
        session -> session.createQuery("select g from Game g where g.app = ?1 and g.uid = ?2",
            Game.class).setParameter(1, app).setParameter(2, uid).getSingleResult());
  }
}

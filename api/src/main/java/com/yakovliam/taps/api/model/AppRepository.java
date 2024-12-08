package com.yakovliam.taps.api.model;

import jakarta.persistence.EntityManagerFactory;

public class AppRepository extends GenericDao<App, Long> {

  private static AppRepository instance;

  public static void init(EntityManagerFactory entityManagerFactory) {
    instance = new AppRepository(entityManagerFactory);
  }

  public static AppRepository getInstance() {
    return instance;
  }

  public AppRepository(EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
  }

  public App findTopByOrderByIdAsc() {
    return withTransaction(
        em -> em.createQuery("FROM App ORDER BY id ASC", App.class)
            .setMaxResults(1).getSingleResult());
  }

  public App findByUid(String uid) {
    return withTransaction(
        sm -> sm.createQuery("FROM App WHERE uid = :uid", App.class)
            .setParameter("uid", uid).getSingleResult());
  }

  public App findByName(String name) {
    return withTransaction(
        em -> em.createQuery("FROM App WHERE name = :name", App.class)
            .setParameter("name", name).getSingleResult());
  }
}

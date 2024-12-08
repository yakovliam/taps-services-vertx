package com.yakovliam.taps.api.model;

import jakarta.persistence.EntityManagerFactory;
import java.util.List;

public class IdentityRepository extends GenericDao<Identity, Long> {

  private static IdentityRepository instance;

  public static void init(EntityManagerFactory entityManagerFactory) {
    instance = new IdentityRepository(entityManagerFactory);
  }

  public static IdentityRepository getInstance() {
    return instance;
  }

  public IdentityRepository(EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
  }

  public Identity getFirstByAppIs(App app) {
    return withTransaction(
        em -> em.createQuery("FROM Identity i WHERE i.app = ?1", Identity.class)
            .setParameter(1, app).getSingleResult());
  }

  // find all that match app=? and where activeJob is null
  public List<Identity> findAllByAppAndActiveJobIsNull(App app) {
    return withTransaction(
        em -> em.createQuery("FROM Identity i WHERE i.app = ?1 AND i.activeJob IS NULL",
            Identity.class).setParameter(1, app).getResultList());
  }
}

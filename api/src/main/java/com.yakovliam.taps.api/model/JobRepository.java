package com.yakovliam.taps.api.model;

import jakarta.persistence.EntityManagerFactory;

public class JobRepository extends GenericDao<Job, Long> {

  private static JobRepository instance;

  public static void init(EntityManagerFactory entityManagerFactory) {
    instance = new JobRepository(entityManagerFactory);
  }

  public static JobRepository getInstance() {
    return instance;
  }

  public JobRepository(EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
  }
}

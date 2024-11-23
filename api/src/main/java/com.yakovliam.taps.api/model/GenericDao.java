package com.yakovliam.taps.api.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Function;

public class GenericDao<T, ID extends Serializable> {

  protected EntityManagerFactory entityManagerFactory;

  public GenericDao(EntityManagerFactory entityManagerFactory) {
    this.entityManagerFactory = entityManagerFactory;
  }

  public T save(T entity) {
    return withTransaction(em -> em.merge(entity));
  }

  public void update(T entity) {
    withTransaction(em -> em.merge(entity));
  }

  public void delete(T entity) {
    withTransactionVoid(em -> em.remove(entity));
  }


  public T find(Class<T> entityClass, ID id) {
    return withTransaction(em -> em.find(entityClass, id));
  }

  protected <T> T withTransaction(Function<EntityManager, T> action) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    T result = action.apply(entityManager);

    entityManager.getTransaction().commit();
    entityManager.close();

    return result;
  }

  protected void withTransactionVoid(Consumer<EntityManager> action) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    action.accept(entityManager);

    entityManager.getTransaction().commit();
    entityManager.close();
  }
}
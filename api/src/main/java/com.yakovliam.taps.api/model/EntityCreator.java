package com.yakovliam.taps.api.model;

public interface EntityCreator<T> {

  /**
   * Creates an object in the database
   *
   * @param object object to create
   * @return the created object
   */
  T create(T object);
}

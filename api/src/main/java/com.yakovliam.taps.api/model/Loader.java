package com.yakovliam.taps.api.model;

public interface Loader<T> {

  /**
   * Load
   *
   * @return t
   */
  T load();
}

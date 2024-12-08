package com.yakovliam.taps.api.model;

import java.util.Map;

/**
 * Map serializable object
 */
public abstract class MapSerializableObject {

  /**
   * To map
   *
   * @return map
   */
  public abstract Map<String, String> toMap();
}

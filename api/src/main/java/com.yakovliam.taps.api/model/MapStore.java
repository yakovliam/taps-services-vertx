package com.yakovliam.taps.api.model;

import java.util.Map;
import java.util.Optional;

public class MapStore<K, V> {

  /**
   * Map
   */
  private Map<K, V> map;

  /**
   * Constructor
   *
   * @param map map
   */
  public MapStore(Map<K, V> map) {
    this.map = map;
  }

  /**
   * Constructor
   */
  public MapStore() {
    this(new java.util.HashMap<>());
  }

  public void setMap(Map<K, V> map) {
    this.map = map;
  }

  public void put(K key, V value) {
    map.put(key, value);
  }

  public V get(K key) {
    return map.get(key);
  }

  public Map<K, V> getMap() {
    return map;
  }

  public Optional<V> findFirstByPredicate(java.util.function.Predicate<V> predicate) {
    return map.values().stream().filter(predicate).findFirst();
  }

  public void forEach(java.util.function.BiConsumer<K, V> consumer) {
    map.forEach(consumer);
  }
}

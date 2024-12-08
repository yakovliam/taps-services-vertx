package com.yakovliam.taps.api.model;

import java.util.UUID;

/**
 * Noncable UUID
 * <p>
 * A uuid capable of being a 'nonce' (number used once).
 * This uuid MUST be capitalized at all times.
 */
public class NoncableUUID {

  /**
   * The uuid
   */
  private final String uuid;

  public NoncableUUID(String uuid) {
    this.uuid = uuid;
  }

  public NoncableUUID(UUID uuid) {
    this.uuid = uuid.toString().toUpperCase();
  }

  public String uuid() {
    return this.uuid;
  }

  public UUID toUUID() {
    return UUID.fromString(this.uuid);
  }

  @Override
  public String toString() {
    return this.uuid;
  }

  public static NoncableUUID from(UUID uuid) {
    return new NoncableUUID(uuid);
  }
}

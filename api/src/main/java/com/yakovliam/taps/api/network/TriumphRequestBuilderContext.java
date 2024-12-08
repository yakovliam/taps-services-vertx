package com.yakovliam.taps.api.network;

import com.yakovliam.taps.api.model.App;
import com.yakovliam.taps.api.model.Device;
import com.yakovliam.taps.api.model.Identity;

public class TriumphRequestBuilderContext extends RequestBuilderContext {

  /**
   * The app to be used when building the request
   */
  private App app;

  /**
   * The identity
   * <p>
   * This is nullable because some requests may not require an identity,
   * or may happen before an identity is established
   */
  private Identity identity;

  /**
   * The device
   */
  private Device device;

  /**
   * Triumph request builder context
   *
   * @param app      the app
   * @param identity the identity
   */
  public TriumphRequestBuilderContext(App app, Identity identity, Device device) {
    this.app = app;
    this.identity = identity;
    this.device = device;
  }

  /**
   * Triumph request builder context
   *
   * @param app    the app
   * @param device the device
   */
  public TriumphRequestBuilderContext(App app, Device device) {
    this.app = app;
    this.device = device;
    this.identity = null;
  }

  public App app() {
    return app;
  }

  public Identity identity() {
    return identity;
  }

  public Device device() {
    return device;
  }
}

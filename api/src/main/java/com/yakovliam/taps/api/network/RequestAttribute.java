package com.yakovliam.taps.api.network;

import com.yakovliam.taps.api.model.App;
import com.yakovliam.taps.api.model.Device;
import com.yakovliam.taps.api.model.Identity;

public enum RequestAttribute {
  USE_SIGNATURE(Boolean.class), INCLUDE_TRIUMPH_ID_TOKEN(Boolean.class),
  INCLUDE_FIREBASE_ID_TOKEN(Boolean.class), INCLUDE_GAME_ID(Boolean.class),
  INCLUDE_LOCATION(Boolean.class), GAME_ID(String.class), DEVICE(Device.class),
  IDENTITY(Identity.class), APP(App.class);

  private Class<?> type;

  RequestAttribute(Class<?> type) {
    this.type = type;
  }

  public Class<?> getType() {
    return type;
  }
}

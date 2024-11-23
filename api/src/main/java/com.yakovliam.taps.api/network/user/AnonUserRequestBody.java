package com.yakovliam.taps.api.network.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;

public class AnonUserRequestBody extends JsonSerializableObject {

  @JsonProperty("deviceToken")
  private String deviceToken;

  public AnonUserRequestBody(String deviceToken) {
    this.deviceToken = deviceToken;
  }

  public String getDeviceToken() {
    return deviceToken;
  }

  public void setDeviceToken(String deviceToken) {
    this.deviceToken = deviceToken;
  }
}

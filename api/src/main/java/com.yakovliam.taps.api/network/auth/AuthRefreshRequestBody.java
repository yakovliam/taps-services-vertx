package com.yakovliam.taps.api.network.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;

public class AuthRefreshRequestBody extends JsonSerializableObject {

  @JsonProperty("refreshToken")
  private final String refreshToken;

  @JsonProperty("deviceToken")
  private final String deviceToken;

  public AuthRefreshRequestBody(String refreshToken, String deviceToken) {
    this.refreshToken = refreshToken;
    this.deviceToken = deviceToken;
  }
}

package com.yakovliam.taps.api.network.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;

public class AnonUserResponseBody extends JsonSerializableObject {

  @JsonProperty("userId")
  private String userId;

  @JsonProperty("idToken")
  private String idToken;

  @JsonProperty("refreshToken")
  private String refreshToken;

  @JsonProperty("expiresIn")
  private int expiresIn;

  @JsonProperty("token")
  private String token;

  public AnonUserResponseBody(String userId, String idToken, String refreshToken, int expiresIn,
                              String token) {
    this.userId = userId;
    this.idToken = idToken;
    this.refreshToken = refreshToken;
    this.expiresIn = expiresIn;
    this.token = token;
  }

  public AnonUserResponseBody() {
  }

  public String userId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String idToken() {
    return idToken;
  }

  public void setIdToken(String idToken) {
    this.idToken = idToken;
  }

  public String refreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public int expiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String token() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}

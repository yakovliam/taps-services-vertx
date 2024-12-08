package com.yakovliam.taps.api.network.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;

public class AuthRefreshResponseBody extends JsonSerializableObject {

  @JsonProperty("userId")
  private String userId;

  @JsonProperty("expiresIn")
  private int expiresIn;

  @JsonProperty("idToken")
  private String idToken;

  @JsonProperty("refreshToken")
  private String refreshToken;

  public AuthRefreshResponseBody(String userId, int expiresIn, String idToken,
                                 String refreshToken) {
    this.userId = userId;
    this.expiresIn = expiresIn;
    this.idToken = idToken;
    this.refreshToken = refreshToken;
  }

  public AuthRefreshResponseBody() {
  }

  public String userId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public int expiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
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
}

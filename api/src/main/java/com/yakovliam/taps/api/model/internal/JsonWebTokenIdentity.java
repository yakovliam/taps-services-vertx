package com.yakovliam.taps.api.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonWebTokenIdentity {

  /**
   * The identifier
   */
  @JsonProperty("id")
  private String identifier;

  /**
   * The JWT token
   */
  @JsonProperty("jwt")
  private String jwt;

  /**
   * The issuer
   */
  @JsonProperty("iss")
  private String issuer;

  /**
   * The audience
   */
  @JsonProperty("aud")
  private String audience;

  /**
   * The authentication time
   */
  @JsonProperty("auth_time")
  private Long authTime;

  /**
   * The user id
   */
  @JsonProperty("user_id")
  private String userId;

  /**
   * The subject
   * This is the user id
   */
  @JsonProperty("sub")
  private String subject;

  /**
   * The time at which the token was issued
   */
  @JsonProperty("iat")
  private Long issuedAt;

  /**
   * The expiration time
   * This is the time at which the token will expire
   */
  @JsonProperty("exp")
  private Long expiration;

  /**
   * The refresh token
   * This is not included in the JWT token,
   * but is used to refresh the JWT token
   */
  @JsonProperty("refresh_token")
  private String refreshToken;

  /**
   * The longitude
   */
  @JsonProperty("longitude")
  private Double longitude;

  /**
   * The latitude
   */
  @JsonProperty("latitude")
  private Double latitude;

  /**
   * The device id
   */
  @JsonProperty("device_id")
  private String deviceId;

  /**
   * Identity
   *
   * @param identifier   identifier
   * @param jwt          jwt
   * @param issuer       issuer
   * @param audience     audience
   * @param authTime     auth time
   * @param userId       user id
   * @param subject      subject
   * @param issuedAt     issued at
   * @param expiration   expiration
   * @param refreshToken refresh token
   * @param longitude    longitude
   * @param latitude     latitude
   * @param deviceId     device id
   */
  public JsonWebTokenIdentity(String identifier, String jwt, String issuer, String audience, Long authTime,
                              String userId, String subject, Long issuedAt, Long expiration,
                              String refreshToken, Double longitude, Double latitude, String deviceId) {
    this.identifier = identifier;
    this.jwt = jwt;
    this.issuer = issuer;
    this.audience = audience;
    this.authTime = authTime;
    this.userId = userId;
    this.subject = subject;
    this.issuedAt = issuedAt;
    this.expiration = expiration;
    this.refreshToken = refreshToken;
    this.longitude = longitude;
    this.latitude = latitude;
    this.deviceId = deviceId;
  }

  public JsonWebTokenIdentity() {
    // empty constructor
  }

  public String identifier() {
    return identifier;
  }

  public JsonWebTokenIdentity setIdentifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

  public String jwt() {
    return jwt;
  }

  public JsonWebTokenIdentity setJwt(String jwt) {
    this.jwt = jwt;
    return this;
  }

  public String issuer() {
    return issuer;
  }

  public JsonWebTokenIdentity setIssuer(String issuer) {
    this.issuer = issuer;
    return this;
  }

  public String audience() {
    return audience;
  }

  public JsonWebTokenIdentity setAudience(String audience) {
    this.audience = audience;
    return this;
  }

  public Long authTime() {
    return authTime;
  }

  public JsonWebTokenIdentity setAuthTime(Long authTime) {
    this.authTime = authTime;
    return this;
  }

  public String userId() {
    return userId;
  }

  public JsonWebTokenIdentity setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String subject() {
    return subject;
  }

  public JsonWebTokenIdentity setSubject(String subject) {
    this.subject = subject;
    return this;
  }

  public Long issuedAt() {
    return issuedAt;
  }

  public JsonWebTokenIdentity setIssuedAt(Long issuedAt) {
    this.issuedAt = issuedAt;
    return this;
  }

  public Long expiration() {
    return expiration;
  }

  public JsonWebTokenIdentity setExpiration(Long expiration) {
    this.expiration = expiration;
    return this;
  }

  public String refreshToken() {
    return refreshToken;
  }

  public JsonWebTokenIdentity setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
    return this;
  }

  public Double longitude() {
    return longitude;
  }

  public JsonWebTokenIdentity setLongitude(Double longitude) {
    this.longitude = longitude;
    return this;
  }

  public Double latitude() {
    return latitude;
  }

  public JsonWebTokenIdentity setLatitude(Double latitude) {
    this.latitude = latitude;
    return this;
  }

  public String deviceId() {
    return deviceId;
  }

  public JsonWebTokenIdentity setDeviceId(String deviceId) {
    this.deviceId = deviceId;
    return this;
  }
}

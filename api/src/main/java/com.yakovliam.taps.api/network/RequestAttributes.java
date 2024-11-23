package com.yakovliam.taps.api.network;

public class RequestAttributes {

  private boolean useSignature;

  private boolean includeTriumphIdToken;

  private boolean includeFirebaseIdToken;

  private boolean includeGameId;

  private boolean excludeLocation;

  private String gameId;

  public RequestAttributes() {
    this.useSignature = false;
    this.includeTriumphIdToken = false;
    this.includeFirebaseIdToken = false;
    this.includeGameId = false;
    this.gameId = null;
    this.excludeLocation = false;
  }

  public boolean includeGameId() {
    return includeGameId;
  }

  public RequestAttributes includeGameId(boolean includeGameId) {
    this.includeGameId = includeGameId;
    return this;
  }

  public boolean includeFirebaseIdToken() {
    return includeFirebaseIdToken;
  }

  public RequestAttributes includeFirebaseIdToken(boolean includeFirebaseIdToken) {
    this.includeFirebaseIdToken = includeFirebaseIdToken;
    return this;
  }

  public boolean includeTriumphIdToken() {
    return includeTriumphIdToken;
  }

  public RequestAttributes includeTriumphIdToken(boolean includeTriumphIdToken) {
    this.includeTriumphIdToken = includeTriumphIdToken;
    return this;
  }

  public boolean useSignature() {
    return useSignature;
  }

  public RequestAttributes useSignature(boolean useSignature) {
    this.useSignature = useSignature;
    return this;
  }

  public String gameId() {
    return gameId;
  }

  public RequestAttributes gameId(String gameId) {
    this.gameId = gameId;
    return this;
  }


  public boolean excludeLocation() {
    return excludeLocation;
  }

  public RequestAttributes excludeLocation(boolean excludeLocation) {
    this.excludeLocation = excludeLocation;
    return this;
  }

  public static RequestAttributes create() {
    return new RequestAttributes();
  }
}

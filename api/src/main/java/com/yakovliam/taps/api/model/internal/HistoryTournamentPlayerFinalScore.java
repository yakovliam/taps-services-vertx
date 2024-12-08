package com.yakovliam.taps.api.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;

public class HistoryTournamentPlayerFinalScore extends JsonSerializableObject {

  @JsonProperty("systemReported")
  private boolean systemReported;

  @JsonProperty("crashReported")
  private boolean crashReported;

  @JsonProperty("value")
  private int value;

  @JsonProperty("createdAt")
  private long createdAt;

  public HistoryTournamentPlayerFinalScore() {
  }

  public boolean isSystemReported() {
    return systemReported;
  }

  public void setSystemReported(boolean systemReported) {
    this.systemReported = systemReported;
  }

  public boolean isCrashReported() {
    return crashReported;
  }

  public void setCrashReported(boolean crashReported) {
    this.crashReported = crashReported;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(long createdAt) {
    this.createdAt = createdAt;
  }
}

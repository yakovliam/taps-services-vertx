package com.yakovliam.taps.api.network.history;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;
import com.yakovliam.taps.api.model.internal.HistoryTournament;

public class HistoryResponseBody extends JsonSerializableObject {

  @JsonProperty("tournaments")
  private HistoryTournament[] tournaments;

  @JsonProperty("lastCreatedAt")
  private long lastCreatedAt;

  public HistoryResponseBody() {
  }

  public HistoryTournament[] getTournaments() {
    return tournaments;
  }

  public void setTournaments(HistoryTournament[] tournaments) {
    this.tournaments = tournaments;
  }

  public long getLastCreatedAt() {
    return lastCreatedAt;
  }

  public void setLastCreatedAt(long lastCreatedAt) {
    this.lastCreatedAt = lastCreatedAt;
  }
}

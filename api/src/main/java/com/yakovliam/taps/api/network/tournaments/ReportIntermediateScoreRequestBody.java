package com.yakovliam.taps.api.network.tournaments;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;

public class ReportIntermediateScoreRequestBody extends JsonSerializableObject {

  @JsonProperty("createdAt")
  private long createdAt;

  @JsonProperty("sequenceId")
  private int sequenceId;

  @JsonProperty("value")
  private int value;

  @JsonProperty("tournamentId")
  private String tournamentId;

  @JsonProperty("gameId")
  private String gameId;

  public ReportIntermediateScoreRequestBody(long createdAt, int sequenceId, int value,
                                            String tournamentId, String gameId) {
    this.createdAt = createdAt;
    this.sequenceId = sequenceId;
    this.value = value;
    this.tournamentId = tournamentId;
    this.gameId = gameId;
  }

  public long createdAt() {
    return createdAt;
  }

  public void setCreatedAt(long createdAt) {
    this.createdAt = createdAt;
  }

  public int sequenceId() {
    return sequenceId;
  }

  public void setSequenceId(int sequenceId) {
    this.sequenceId = sequenceId;
  }

  public int value() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String tournamentId() {
    return tournamentId;
  }

  public void setTournamentId(String tournamentId) {
    this.tournamentId = tournamentId;
  }

  public String gameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }
}

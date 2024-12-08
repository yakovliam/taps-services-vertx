package com.yakovliam.taps.api.network.tournaments;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;

public class ReportScoreRequestBody extends JsonSerializableObject {

  @JsonProperty("score")
  private int score;

  @JsonProperty("tournamentId")
  private String tournamentId;

  @JsonProperty("gameId")
  private String gameId;

  public ReportScoreRequestBody(int score, String tournamentId, String gameId) {
    this.score = score;
    this.tournamentId = tournamentId;
    this.gameId = gameId;
  }

  public int score() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
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


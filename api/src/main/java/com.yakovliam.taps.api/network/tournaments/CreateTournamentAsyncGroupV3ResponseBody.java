package com.yakovliam.taps.api.network.tournaments;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;
import com.yakovliam.taps.api.model.internal.TournamentDefinition;
import com.yakovliam.taps.api.model.internal.TournamentV3;

public class CreateTournamentAsyncGroupV3ResponseBody extends JsonSerializableObject {

  @JsonProperty("status")
  private String status;

  @JsonProperty("tournamentId")
  private String tournamentId;

  @JsonProperty("tournament")
  private TournamentV3 tournament;

  public CreateTournamentAsyncGroupV3ResponseBody() {
  }

  public String getStatus() {
    return status;
  }

  public String getTournamentId() {
    return tournamentId;
  }

  public TournamentV3 getTournament() {
    return tournament;
  }
}

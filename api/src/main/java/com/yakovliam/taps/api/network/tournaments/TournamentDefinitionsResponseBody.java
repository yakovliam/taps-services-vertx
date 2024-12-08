package com.yakovliam.taps.api.network.tournaments;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;
import com.yakovliam.taps.api.model.internal.TournamentDefinition;

public class TournamentDefinitionsResponseBody extends JsonSerializableObject {

  @JsonProperty("tournamentDefinitions")
  private TournamentDefinition[] tournamentDefinitions;

  public TournamentDefinitionsResponseBody(TournamentDefinition[] tournamentDefinitions) {
    this.tournamentDefinitions = tournamentDefinitions;
  }

  public TournamentDefinitionsResponseBody() {
  }

  public TournamentDefinition[] getTournamentDefinitions() {
    return tournamentDefinitions;
  }
}

package com.yakovliam.taps.api.network.tournaments;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;

public class CreateTournamentAsyncGroupV3RequestBody extends JsonSerializableObject {

  @JsonProperty("gameId")
  private String gameId;

  @JsonProperty("configId")
  private String configId;

  public CreateTournamentAsyncGroupV3RequestBody(String gameId, String configId) {
    this.gameId = gameId;
    this.configId = configId;
  }

  public String gameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }

  public String configId() {
    return configId;
  }

  public void setConfigId(String configId) {
    this.configId = configId;
  }
}
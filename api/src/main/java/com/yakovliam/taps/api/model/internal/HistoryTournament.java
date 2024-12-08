package com.yakovliam.taps.api.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;
import java.util.Map;

public class HistoryTournament extends JsonSerializableObject {

  @JsonProperty("id")
  private String id;

  @JsonProperty("gameId")
  private String gameId;

  @JsonProperty("type")
  private GameConfigType type;

  @JsonProperty("status")
  private String status;

  @JsonProperty("seed")
  private long seed;

  @JsonProperty("results")
  private Map<String, HistoryTournamentResult> results;

  @JsonProperty("createdAt")
  private long createdAt;

  @JsonProperty("blitzMultipliers")
  private Object blitzMultipliers;

  @JsonProperty("blitzMultiplier")
  private Object blitzMultiplier;

  @JsonProperty("blitzDefinition")
  private Object blitzDefinition;

  @JsonProperty("blitzVersion")
  private Object blitzVersion;

  @JsonProperty("replays")
  private Object replays;

  @JsonProperty("participantsDefinitions")
  private Map<String, HistoryTournamentParticipant> participantsDefinitions;

  @JsonProperty("players")
  private HistoryTournamentPlayer[] players;

  @JsonProperty("challengeEntries")
  private Object challengeEntries;

  public HistoryTournament() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }

  public GameConfigType getType() {
    return type;
  }

  public void setType(GameConfigType type) {
    this.type = type;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public long getSeed() {
    return seed;
  }

  public void setSeed(long seed) {
    this.seed = seed;
  }

  public Map<String, HistoryTournamentResult> getResults() {
    return results;
  }

  public void setResults(Map<String, HistoryTournamentResult> results) {
    this.results = results;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(long createdAt) {
    this.createdAt = createdAt;
  }

  public Object getBlitzMultipliers() {
    return blitzMultipliers;
  }

  public void setBlitzMultipliers(Object blitzMultipliers) {
    this.blitzMultipliers = blitzMultipliers;
  }

  public Object getBlitzMultiplier() {
    return blitzMultiplier;
  }

  public void setBlitzMultiplier(Object blitzMultiplier) {
    this.blitzMultiplier = blitzMultiplier;
  }

  public Object getBlitzDefinition() {
    return blitzDefinition;
  }

  public void setBlitzDefinition(Object blitzDefinition) {
    this.blitzDefinition = blitzDefinition;
  }

  public Object getBlitzVersion() {
    return blitzVersion;
  }

  public void setBlitzVersion(Object blitzVersion) {
    this.blitzVersion = blitzVersion;
  }

  public Object getReplays() {
    return replays;
  }

  public void setReplays(Object replays) {
    this.replays = replays;
  }

  public Map<String, HistoryTournamentParticipant> getParticipantsDefinitions() {
    return participantsDefinitions;
  }

  public void setParticipantsDefinitions(
      Map<String, HistoryTournamentParticipant> participantsDefinitions) {
    this.participantsDefinitions = participantsDefinitions;
  }

  public HistoryTournamentPlayer[] getPlayers() {
    return players;
  }

  public void setPlayers(HistoryTournamentPlayer[] players) {
    this.players = players;
  }

  public Object getChallengeEntries() {
    return challengeEntries;
  }

  public void setChallengeEntries(Object challengeEntries) {
    this.challengeEntries = challengeEntries;
  }
}

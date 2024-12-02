package com.yakovliam.taps.api.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;

public class TournamentV3 extends JsonSerializableObject {

  /**
   * {
   * "uid": "aZpPB9vzOun9AoU3m1D4",
   * "orgId": "HTf2sX69vBmuD0nmC1qq",
   * "type": "async-group-v3",
   * "gameId": "brickbreaker",
   * "RNG": 5922678206383230140,
   * "version": "3",
   * "participantsMax": 2,
   * "participantsSize": 1,
   * "participants": [
   * "Gz50GAUvQJPzzbZ5ynHnlSVd3i42"
   * ],
   * "removed": {},
   * "players": [
   * {
   * "uid": "Gz50GAUvQJPzzbZ5ynHnlSVd3i42",
   * "weightedAverageScore": 8204.116357663685,
   * "eloV4": 1987.1714401212073,
   * "eloV4LowerBound": 2137.1714401212075,
   * "eloV4UpperBound": null,
   * "eloV4Priority": 2,
   * "momentum": null,
   * "finalScore": null,
   * "createdAt": 1729567416695,
   * "leagueIndex": 4,
   * "profilePhotoPath": null,
   * "profilePhotoUrl": "https://picsum.photos/id/677/800",
   * "username": "pintobean01",
   * "emojiReaction": null,
   * "location": "World"
   * }
   * ],
   * "status": "waiting-to-match",
   * "replays": {},
   * "winnerUids": [],
   * "results": {},
   * "createdAt": 1729567416695,
   * "updatedAt": 1729567416695,
   * "participantConfigs": {
   * "Gz50GAUvQJPzzbZ5ynHnlSVd3i42": {
   * "uid": "practice-v3",
   * "type": "async-group-v3",
   * "name": "Practice",
   * "entryPrice": 0,
   * "entryGems": 2,
   * "archived": false,
   * "version": "",
   * "size": 2,
   * "createdAt": 1673318190752,
   * "priority": 0,
   * "softArchived": false,
   * "updatedAt": 1711683810000,
   * "rewards": [
   * {
   * "payout": 0,
   * "payoutBonusCash": 0,
   * "payoutGems": 4
   * }
   * ],
   * "VIPGroups": null
   * }
   * },
   * "unClaimedUids": [
   * "Gz50GAUvQJPzzbZ5ynHnlSVd3i42"
   * ]
   * }
   */

  @JsonProperty("uid")
  private String uid;

  @JsonProperty("orgId")
  private String orgId;

  @JsonProperty("type")
  private String type;

  @JsonProperty("gameId")
  private String gameId;

  @JsonProperty("RNG")
  private Long RNG;

  @JsonProperty("version")
  private String version;

  @JsonProperty("participantsMax")
  private Integer participantsMax;

  @JsonProperty("participantsSize")
  private Integer participantsSize;

  @JsonProperty("participants")
  private String[] participants;

//  @JsonProperty("removed")
//  private Object removed;

  @JsonProperty("players")
  private TournamentV3Player[] players;

  @JsonProperty("status")
  private String status;

//  @JsonProperty("replays")
//  private Object replays;

  @JsonProperty("winnerUids")
  private String[] winnerUids;

//  @JsonProperty("results")
//  private Object results;

  @JsonProperty("createdAt")
  private Long createdAt;

  @JsonProperty("updatedAt")
  private Long updatedAt;

//  @JsonProperty("participantConfigs")
//  private Map<String, TournamentV3ParticipantConfig> participantConfigs;

  @JsonProperty("unClaimedUids")
  private String[] unClaimedUids;

  public TournamentV3() {
  }

  public String getUid() {
    return uid;
  }

  public String getOrgId() {
    return orgId;
  }

  public String getType() {
    return type;
  }

  public String getGameId() {
    return gameId;
  }

  public Long getRNG() {
    return RNG;
  }

  public String getVersion() {
    return version;
  }

  public Integer getParticipantsMax() {
    return participantsMax;
  }

  public Integer getParticipantsSize() {
    return participantsSize;
  }

  public String[] getParticipants() {
    return participants;
  }

  public TournamentV3Player[] getPlayers() {
    return players;
  }

  public String getStatus() {
    return status;
  }

  public String[] getWinnerUids() {
    return winnerUids;
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  public Long getUpdatedAt() {
    return updatedAt;
  }

  public String[] getUnClaimedUids() {
    return unClaimedUids;
  }
}

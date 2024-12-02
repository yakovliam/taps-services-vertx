package com.yakovliam.taps.api.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;

public class TournamentV3Player extends JsonSerializableObject {

  @JsonProperty("uid")
  private String uid;

  @JsonProperty("weightedAverageScore")
  private double weightedAverageScore;

  @JsonProperty("eloV4")
  private double eloV4;

  @JsonProperty("eloV4LowerBound")
  private double eloV4LowerBound;

  @JsonProperty("eloV4UpperBound")
  private double eloV4UpperBound;

  @JsonProperty("eloV4Priority")
  private int eloV4Priority;

  @JsonProperty("momentum")
  private double momentum;

  @JsonProperty("finalScore")
  private Integer finalScore;

  @JsonProperty("createdAt")
  private long createdAt;

  @JsonProperty("leagueIndex")
  private int leagueIndex;

  @JsonProperty("profilePhotoPath")
  private String profilePhotoPath;

  @JsonProperty("profilePhotoUrl")
  private String profilePhotoUrl;

  @JsonProperty("username")
  private String username;

//  @JsonProperty("emojiReaction")
//  private Object emojiReaction;

  @JsonProperty("location")
  private String location;

  public TournamentV3Player() {
  }


  public String getUid() {
    return uid;
  }

  public double getWeightedAverageScore() {
    return weightedAverageScore;
  }

  public double getEloV4() {
    return eloV4;
  }

  public double getEloV4LowerBound() {
    return eloV4LowerBound;
  }

  public double getEloV4UpperBound() {
    return eloV4UpperBound;
  }

  public int getEloV4Priority() {
    return eloV4Priority;
  }

  public double getMomentum() {
    return momentum;
  }

  public Integer getFinalScore() {
    return finalScore;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public int getLeagueIndex() {
    return leagueIndex;
  }

  public String getProfilePhotoPath() {
    return profilePhotoPath;
  }

  public String getProfilePhotoUrl() {
    return profilePhotoUrl;
  }

  public String getUsername() {
    return username;
  }

  public String getLocation() {
    return location;
  }
}

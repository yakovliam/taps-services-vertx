package com.yakovliam.taps.api.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;

public class HistoryTournamentPlayer extends JsonSerializableObject {

  @JsonProperty("leagueIndex")
  private int leagueIndex;

  @JsonProperty("profilePhotoPath")
  private Object profilePhotoPath;

  @JsonProperty("profilePhotoUrl")
  private String profilePhotoUrl;

  @JsonProperty("username")
  private String username;

  @JsonProperty("emojiReaction")
  private Object emojiReaction;

  @JsonProperty("canCancelStartingAt")
  private Object canCancelStartingAt;

  @JsonProperty("cancelledAt")
  private Object cancelledAt;

  @JsonProperty("canCancel")
  private boolean canCancel;

  @JsonProperty("isCancelled")
  private boolean isCancelled;

  @JsonProperty("uid")
  private String uid;

  @JsonProperty("weightedAverageScore")
  private double weightedAverageScore;

  @JsonProperty("eloV4")
  private double eloV4;

  @JsonProperty("eloV4LowerBound")
  private double eloV4LowerBound;

  @JsonProperty("eloV4UpperBound")
  private Object eloV4UpperBound;

  @JsonProperty("eloV4Priority")
  private int eloV4Priority;

  @JsonProperty("momentum")
  private Object momentum;

  @JsonProperty("finalScore")
  private HistoryTournamentPlayerFinalScore finalScore;

  @JsonProperty("createdAt")
  private long createdAt;

  @JsonProperty("location")
  private String location;

  public HistoryTournamentPlayer() {
  }

  public int getLeagueIndex() {
    return leagueIndex;
  }

  public void setLeagueIndex(int leagueIndex) {
    this.leagueIndex = leagueIndex;
  }

  public Object getProfilePhotoPath() {
    return profilePhotoPath;
  }

  public void setProfilePhotoPath(Object profilePhotoPath) {
    this.profilePhotoPath = profilePhotoPath;
  }

  public String getProfilePhotoUrl() {
    return profilePhotoUrl;
  }

  public void setProfilePhotoUrl(String profilePhotoUrl) {
    this.profilePhotoUrl = profilePhotoUrl;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Object getEmojiReaction() {
    return emojiReaction;
  }

  public void setEmojiReaction(Object emojiReaction) {
    this.emojiReaction = emojiReaction;
  }

  public Object getCanCancelStartingAt() {
    return canCancelStartingAt;
  }

  public void setCanCancelStartingAt(Object canCancelStartingAt) {
    this.canCancelStartingAt = canCancelStartingAt;
  }

  public Object getCancelledAt() {
    return cancelledAt;
  }

  public void setCancelledAt(Object cancelledAt) {
    this.cancelledAt = cancelledAt;
  }

  public boolean isCanCancel() {
    return canCancel;
  }

  public void setCanCancel(boolean canCancel) {
    this.canCancel = canCancel;
  }

  public boolean isCancelled() {
    return isCancelled;
  }

  public void setCancelled(boolean cancelled) {
    isCancelled = cancelled;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public double getWeightedAverageScore() {
    return weightedAverageScore;
  }

  public void setWeightedAverageScore(double weightedAverageScore) {
    this.weightedAverageScore = weightedAverageScore;
  }

  public double getEloV4() {
    return eloV4;
  }

  public void setEloV4(double eloV4) {
    this.eloV4 = eloV4;
  }

  public double getEloV4LowerBound() {
    return eloV4LowerBound;
  }

  public void setEloV4LowerBound(double eloV4LowerBound) {
    this.eloV4LowerBound = eloV4LowerBound;
  }

  public Object getEloV4UpperBound() {
    return eloV4UpperBound;
  }

  public void setEloV4UpperBound(Object eloV4UpperBound) {
    this.eloV4UpperBound = eloV4UpperBound;
  }

  public int getEloV4Priority() {
    return eloV4Priority;
  }

  public void setEloV4Priority(int eloV4Priority) {
    this.eloV4Priority = eloV4Priority;
  }

  public Object getMomentum() {
    return momentum;
  }

  public void setMomentum(Object momentum) {
    this.momentum = momentum;
  }

  public HistoryTournamentPlayerFinalScore getFinalScore() {
    return finalScore;
  }

  public void setFinalScore(HistoryTournamentPlayerFinalScore finalScore) {
    this.finalScore = finalScore;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(long createdAt) {
    this.createdAt = createdAt;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}

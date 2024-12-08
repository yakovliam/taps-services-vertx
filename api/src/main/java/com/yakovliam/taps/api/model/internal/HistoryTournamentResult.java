package com.yakovliam.taps.api.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;

public class HistoryTournamentResult extends JsonSerializableObject {

  @JsonProperty("won")
  private boolean won;

  @JsonProperty("wonBlitzJackpot")
  private Object wonBlitzJackpot;

  @JsonProperty("payout")
  private long payout;

  @JsonProperty("payoutGems")
  private long payoutGems;

  @JsonProperty("payoutBonusCash")
  private long payoutBonusCash;

  @JsonProperty("score")
  private long score;

  @JsonProperty("place")
  private long place;

  @JsonProperty("placeCount")
  private long placeCount;

  @JsonProperty("claimedAt")
  private Object claimedAt;

  @JsonProperty("crowns")
  private long crowns;

  public HistoryTournamentResult() {
  }

  public boolean isWon() {
    return won;
  }

  public void setWon(boolean won) {
    this.won = won;
  }

  public Object getWonBlitzJackpot() {
    return wonBlitzJackpot;
  }

  public void setWonBlitzJackpot(Object wonBlitzJackpot) {
    this.wonBlitzJackpot = wonBlitzJackpot;
  }

  public long getPayout() {
    return payout;
  }

  public void setPayout(long payout) {
    this.payout = payout;
  }

  public long getPayoutGems() {
    return payoutGems;
  }

  public void setPayoutGems(long payoutGems) {
    this.payoutGems = payoutGems;
  }

  public long getPayoutBonusCash() {
    return payoutBonusCash;
  }

  public void setPayoutBonusCash(long payoutBonusCash) {
    this.payoutBonusCash = payoutBonusCash;
  }

  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }

  public long getPlace() {
    return place;
  }

  public void setPlace(long place) {
    this.place = place;
  }

  public long getPlaceCount() {
    return placeCount;
  }

  public void setPlaceCount(long placeCount) {
    this.placeCount = placeCount;
  }

  public Object getClaimedAt() {
    return claimedAt;
  }

  public void setClaimedAt(Object claimedAt) {
    this.claimedAt = claimedAt;
  }

  public long getCrowns() {
    return crowns;
  }

  public void setCrowns(long crowns) {
    this.crowns = crowns;
  }
}

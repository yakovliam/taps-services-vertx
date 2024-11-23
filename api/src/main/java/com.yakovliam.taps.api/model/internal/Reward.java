package com.yakovliam.taps.api.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;

public class Reward extends JsonSerializableObject {

  @JsonProperty("payout")
  private int payout;

  @JsonProperty("payoutBonusCash")
  private int payoutBonusCash;

  @JsonProperty("payoutGems")
  private int payoutGems;

  public Reward(int payout, int payoutBonusCash, int payoutGems) {
    this.payout = payout;
    this.payoutBonusCash = payoutBonusCash;
    this.payoutGems = payoutGems;
  }

  public Reward() {
  }

  public int getPayout() {
    return payout;
  }

  public int getPayoutBonusCash() {
    return payoutBonusCash;
  }

  public int getPayoutGems() {
    return payoutGems;
  }
}

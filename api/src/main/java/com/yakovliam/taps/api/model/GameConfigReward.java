package com.yakovliam.taps.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "game_config_reward")
public class GameConfigReward {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "game_config_id")
  private GameConfig gameConfig;

  @Column(name = "payout")
  private int payout;

  @Column(name = "payout_bonus_cash")
  private int payoutBonusCash;

  @Column(name = "payout_gems")
  private int payoutGems;

  public Long id() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public GameConfig gameConfig() {
    return gameConfig;
  }

  public void setGameConfig(GameConfig gameConfig) {
    this.gameConfig = gameConfig;
  }

  public int payout() {
    return payout;
  }

  public void setPayout(int payout) {
    this.payout = payout;
  }

  public int payoutBonusCash() {
    return payoutBonusCash;
  }

  public void setPayoutBonusCash(int payoutBonusCash) {
    this.payoutBonusCash = payoutBonusCash;
  }

  public int payoutGems() {
    return payoutGems;
  }

  public void setPayoutGems(int payoutGems) {
    this.payoutGems = payoutGems;
  }
}

package com.yakovliam.taps.api.model;

import com.yakovliam.taps.api.model.internal.GameConfigType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "game_config")
public class GameConfig {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "game_id")
  private Game game;

  @Column(name = "uid")
  private String uid;

  @Column(name = "name")
  private String name;

  @Column(name = "type")
  @Enumerated(EnumType.STRING)
  private GameConfigType type;

  @Column(name = "size")
  private int size;

  @OneToMany(mappedBy = "gameConfig")
  private Set<GameConfigReward> rewards;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public GameConfigType getType() {
    return type;
  }

  public void setType(GameConfigType type) {
    this.type = type;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public Set<GameConfigReward> getRewards() {
    return rewards;
  }

  public void setRewards(Set<GameConfigReward> rewards) {
    this.rewards = rewards;
  }
}

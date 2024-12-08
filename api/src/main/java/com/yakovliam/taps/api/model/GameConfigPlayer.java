package com.yakovliam.taps.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "game_config_player")
public class GameConfigPlayer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "game_config_id")
  private GameConfig gameConfig;

  @ManyToOne
  @JoinColumn(name = "predictor_model_id")
  private PredictorModel predictorModel;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public GameConfig getGameConfig() {
    return gameConfig;
  }

  public void setGameConfig(GameConfig gameConfig) {
    this.gameConfig = gameConfig;
  }

  public PredictorModel getPredictorModel() {
    return predictorModel;
  }

  public void setPredictorModel(PredictorModel predictorModel) {
    this.predictorModel = predictorModel;
  }
}

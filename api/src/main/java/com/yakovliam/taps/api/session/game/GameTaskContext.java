package com.yakovliam.taps.api.session.game;

import com.yakovliam.taps.api.model.Game;
import com.yakovliam.taps.api.model.Identity;

public class GameTaskContext {

  private final Identity identity;

  private final Game game;

  private GameTaskStrategy strategy;

  public GameTaskContext(Identity identity, Game game) {
    this.identity = identity;
    this.game = game;
  }

  public void setStrategy(GameTaskStrategy strategy) {
    this.strategy = strategy;
  }

  public GameTaskResult executeStrategy() {
    return strategy.execute(this);
  }

  public Identity getIdentity() {
    return identity;
  }

  public Game getGame() {
    return game;
  }
}
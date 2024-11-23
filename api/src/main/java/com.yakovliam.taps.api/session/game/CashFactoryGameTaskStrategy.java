package com.yakovliam.taps.api.session.game;

public class CashFactoryGameTaskStrategy implements GameTaskStrategy {

  @Override
  public GameTaskResult execute(GameTaskContext context) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return new GameTaskResult();
  }
}

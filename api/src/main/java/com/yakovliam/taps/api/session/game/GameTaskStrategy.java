package com.yakovliam.taps.api.session.game;

public interface GameTaskStrategy {

  GameTaskResult execute(GameTaskContext context);
}

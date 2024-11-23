package com.yakovliam.taps.api.player;

/**
 * Game player
 *
 * @param <R> result type
 * @param <I> input type
 */
public abstract class GamePlayer<R, I> {

  /**
   * Predict with a model
   *
   * @param input input
   * @return result
   */
  public abstract R play(I input);
}

package com.yakovliam.taps.api.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Glicko2Instant {

  @JsonProperty("vol")
  private double vol;

  @JsonProperty("rating")
  private double rating;

  @JsonProperty("deviation")
  private double deviation;

  public Glicko2Instant(double vol, double rating, double deviation) {
    this.vol = vol;
    this.rating = rating;
    this.deviation = deviation;
  }

  public Glicko2Instant() {
  }

  public double vol() {
    return vol;
  }

  public double rating() {
    return rating;
  }

  public double deviation() {
    return deviation;
  }
}

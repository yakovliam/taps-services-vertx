package com.yakovliam.taps.api.model.internal;


import com.fasterxml.jackson.annotation.JsonProperty;

public class IntermediateScore {

  @JsonProperty("createdAt")
  long createdAt;

  @JsonProperty("value")
  int value;

  @JsonProperty("sequenceId")
  int sequenceId;

  /**
   * PracticeIntermediateScore
   *
   * @param createdAt  the time the score was created at
   * @param value      the value of the score
   * @param sequenceId the sequence id
   */
  public IntermediateScore(long createdAt, int value, int sequenceId) {
    this.createdAt = createdAt;
    this.value = value;
    this.sequenceId = sequenceId;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public int getValue() {
    return value;
  }

  public int getSequenceId() {
    return sequenceId;
  }

  @Override
  public String toString() {
    return "IntermediateScore{" + "createdAt=" + createdAt + ", value=" + value + ", sequenceId=" +
        sequenceId + '}';
  }
}

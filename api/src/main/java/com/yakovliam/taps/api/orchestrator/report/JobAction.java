package com.yakovliam.taps.api.orchestrator.report;

import java.util.Date;

public class JobAction {

  /**
   * The description of the action
   */
  private final String description;

  /**
   * The timestamp of the action
   */
  private final Date timestamp;

  /**
   * Job action
   *
   * @param description description
   * @param timestamp   timestamp
   */
  public JobAction(String description, Date timestamp) {
    this.description = description;
    this.timestamp = timestamp;
  }

  public JobAction(String description) {
    this(description, new Date());
  }

  public String getDescription() {
    return description;
  }

  public Date getTimestamp() {
    return timestamp;
  }
}
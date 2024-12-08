package com.yakovliam.taps.api.session.report;

import java.util.Date;

public class SessionJobAction {

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
  public SessionJobAction(String description, Date timestamp) {
    this.description = description;
    this.timestamp = timestamp;
  }

  public String getDescription() {
    return description;
  }

  public Date getTimestamp() {
    return timestamp;
  }
}
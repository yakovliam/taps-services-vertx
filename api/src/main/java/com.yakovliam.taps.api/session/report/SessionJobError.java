package com.yakovliam.taps.api.session.report;

import java.util.Date;

public class SessionJobError {

  /**
   * Message
   */
  private final String message;

  /**
   * Date
   */
  private final Date date;

  /**
   * Cause
   */
  private final Throwable cause;

  /**
   * Job error
   *
   * @param message message
   * @param date    date
   * @param cause   cause
   */
  public SessionJobError(String message, Date date, Throwable cause) {
    this.message = message;
    this.date = date;
    this.cause = cause;
  }

  /**
   * Job error
   *
   * @param message message
   * @param cause   cause
   */
  public SessionJobError(String message, Throwable cause) {
    this(message, new Date(), cause);
  }

  public String getMessage() {
    return message;
  }

  public Date getDate() {
    return date;
  }

  public Throwable getCause() {
    return cause;
  }
}

package com.yakovliam.taps.api.session;

import com.yakovliam.taps.api.session.report.SessionJobExecutionReport;

/**
 * Job criterion
 */
public abstract class SessionJobCriterion {

  /**
   * Returns whether the criterion is met
   *
   * @param report report
   * @return boolean
   */
  public abstract boolean isMet(SessionJobExecutionReport report);
}

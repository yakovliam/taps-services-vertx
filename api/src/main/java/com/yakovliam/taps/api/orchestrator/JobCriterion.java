package com.yakovliam.taps.api.orchestrator;

import com.yakovliam.taps.api.orchestrator.report.JobExecutionReport;

/**
 * Job criterion
 */
public abstract class JobCriterion {

  /**
   * Returns whether the criterion is met
   *
   * @param report report
   * @return boolean
   */
  public abstract boolean isMet(JobExecutionReport report);
}

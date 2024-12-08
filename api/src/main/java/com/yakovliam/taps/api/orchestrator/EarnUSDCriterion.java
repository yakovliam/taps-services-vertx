package com.yakovliam.taps.api.orchestrator;

import com.yakovliam.taps.api.orchestrator.report.JobExecutionReport;

public class EarnUSDCriterion extends JobCriterion {

  /**
   * The amount of USD to earn
   */
  private final double amount;

  /**
   * Earn USD criterion
   *
   * @param amount the amount
   */
  public EarnUSDCriterion(double amount) {
    this.amount = amount;
  }

  @Override
  public boolean isMet(JobExecutionReport report) {
    return report.getTotalEarnings() >= amount;
  }
}

package com.yakovliam.taps.api.session;

import com.yakovliam.taps.api.session.report.SessionJobExecutionReport;

public class EarnUSDSessionCriterion extends SessionJobCriterion {

  /**
   * The amount of USD to earn
   */
  private final double amount;

  /**
   * Earn USD criterion
   *
   * @param amount the amount
   */
  public EarnUSDSessionCriterion(double amount) {
    this.amount = amount;
  }

  @Override
  public boolean isMet(SessionJobExecutionReport report) {
    return report.getTotalEarnings() >= amount;
  }
}

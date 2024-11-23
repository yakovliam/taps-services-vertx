package com.yakovliam.taps.api.orchestrator.executor;

import com.yakovliam.taps.api.model.App;
import com.yakovliam.taps.api.model.AppRepository;
import com.yakovliam.taps.api.orchestrator.EarnUSDJobGoal;
import com.yakovliam.taps.api.orchestrator.Job;
import com.yakovliam.taps.api.orchestrator.report.JobAction;
import com.yakovliam.taps.api.orchestrator.report.JobError;
import com.yakovliam.taps.api.orchestrator.report.JobExecutionReport;
import com.yakovliam.taps.api.session.EarnUSDSessionJobGoal;
import com.yakovliam.taps.api.session.SessionJob;
import com.yakovliam.taps.api.session.report.SessionJobExecutionReport;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;

public class EarnUSDJobExecutor extends JobExecutor<EarnUSDJobGoal> {

  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EarnUSDJobExecutor.class);

  @Override
  public JobExecutionReport execute(Job<EarnUSDJobGoal> job) {
    EarnUSDJobGoal goal = job.getGoal();

    LOGGER.info("Executing job with goal: {}", goal.getName());
    LOGGER.info("Attempting to earn {} USD", goal.getExpectedReturn());

    //////////////////////////

    // Calculate the number of identities needed to earn the expected return

    // Create a SessionJob with a corresponding goal; for each identity needed
    //    - Don't create the identities, beforehand.
    //      On execution, find an available identity and use it.
    //      If no available identities, create a new one dynamically.

    int neededIdentities = 1; // TODO calculate this based on the expected return, dynamically
    List<SessionJob<EarnUSDSessionJobGoal>> sessionJobs = new java.util.ArrayList<>();

    // select Triumph app
    // TODO in the future, select an app based on some algorithm
    App app = AppRepository.getInstance().findByName("triumph");

    addAction(new JobAction("Calculated the number of identities needed: " + neededIdentities));

    // split the expected return of the job evenly into the session jobs
    // TODO split it unevenly, possibly using a random distribution or some algorithm
    double expectedReturnPerSessionJob = goal.getExpectedReturn() / neededIdentities;
    // round up
    expectedReturnPerSessionJob = Math.ceil(expectedReturnPerSessionJob * 100.0) / 100.0;

    addAction(new JobAction(
        "Split the expected return evenly into session jobs: " + expectedReturnPerSessionJob));

    for (int i = 0; i < neededIdentities; i++) {
      sessionJobs.add(
          new SessionJob<>(new EarnUSDSessionJobGoal(expectedReturnPerSessionJob, app)));
    }

    LOGGER.info("Created {} session jobs", sessionJobs.size());

    // orchestrate all the session jobs concurrently
    JobExecutionReport combinedReport = executeAndCombineReports(sessionJobs, goal);

    //////////////////////////

    LOGGER.info("Job executed successfully");

    return combinedReport;
  }

  @Override
  public Class<EarnUSDJobGoal> getGoalType() {
    return EarnUSDJobGoal.class;
  }

  private JobExecutionReport executeAndCombineReports(
      List<SessionJob<EarnUSDSessionJobGoal>> sessionJobs, EarnUSDJobGoal goal) {
    // execute all the session jobs concurrently
    // combine the reports into a single report

    // create a list of futures
    List<CompletableFuture<SessionJobExecutionReport>> futures = new java.util.ArrayList<>();


    for (SessionJob<EarnUSDSessionJobGoal> sessionJob : sessionJobs) {
      futures.add(sessionJob.execute());
    }

    // wait for all the futures to complete and
    // map the futures to their reports
    List<SessionJobExecutionReport> reports =
        futures.stream().map(CompletableFuture::join).toList();

    List<JobAction> jobActions = this.getJobActions();
    reports.stream().map(SessionJobExecutionReport::getJobActions).flatMap(List::stream)
        .forEach(a -> {
          jobActions.add(
              new JobAction("Session job action: " + a.getDescription(), a.getTimestamp()));
        });

    List<JobError> jobErrors = this.getJobErrors();
    reports.stream().map(SessionJobExecutionReport::getErrors).flatMap(List::stream).forEach(e -> {
      jobErrors.add(
          new JobError("Session job error: " + e.getMessage(), e.getDate(), e.getCause()));
    });

    double totalEarnings =
        reports.stream().mapToDouble(SessionJobExecutionReport::getTotalEarnings).sum();

    // create a combined report
    return new JobExecutionReport(goal, jobActions, totalEarnings, jobErrors, reports);
  }
}

package com.yakovliam.taps.api.session.executor.earnusd;

import com.yakovliam.taps.api.model.App;
import com.yakovliam.taps.api.model.Game;
import com.yakovliam.taps.api.model.GameRepository;
import com.yakovliam.taps.api.model.Identity;
import com.yakovliam.taps.api.model.IdentityRepository;
import com.yakovliam.taps.api.remote.IdentityBalanceService;
import com.yakovliam.taps.api.session.EarnUSDSessionJobGoal;
import com.yakovliam.taps.api.session.GameSessionAuthStateManager;
import com.yakovliam.taps.api.session.SessionJob;
import com.yakovliam.taps.api.session.executor.SessionJobExecutor;
import com.yakovliam.taps.api.session.game.BlitzGameTaskStrategy;
import com.yakovliam.taps.api.session.game.CashFactoryGameTaskStrategy;
import com.yakovliam.taps.api.session.game.GameTaskContext;
import com.yakovliam.taps.api.session.game.PracticeV3GameTaskStrategy;
import com.yakovliam.taps.api.session.report.SessionJobError;
import com.yakovliam.taps.api.session.report.SessionJobExecutionReport;
import java.util.Collections;
import org.slf4j.Logger;

public class EarnUSDSessionJobExecutor extends SessionJobExecutor<EarnUSDSessionJobGoal> {

  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(EarnUSDSessionJobExecutor.class);

  private static final double USD_MINIMUM_TO_PLAY_BLITZ = 0.10;

  @Override
  public SessionJobExecutionReport execute(SessionJob<EarnUSDSessionJobGoal> job) {
    IdentityRepository identityRepository = IdentityRepository.getInstance();
    IdentityBalanceService identityBalanceService = IdentityBalanceService.getInstance();

    EarnUSDSessionJobGoal goal = job.getGoal();
    App app = job.getGoal().getApp();

    Identity randomIdentity =
        identityRepository.findAllByAppAndActiveJobIsNull(app).stream().findFirst().orElse(null);

    if (randomIdentity == null) {
      LOGGER.info("No available identities for job execution");
      return new SessionJobExecutionReport(goal, Collections.emptyList(), 0.0,
          Collections.singletonList(new SessionJobError("No available identities for job execution",
              new Throwable("No available identities for job execution"))));
    }

    // create game session auth state manager
    GameSessionAuthStateManager gameSessionAuthStateManager = new GameSessionAuthStateManager(randomIdentity);
    // start
    gameSessionAuthStateManager.start();

    // select brickbreaker game
    // TODO in the future, select a game based on some algorithm
    Game game = GameRepository.getInstance().findByAppEqualsAndUidIs(app, "brickbreaker");

    if (game == null) {
      LOGGER.info("No available apps for job execution");
      return new SessionJobExecutionReport(goal, Collections.emptyList(), 0.0,
          Collections.singletonList(new SessionJobError("No available apps for job execution",
              new Throwable("No available apps for job execution"))));
    }

    // TODO implement job execution logic here

    //////////////////////////

    // logic loop
    while (true) {
      // check the gem & USD balance of the identity
      IdentityBalanceService.IdentityBalanceWrapper balance =
          identityBalanceService.getBalance(randomIdentity);

      LOGGER.info("Identity balance: USD: {}, Gems: {}", balance.getUsdBalance(),
          balance.getGemBalance());

      // if balance is greater than the goal, break the loop
      if (balance.getUsdBalance() >= goal.getExpectedReturn()) {
        LOGGER.info("Balance is greater than the goal, breaking loop");
        break;
      }

      GameTaskContext gameTaskContext = new GameTaskContext(randomIdentity, game);

      // if the USD balance is greater than the minimum to play blitz
      if (balance.getUsdBalance() >= USD_MINIMUM_TO_PLAY_BLITZ) {
        // play a blitz game
        gameTaskContext.setStrategy(new BlitzGameTaskStrategy());
        LOGGER.info("Playing a blitz game");
      } else {
        // if the USD balance is less than the minimum to play blitz, need to
        // get more USD.
        // if gem balance is less than cash factory required, play practiceV3
        if (balance.getGemBalance() < 100) {
          gameTaskContext.setStrategy(new PracticeV3GameTaskStrategy());
          LOGGER.info("Playing a practiceV3 game");
        } else {
          gameTaskContext.setStrategy(new CashFactoryGameTaskStrategy());
          LOGGER.info("Playing a cash factory game");
        }

        gameTaskContext.executeStrategy();
      }
    }

    gameSessionAuthStateManager.stop();

    //////////////////////////

    LOGGER.info("Session Job executed successfully");


    return new SessionJobExecutionReport(goal, Collections.emptyList(), 0.0,
        Collections.emptyList());
  }

  @Override
  public Class<EarnUSDSessionJobGoal> getGoalType() {
    return EarnUSDSessionJobGoal.class;
  }
}

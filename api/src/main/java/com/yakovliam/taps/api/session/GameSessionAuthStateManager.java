package com.yakovliam.taps.api.session;

import com.yakovliam.taps.api.model.Identity;
import com.yakovliam.taps.api.util.JWTUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

public class GameSessionAuthStateManager {

  private static final org.slf4j.Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(GameSessionAuthStateManager.class);

  /**
   * The identity
   */
  private Identity identity;

  private List<Timer> timers = new ArrayList<>();

  public GameSessionAuthStateManager(Identity identity) {
    this.identity = identity;
  }

  public void start() {
    // if the token is already expired, refresh it immediately
    if (JWTUtil.isExpired(identity.getJwt())) {
      LOGGER.info("Identity token is expired. Refreshing immediately");
      identity = refreshToken().join();
    }

    LOGGER.info("Identity expires @ {}", JWTUtil.getExpiresAtInMs(identity.getJwt()));
    LOGGER.info("Current time: {}", System.currentTimeMillis());

    long tokenExpiresAt = JWTUtil.getExpiresAtInMs(identity.getJwt());
    long tokenExpirationTimeIntervalInMs = tokenExpiresAt - System.currentTimeMillis();
    // schedule a repeating task to refresh the token (but shift it forward by 5 minutes
    // to ensure we don't refresh too late)
    long refreshInterval = tokenExpirationTimeIntervalInMs - 5 * 60 * 1000;
    long expirationTime = JWTUtil.getExpirationTime(identity.getJwt());
    long msUntilFirstExpiration = expirationTime - System.currentTimeMillis();

    LOGGER.info("Identity refresh task started for identity: {}. Waiting {} ms until first refresh",
        identity.getUid(), msUntilFirstExpiration);

    // schedule the refresh task
    CompletableFuture.runAsync(() -> {
      LOGGER.info("Waiting for first refresh");
      try {
        Thread.sleep(msUntilFirstExpiration);
      } catch (InterruptedException e) {
        LOGGER.error("Error while waiting for first refresh", e);
      }

      Timer timer = new Timer();
      TimerTask task = new java.util.TimerTask() {
        @Override
        public void run() {
          LOGGER.info("Refreshing identity: {}", identity.getUid());
          refreshToken();
        }
      };

      timers.add(timer);

      // schedule the task
      timer.schedule(task, refreshInterval, refreshInterval);
    });

  }

  private CompletableFuture<Identity> refreshToken() {
    // refresh the token
    return TriumphServerAuthenticationWebRequestService.getInstance().refreshToken(identity)
        .thenApply(authRefreshResponseBody -> {
          System.out.println(
              "authRefreshResponseBody.idToken() = " + authRefreshResponseBody.idToken());
          // update the identity
          IdentityPersistenceService.getInstance()
              .updateIdentity(identity, authRefreshResponseBody.idToken(),
                  authRefreshResponseBody.refreshToken());

          return identity;
        });
  }

  public void stop() {
    timers.forEach(Timer::cancel);
  }
}

package com.yakovliam.taps.api.remote;

import com.yakovliam.taps.api.model.Identity;
import com.yakovliam.taps.api.model.internal.HistoryTournament;
import com.yakovliam.taps.api.model.internal.HistoryTournamentResult;
import com.yakovliam.taps.api.network.NetworkClient;
import com.yakovliam.taps.api.network.TriumphRequestBuilder;
import com.yakovliam.taps.api.network.history.HistoryRequest;
import com.yakovliam.taps.api.network.history.HistoryResponseBody;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;


public class IdentityBalanceService {

  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(IdentityBalanceService.class);

  private static IdentityBalanceService instance;

  public static IdentityBalanceService getInstance() {
    if (instance == null) {
      instance = new IdentityBalanceService();
    }
    return instance;
  }

  public IdentityBalanceWrapper getBalance(Identity identity) {
    HistoryRequest historyRequest = new HistoryRequest(identity.getDevice(), identity);
    CompletableFuture<HistoryResponseBody> future =
        NetworkClient.getInstance().send(TriumphRequestBuilder.getInstance().build(historyRequest),
            HistoryResponseBody.class);

    HistoryResponseBody historyResponseBody = future.join();

    if (Objects.isNull(historyResponseBody)) {
      LOGGER.error("Failed to retrieve history response body for identity: {}", identity.getId());
      return new IdentityBalanceWrapper(0.0, 0);
    }

    LOGGER.info("Retrieved history response body for identity: {}", identity.getId());

    HistoryTournament[] tournaments = historyResponseBody.getTournaments();

    LOGGER.info("Retrieved {} tournaments for identity: {}", tournaments.length, identity.getId());

    long usdCentsBalance = 0;
    long gemBalance = 0;

    for (HistoryTournament tournament : tournaments) {
      HistoryTournamentResult result =
          tournament.getResults().getOrDefault(identity.getUid(), null);
      if (result == null) {
        LOGGER.warn("No result found for identity: {} in tournament: {}", identity.getId(),
            tournament.getId());
        continue;
      }

      usdCentsBalance += result.getPayout();
      gemBalance += result.getPayoutGems();

      LOGGER.info("Payout for identity: {} in tournament: {} - USD: {}, Gems: {}", identity.getId(),
          tournament.getId(), result.getPayout(), result.getPayoutGems());
    }

    return new IdentityBalanceWrapper(usdCentsBalance / 100.0, (int) gemBalance);
  }

  public static class IdentityBalanceWrapper {
    private final double usdBalance;
    private final int gemBalance;

    public IdentityBalanceWrapper(double usdBalance, int gemBalance) {
      this.usdBalance = usdBalance;
      this.gemBalance = gemBalance;
    }

    public double getUsdBalance() {
      return usdBalance;
    }

    public int getGemBalance() {
      return gemBalance;
    }
  }
}

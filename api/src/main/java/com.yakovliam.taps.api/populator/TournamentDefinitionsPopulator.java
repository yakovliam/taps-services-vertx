package com.yakovliam.taps.api.populator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yakovliam.taps.api.model.App;
import com.yakovliam.taps.api.model.Game;
import com.yakovliam.taps.api.model.GameConfig;
import com.yakovliam.taps.api.model.GameConfigReward;
import com.yakovliam.taps.api.model.Identity;
import com.yakovliam.taps.api.model.IdentityCreator;
import com.yakovliam.taps.api.model.IdentityRepository;
import com.yakovliam.taps.api.model.internal.GameConfigType;
import com.yakovliam.taps.api.model.internal.Reward;
import com.yakovliam.taps.api.model.internal.TournamentDefinition;
import com.yakovliam.taps.api.network.NetworkClient;
import com.yakovliam.taps.api.network.TriumphRequestBuilder;
import com.yakovliam.taps.api.network.tournaments.TournamentDefinitionsRequest;
import com.yakovliam.taps.api.network.tournaments.TournamentDefinitionsResponseBody;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.slf4j.Logger;

// TODO implement a system of identities that are solely used for the purpose of making requests
//      to populate tournament definitions/other housekeeping data.
//      This is important to have a separation of powers.
//      If one of the game identities are compromised or banned, the housekeeping identities are not.
public class TournamentDefinitionsPopulator {

  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(TournamentDefinitionsPopulator.class);

  private static TournamentDefinitionsPopulator instance;

  public static TournamentDefinitionsPopulator getInstance() {
    if (instance == null) {
      instance = new TournamentDefinitionsPopulator();
    }
    return instance;
  }

  /**
   * Populates tournament definitions
   *
   * @param game app game related to the tournament definitions
   */
  public CompletableFuture<Set<GameConfig>> populateTournamentDefinitions(Game game) {
    App app = game.getApp();
    // populate tournament definitions
    Identity optionalIdentity =
        IdentityRepository.getInstance().getFirstByAppIs(app);

    Identity identity = optionalIdentity != null ? optionalIdentity :
        IdentityCreator.getInstance().create(app).join();
    LOGGER.info("Populating tournament definitions for app game: {}", game.getId());

    TournamentDefinitionsRequest request =
        new TournamentDefinitionsRequest(game.getUid(), identity.getDevice(), identity);
    CompletableFuture<TournamentDefinitionsResponseBody> response = NetworkClient.getInstance()
        .send(TriumphRequestBuilder.getInstance().build(request),
            TournamentDefinitionsResponseBody.class);

    return response.thenApply(d -> {
      LOGGER.info("Received tournament definitions for app game: {}", game.getId());
      TournamentDefinition[] definitions = d.getTournamentDefinitions();

      // print tournaments JSON
      for (TournamentDefinition definition : definitions) {
        try {
          String json = new ObjectMapper().writeValueAsString(definition);
          LOGGER.info("Tournament definition: {}", json);
        } catch (JsonProcessingException e) {
          throw new RuntimeException(e);
        }
      }

      Set<TournamentDefinition> tournamentDefinitions = Set.of(definitions);
      Set<GameConfig> gameConfigs = new HashSet<>();

      LOGGER.info("Received {} tournament definitions", tournamentDefinitions.size());

      for (TournamentDefinition tournamentDefinition : tournamentDefinitions) {
        GameConfig gameConfig = new GameConfig();
        gameConfig.setGame(game);
        gameConfig.setUid(tournamentDefinition.getUid());
        gameConfig.setName(tournamentDefinition.getName());
        gameConfig.setSize(tournamentDefinition.getSize());

        String type = tournamentDefinition.getType();
        GameConfigType gameConfigType = GameConfigType.fromString(type);

        if (gameConfigType == null) {
          LOGGER.error("Unknown tournament definition type: {}", type);
          continue;
        }

        gameConfig.setType(gameConfigType);

        // parse/process rewards
        Reward[] rewards = tournamentDefinition.getRewards();
        gameConfig.setRewards(parseRewards(rewards, gameConfig));

        gameConfigs.add(gameConfig);
      }

      LOGGER.info("Parsed {} app game configs", gameConfigs.size());

      // save app game configs, do overwrite if they already exist (check by game_id AND uid)
//      appGameConfigRepository.saveAll(appGameConfigs);

//      for (GameConfig gameConfig : gameConfigs) {
//        GameConfig existing = gameConfigRepository.findByGameAndUid(gameConfig.getGame(),
//            gameConfig.getUid());
//
//        if (existing != null) {
//          gameConfig.setId(existing.getId());
//        }
//
//        gameConfigRepository.save(gameConfig);
//      }

//      LOGGER.info("Saved {} app game configs", gameConfigs.size());
      return new HashSet<>(gameConfigs);
    });
  }

  private Set<GameConfigReward> parseRewards(Reward[] rewards, GameConfig gameConfig) {
    return Arrays.stream(rewards).map(reward -> {
      GameConfigReward gameConfigReward = new GameConfigReward();
      gameConfigReward.setGameConfig(gameConfig);
      gameConfigReward.setPayout(reward.getPayout());
      gameConfigReward.setPayoutGems(reward.getPayoutGems());
      gameConfigReward.setPayoutBonusCash(reward.getPayoutBonusCash());

      return gameConfigReward;
    }).collect(Collectors.toSet());
  }
}

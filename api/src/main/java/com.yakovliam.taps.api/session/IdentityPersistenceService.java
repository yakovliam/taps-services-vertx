package com.yakovliam.taps.api.session;

import com.yakovliam.taps.api.model.Identity;
import com.yakovliam.taps.api.model.IdentityRepository;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;

public class IdentityPersistenceService {

  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(IdentityPersistenceService.class);

  private static IdentityPersistenceService instance;

  public static IdentityPersistenceService getInstance() {
    if (instance == null) {
      instance = new IdentityPersistenceService();
    }
    return instance;
  }

  public CompletableFuture<Identity> updateIdentity(Identity identity, String jwt,
                                                    String refreshToken) {

    return CompletableFuture.supplyAsync(() -> {
      identity.setJwt(jwt);
      identity.setRefreshToken(refreshToken);

      LOGGER.info("Updating identity in database: {}", identity.getUid());
      return IdentityRepository.getInstance().save(identity);
    });
  }

}

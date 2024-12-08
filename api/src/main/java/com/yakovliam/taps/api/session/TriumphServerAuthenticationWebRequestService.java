package com.yakovliam.taps.api.session;

import static com.yakovliam.taps.api.util.JWTUtil.isExpired;
import static com.yakovliam.taps.api.util.JWTUtil.isValid;

import com.yakovliam.taps.api.model.Identity;
import com.yakovliam.taps.api.network.auth.AuthRefreshRequest;
import com.yakovliam.taps.api.network.auth.AuthRefreshRequestBody;
import com.yakovliam.taps.api.network.auth.AuthRefreshResponseBody;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;

public class TriumphServerAuthenticationWebRequestService extends TriumphServerWebRequestService {

  private static TriumphServerAuthenticationWebRequestService instance;

  public static TriumphServerAuthenticationWebRequestService getInstance() {
    if (instance == null) {
      instance = new TriumphServerAuthenticationWebRequestService();
    }
    return instance;
  }

  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(TriumphServerAuthenticationWebRequestService.class);

  public CompletableFuture<AuthRefreshResponseBody> refreshToken(Identity identity) {
    if (!isValid(identity.getJwt())) {
      LOGGER.error("Invalid JWT token");
      throw new IllegalArgumentException("Invalid JWT token");
    }

    // check if it's necessary to refresh the token
    if (!isExpired(identity.getJwt())) {
      LOGGER.info("JWT token is not expired");
      return CompletableFuture.completedFuture(null);
    }

    AuthRefreshRequestBody body = new AuthRefreshRequestBody(identity.getRefreshToken(),
        identity.getDevice().getDeviceToken());
    AuthRefreshRequest request = new AuthRefreshRequest(body, identity.getDevice(), identity);

    return sendRequest(request, AuthRefreshResponseBody.class);
  }
}

package com.yakovliam.taps.api.cookie;

import java.util.Optional;

public class GoogleAppEngineSessionAffinityCookieService {

  /**
   * The Google app engine session affinity cookie
   * <p>
   * The cookie is used to maintain session affinity.
   * It is set by the Google app engine load balancer, usually upon auth refresh.
   * It is null if the cookie is not present.
   */
  private String cookie;

  /**
   * Google app engine session affinity cookie service
   *
   * @param cookie the cookie
   */
  public GoogleAppEngineSessionAffinityCookieService(String cookie) {
    this.cookie = cookie;
  }

  public GoogleAppEngineSessionAffinityCookieService() {
  }

  public Optional<String> cookie() {
    return Optional.ofNullable(cookie);
  }

  public void cookie(String cookie) {
    this.cookie = cookie;
  }
}

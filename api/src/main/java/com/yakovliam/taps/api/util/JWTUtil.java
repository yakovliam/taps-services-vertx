package com.yakovliam.taps.api.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import java.util.Date;

public class JWTUtil {

  /**
   * Checks if the token is valid
   *
   * @param token the token
   * @return if the token is valid
   */
  public static boolean isValid(String token) {
    try {
      JWT.decode(token);
      return true;
    } catch (JWTDecodeException e) {
      return false;
    }
  }


  /**
   * Checks if the token is expired
   *
   * @param token                 the token
   * @param expiredMinutesFromNow the amount of minutes from now the token should be expired
   * @return if the token is expired
   */
  public static boolean isExpired(String token, int expiredMinutesFromNow) {
    if (!isValid(token)) {
      throw new IllegalArgumentException("Invalid token");
    }
    return JWT.decode(token).getExpiresAt()
        .before(new Date(System.currentTimeMillis() + (long) expiredMinutesFromNow * 60 * 1000));
  }

  /**
   * Checks if the token is expired
   *
   * @param token the token
   * @return if the token is expired
   */
  public static boolean isExpired(String token) {
    return isExpired(token, 0);
  }

  /**
   * Gets the expiration time interval in milliseconds
   * <p>
   * This is primarily used for calculating the time until the token expires
   * so we can schedule a refresh
   *
   * @param token the token
   * @return the expiration time interval in milliseconds
   */
  public static long getExpirationTimeIntervalInMs(String token) {
    if (!isValid(token)) {
      throw new IllegalArgumentException("Invalid token");
    }
    return JWT.decode(token).getExpiresAt().getTime() - System.currentTimeMillis();
  }

  /**
   * Gets the expiration time of the token
   *
   * @param token the token
   * @return the expiration time of the token
   */
  public static long getExpiresAtInMs(String token) {
    if (!isValid(token)) {
      throw new IllegalArgumentException("Invalid token");
    }
    return JWT.decode(token).getExpiresAt().getTime();
  }

  /**
   * Gets the expiration time of the token
   *
   * @param token the token
   * @return the expiration time of the token
   */
  public static long getExpirationTime(String token) {
    if (!isValid(token)) {
      throw new IllegalArgumentException("Invalid token");
    }
    return JWT.decode(token).getExpiresAt().getTime();
  }
}

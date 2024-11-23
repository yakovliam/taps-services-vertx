package com.yakovliam.taps.api.util;

import java.security.SecureRandom;

public class TriumphDeviceTokenGeneratorUtil {

  private static final int LENGTH = 1498;

  public static String generateDeviceToken() {
    // generate a random base64 string, always ending with a '='
    SecureRandom random = new SecureRandom();
    // -1 to ensure the string is always 2995 characters long, and then we add a '='
    // to the end to make it 2996
    byte[] bytes = new byte[LENGTH - 1];
    random.nextBytes(bytes);
    return java.util.Base64.getEncoder().encodeToString(bytes) + "=";
  }
}

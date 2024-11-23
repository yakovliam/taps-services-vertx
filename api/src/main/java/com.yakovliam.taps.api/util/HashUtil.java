package com.yakovliam.taps.api.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

  public static String sha256(String input) {
    MessageDigest digest = null;
    try {
      digest = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
    // write to string
    StringBuilder hexString = new StringBuilder(2 * hash.length);
    for (byte b : hash) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString().toLowerCase();
  }
}

package com.yakovliam.taps.api.signature.triumph;

import static com.yakovliam.taps.api.util.HashUtil.sha256;

import com.yakovliam.taps.api.signature.SignatureGenerator;
import org.slf4j.Logger;

/**
 * Triumph signature generator
 * <p>
 * This signature generator works as follows:
 * - First, a signature input needs to be created
 * - The input is this: '[body][joiner][nonce]'
 * - The joiner is a string used to separate the body and nonce; it's two characters long
 * - E.g. {some_json}0z{nonce}
 * - The default joiner is 'aa'
 * - The first character of the joiner is replaced with the lowest number found in the body
 * if a number is found.
 * - The second character of the joiner is replaced with the highest alphabetical character
 */
public class TriumphSignatureGenerator
    implements SignatureGenerator<TriumphSignatureGeneratorContext> {

  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(TriumphSignatureGenerator.class);

  private static final String DEFAULT_JOINER = "aa";

  @Override
  public String generate(TriumphSignatureGeneratorContext context) {
    String nonce = context.nonce().toString().toUpperCase();
    String body = context.body();
    String joiner = generateJoiner(body);

    String hashInput = body + joiner + nonce;

    return sha256(hashInput);
  }

  private static String generateJoiner(String body) {
    // if the body is empty or '{}', return ""
    if (body == null || body.isEmpty() || body.equals("{}")) {
      return "";
    }

    // remove all non-alphanumeric characters
//    body = body.replaceAll("[^a-zA-Z0-9]", "");

    LOGGER.info("Body: {}", body);

    char[] joiner = new char[2];
    joiner[0] = DEFAULT_JOINER.charAt(0);
    joiner[1] = DEFAULT_JOINER.charAt(1);

    // if the body has a number, find the lowest number
    if (body.chars().anyMatch(Character::isDigit)) {
      // get the lowest number
      int lowestNumber = body.chars().filter(Character::isDigit).min().orElse(-1);
      if (lowestNumber == -1) {
        throw new IllegalArgumentException(
            "Something went wrong while finding the lowest number. -1 should not be possible.");
      }

      // replace the first character of the joiner with the lowest number
      joiner[0] = (char) lowestNumber;
    }

    // get the highest alphabetical character
    int highestAlphabetical = body.chars().filter(Character::isAlphabetic).max().orElse(-1);
    char highestAlphabeticalChar = (char) highestAlphabetical;

    // replace the second character of the joiner with the highest alphabetical character
    joiner[1] = highestAlphabeticalChar;

    return new String(joiner);
  }

  private static TriumphSignatureGenerator instance;

  public static TriumphSignatureGenerator getInstance() {
    if (instance == null) {
      instance = new TriumphSignatureGenerator();
    }
    return instance;
  }
}

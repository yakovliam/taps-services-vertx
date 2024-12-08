package com.yakovliam.taps.api.util;

import com.yakovliam.taps.api.Noop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UidUtil {

  private static final Set<String> words = loadWords();

  private static Set<String> loadWords() {
    // load words
    InputStream inputStream = Noop.class.getResourceAsStream("/words.txt");
    if (inputStream == null) {
      throw new RuntimeException("Could not load words.txt");
    }

    Set<String> words = new HashSet<>();

    // read lines
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // replace any non-alphabetic characters with empty string
        line = line.replaceAll("[^a-zA-Z]", "");
        words.add(line);
      }
    } catch (IOException e) {
      throw new RuntimeException("Could not read words.txt", e);
    }

    return words;
  }

  private static final int NUM_WORDS_IN_PHRASE = 3;

  public static String generateRandomUUIDString() {
    return generateRandomUUID().toString();
  }

  public static UUID generateRandomUUID() {
    return UUID.randomUUID();
  }

  private static String getRandomWord() {
    int randomIndex = (int) (Math.random() * words.size());
    int i = 0;
    for (String word : words) {
      if (i == randomIndex) {
        return word;
      }
      i++;
    }
    return null;
  }

  public static String generateRandomPhraseUid() {
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < NUM_WORDS_IN_PHRASE; i++) {
      // get random word
      String word = getRandomWord();

      // append
      builder.append(word);

      // if not last word, append a dash
      if (i != NUM_WORDS_IN_PHRASE - 1) {
        builder.append("-");
      }
    }

    return builder.toString();
  }
}

package com.yakovliam.taps.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
public class JsonSerializableObject {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  /**
   * Write the body to a string.
   * <p>
   * Traditionally without spaces or new lines.
   *
   * @return the string
   */
  public String write() {
    try {
      String output = OBJECT_MAPPER.writeValueAsString(this);
      // remove all spaces
      output = output.replaceAll("\\s+", "");
      // remove all new lines
      output = output.replaceAll("\n", "");
      // remove all tabs
      output = output.replaceAll("\t", "");
      return output;
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to write object to string", e);
    }
  }

  public static JsonSerializableObject empty() {
    return new JsonSerializableObject();
  }
}

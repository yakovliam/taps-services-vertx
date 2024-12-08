package com.yakovliam.taps.api.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize
public class JsonSerializableObject {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @JsonIgnore
  private Map<String, Object> dynamicValues = new LinkedHashMap<>();

  @JsonAnySetter
  public void ignored(String name, Object value) {
    dynamicValues.put(name, value);
  }

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

  public String writeDynamicValues() {
    try {
      return OBJECT_MAPPER.writeValueAsString(dynamicValues);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Failed to write dynamic values to string", e);
    }
  }

  public static JsonSerializableObject empty() {
    return new JsonSerializableObject();
  }
}

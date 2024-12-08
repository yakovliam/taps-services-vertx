package com.yakovliam.taps.api.model.internal;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public enum GameConfigType {
  ASYNC_GROUP_V1("async-group"), ASYNC_GROUP_V3("async-group-v3");

  private String typeId;

  GameConfigType(String typeId) {
    this.typeId = typeId;
  }

  public String typeId() {
    return this.typeId;
  }

  public static GameConfigType fromString(String typeId) {
    for (GameConfigType value : GameConfigType.values()) {
      if (value.typeId().equalsIgnoreCase(typeId)) {
        return value;
      }
    }

    return null;
  }

  public static class Deserializer extends JsonDeserializer<GameConfigType> {

    @Override
    public GameConfigType deserialize(JsonParser jsonParser,
                                      DeserializationContext deserializationContext)
        throws IOException, JacksonException {
      return GameConfigType.fromString(jsonParser.getText());
    }
  }
}

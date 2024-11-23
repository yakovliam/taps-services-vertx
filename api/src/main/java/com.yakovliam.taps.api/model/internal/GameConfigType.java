package com.yakovliam.taps.api.model.internal;

public enum GameConfigType {
  ASYNC_GROUP_V1("async-group"), ASYNC_GROUP_V3("ASYNC_GROUP_V3");

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
}

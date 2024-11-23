package com.yakovliam.taps.api.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;

public class TournamentDefinition extends JsonSerializableObject {

  @JsonProperty("uid")
  private String uid;

  @JsonProperty("type")
  private String type;

  @JsonProperty("name")
  private String name;

  @JsonProperty("entryPrice")
  private int entryPrice;

  @JsonProperty("entryGems")
  private int entryGems;

  @JsonProperty("archived")
  private boolean archived;

  @JsonProperty("version")
  private String version;

  @JsonProperty("size")
  private int size;

  @JsonProperty("createdAt")
  private long createdAt;

  @JsonProperty("priority")
  private int priority;

  @JsonProperty("softArchived")
  private boolean softArchived;

  @JsonProperty("updatedAt")
  private long updatedAt;

  @JsonProperty("rewards")
  private Reward[] rewards;

//  @JsonProperty("VIPGroups")
//  private Object vipGroups;


  public TournamentDefinition(String uid, String type, String name, int entryPrice, int entryGems,
                              boolean archived, String version, int size, long createdAt,
                              int priority, boolean softArchived, long updatedAt,
                              Reward[] rewards) {
    this.uid = uid;
    this.type = type;
    this.name = name;
    this.entryPrice = entryPrice;
    this.entryGems = entryGems;
    this.archived = archived;
    this.version = version;
    this.size = size;
    this.createdAt = createdAt;
    this.priority = priority;
    this.softArchived = softArchived;
    this.updatedAt = updatedAt;
    this.rewards = rewards;
  }

  public TournamentDefinition() {
  }

  public String getUid() {
    return uid;
  }

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public int getEntryPrice() {
    return entryPrice;
  }

  public int getEntryGems() {
    return entryGems;
  }

  public boolean isArchived() {
    return archived;
  }

  public String getVersion() {
    return version;
  }

  public int getSize() {
    return size;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public int getPriority() {
    return priority;
  }

  public boolean isSoftArchived() {
    return softArchived;
  }

  public long getUpdatedAt() {
    return updatedAt;
  }

  public Reward[] getRewards() {
    return rewards;
  }
}

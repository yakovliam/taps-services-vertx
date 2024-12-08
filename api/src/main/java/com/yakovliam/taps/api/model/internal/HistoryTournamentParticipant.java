package com.yakovliam.taps.api.model.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yakovliam.taps.api.model.JsonSerializableObject;

public class HistoryTournamentParticipant extends JsonSerializableObject {

  @JsonProperty("uid")
  private String uid;

  @JsonProperty("type")
  private GameConfigType type;

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

  @JsonProperty("VIPGroups")
  private Object VIPGroups;

  public HistoryTournamentParticipant() {
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public GameConfigType getType() {
    return type;
  }

  public void setType(GameConfigType type) {
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getEntryPrice() {
    return entryPrice;
  }

  public void setEntryPrice(int entryPrice) {
    this.entryPrice = entryPrice;
  }

  public int getEntryGems() {
    return entryGems;
  }

  public void setEntryGems(int entryGems) {
    this.entryGems = entryGems;
  }

  public boolean isArchived() {
    return archived;
  }

  public void setArchived(boolean archived) {
    this.archived = archived;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(long createdAt) {
    this.createdAt = createdAt;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public boolean isSoftArchived() {
    return softArchived;
  }

  public void setSoftArchived(boolean softArchived) {
    this.softArchived = softArchived;
  }

  public long getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(long updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Reward[] getRewards() {
    return rewards;
  }

  public void setRewards(Reward[] rewards) {
    this.rewards = rewards;
  }

  public Object getVIPGroups() {
    return VIPGroups;
  }

  public void setVIPGroups(Object VIPGroups) {
    this.VIPGroups = VIPGroups;
  }
}


package com.yakovliam.taps.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

@Entity(name = "App")
@Table(name = "app")
public class App {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "uid")
  private String uid;

  @Column(name = "app_version")
  private String appVersion;

  @Column(name = "sdk_version")
  private String sdkVersion;

  @Column(name = "user_agent")
  private String userAgent;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "app", fetch = FetchType.EAGER)
  private Set<Game> games;

  @OneToMany(mappedBy = "app", fetch = FetchType.EAGER)
  private Set<Identity> identities;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getAppVersion() {
    return appVersion;
  }

  public void setAppVersion(String appVersion) {
    this.appVersion = appVersion;
  }

  public String getSdkVersion() {
    return sdkVersion;
  }

  public void setSdkVersion(String sdkVersion) {
    this.sdkVersion = sdkVersion;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Game> getGames() {
    return games;
  }

  public void setGames(Set<Game> games) {
    this.games = games;
  }

  public Set<Identity> getIdentities() {
    return identities;
  }

  public void setIdentities(Set<Identity> identities) {
    this.identities = identities;
  }
}

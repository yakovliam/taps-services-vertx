package com.yakovliam.taps.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "identity")
public class Identity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "app_id")
  private App app;

  @ManyToOne
  @JoinColumn(name = "active_job_id")
  private Job activeJob;

  @Column(name = "uid")
  private String uid;

  @Column(name = "jwt")
  private String jwt;

  @Column(name = "refresh_token")
  private String refreshToken;

  @OneToOne
  @JoinColumn(name = "device_id")
  private Device device;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public App getApp() {
    return app;
  }

  public void setApp(App app) {
    this.app = app;
  }

  public Job getActiveJob() {
    return activeJob;
  }

  public void setActiveJob(Job activeJob) {
    this.activeJob = activeJob;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public Device getDevice() {
    return device;
  }

  public void setDevice(Device device) {
    this.device = device;
  }
}

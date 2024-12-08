package com.yakovliam.taps.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "job")
public class Job {

  @Id
  private Long id;

  @Column(name = "goal_name")
  private String goalName;

  @OneToMany(mappedBy = "activeJob")
  private Set<Identity> activeIdentities;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGoalName() {
    return goalName;
  }

  public void setGoalName(String goalName) {
    this.goalName = goalName;
  }

  public Set<Identity> getActiveIdentities() {
    return activeIdentities;
  }

  public void setActiveIdentities(Set<Identity> activeIdentities) {
    this.activeIdentities = activeIdentities;
  }
}

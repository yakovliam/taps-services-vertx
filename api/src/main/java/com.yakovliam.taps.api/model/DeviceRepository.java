package com.yakovliam.taps.api.model;

import jakarta.persistence.EntityManagerFactory;

public class DeviceRepository extends GenericDao<Device, Long> {

  private static DeviceRepository instance;

  public static void init(EntityManagerFactory entityManagerFactory) {
    instance = new DeviceRepository(entityManagerFactory);
  }

  public static DeviceRepository getInstance() {
    return instance;
  }

  public DeviceRepository(EntityManagerFactory entityManagerFactory) {
    super(entityManagerFactory);
  }
}

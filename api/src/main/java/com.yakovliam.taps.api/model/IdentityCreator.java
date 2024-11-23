package com.yakovliam.taps.api.model;

import com.yakovliam.taps.api.network.NetworkClient;
import com.yakovliam.taps.api.network.TriumphRequestBuilder;
import com.yakovliam.taps.api.network.user.AnonUserRequest;
import com.yakovliam.taps.api.network.user.AnonUserRequestBody;
import com.yakovliam.taps.api.network.user.AnonUserResponseBody;
import com.yakovliam.taps.api.util.TriumphDeviceTokenGeneratorUtil;
import com.yakovliam.taps.api.util.UidUtil;
import java.util.concurrent.CompletableFuture;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

public class IdentityCreator {

  private static IdentityCreator instance;

  public static IdentityCreator getInstance() {
    if (instance == null) {
      instance = new IdentityCreator();
    }
    return instance;
  }

  private Identity create(Identity identity) {
    return IdentityRepository.getInstance().save(identity);
  }

  public CompletableFuture<Identity> create(App app) {
    // dayton, ohio. randomly chosen
    Point location =
        new GeometryFactory().createPoint(new Coordinate(-84.19459345019726, 39.759357828755654));
    Device newDevice = new Device();
    newDevice.setDeviceToken(TriumphDeviceTokenGeneratorUtil.generateDeviceToken());
    newDevice.setDeviceId(UidUtil.generateRandomUUID());
    newDevice.setUid(
        UidUtil.generateRandomPhraseUid()); // TODO have a unique check in case of collision
    newDevice.setLocation(location);

    Device device = DeviceRepository.getInstance().save(newDevice);

    AnonUserRequest anonUserRequest =
        new AnonUserRequest(new AnonUserRequestBody(device.getDeviceToken()), device, app);
    return NetworkClient.getInstance()
        .send(TriumphRequestBuilder.getInstance().build(anonUserRequest),
            AnonUserResponseBody.class).thenApply(response -> {
          Identity identity = new Identity();
          identity.setApp(app);
          identity.setDevice(device);
          identity.setUid(response.userId());
          identity.setJwt(response.idToken());
          identity.setRefreshToken(response.refreshToken());

          return create(identity);
        });
  }
}

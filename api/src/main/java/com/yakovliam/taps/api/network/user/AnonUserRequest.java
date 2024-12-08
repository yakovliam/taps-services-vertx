package com.yakovliam.taps.api.network.user;

import com.yakovliam.taps.api.model.App;
import com.yakovliam.taps.api.model.Device;
import com.yakovliam.taps.api.model.RequestMethod;
import com.yakovliam.taps.api.network.RequestAttribute;
import com.yakovliam.taps.api.network.TriumphRequest;
import java.util.Map;

public class AnonUserRequest extends TriumphRequest {

  private final App app;

  public AnonUserRequest(AnonUserRequestBody body, Device device, App app) {
    super(RequestMethod.POST, "users/v2/anon_user", body, device);
    this.app = app;
  }

  @Override
  public Map<RequestAttribute, Object> getAttributes() {
    Map<RequestAttribute, Object> attributes = super.getAttributes();
    attributes.put(RequestAttribute.USE_SIGNATURE, true);
    attributes.put(RequestAttribute.INCLUDE_LOCATION, false);
    attributes.put(RequestAttribute.APP, app);
    return attributes;
  }
}

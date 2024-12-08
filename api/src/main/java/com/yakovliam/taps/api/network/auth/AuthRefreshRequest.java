package com.yakovliam.taps.api.network.auth;

import com.yakovliam.taps.api.model.Device;
import com.yakovliam.taps.api.model.Identity;
import com.yakovliam.taps.api.model.RequestMethod;
import com.yakovliam.taps.api.network.RequestAttribute;
import com.yakovliam.taps.api.network.TriumphRequest;
import java.util.Map;

public class AuthRefreshRequest extends TriumphRequest {

  private final Identity identity;

  public AuthRefreshRequest(AuthRefreshRequestBody body, Device device, Identity identity) {
    super(RequestMethod.POST, "auth/refresh", body, device);
    this.identity = identity;
  }

  @Override
  public Map<RequestAttribute, Object> getAttributes() {
    Map<RequestAttribute, Object> attributes = super.getAttributes();
    attributes.put(RequestAttribute.INCLUDE_FIREBASE_ID_TOKEN, false);
    attributes.put(RequestAttribute.INCLUDE_TRIUMPH_ID_TOKEN, true);
    attributes.put(RequestAttribute.IDENTITY, identity);
    attributes.put(RequestAttribute.APP, identity.getApp());

    return attributes;
  }
}

package com.yakovliam.taps.api.network.history;

import com.yakovliam.taps.api.model.Device;
import com.yakovliam.taps.api.model.Identity;
import com.yakovliam.taps.api.model.JsonSerializableObject;
import com.yakovliam.taps.api.model.RequestMethod;
import com.yakovliam.taps.api.network.RequestAttribute;
import com.yakovliam.taps.api.network.TriumphRequest;
import java.util.Map;

public final class HistoryRequest extends TriumphRequest {

  private final Identity identity;

  public HistoryRequest(Device device, Identity identity) {
    super(RequestMethod.POST, "history", JsonSerializableObject.empty(), device);
    this.identity = identity;
  }

  @Override
  public Map<RequestAttribute, Object> getAttributes() {
    Map<RequestAttribute, Object> attributes = super.getAttributes();
    attributes.put(RequestAttribute.INCLUDE_TRIUMPH_ID_TOKEN, true);
    attributes.put(RequestAttribute.INCLUDE_FIREBASE_ID_TOKEN, true);
    attributes.put(RequestAttribute.IDENTITY, identity);
    attributes.put(RequestAttribute.APP, identity.getApp());

    return attributes;
  }
}

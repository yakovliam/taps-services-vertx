package com.yakovliam.taps.api.network;

import static com.yakovliam.taps.api.network.RequestAttribute.USE_SIGNATURE;
import static com.yakovliam.taps.api.network.TriumphConstants.TRIUMPH_BASE_URL;

import com.yakovliam.taps.api.model.Device;
import com.yakovliam.taps.api.model.JsonSerializableObject;
import com.yakovliam.taps.api.model.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public abstract class TriumphRequest extends Request {

  /**
   * Device
   */
  private final Device device;

  /**
   * Request
   */
  protected TriumphRequest(RequestMethod method, String path, JsonSerializableObject body,
                           Device device) {
    super(method, TRIUMPH_BASE_URL, path, body);
    this.device = device;
  }

  protected TriumphRequest(RequestMethod method, String path, Device device) {
    super(method, TRIUMPH_BASE_URL, path);
    this.device = device;
  }

  @Override
  public Map<RequestAttribute, Object> getAttributes() {
    return new HashMap<>(Map.of(USE_SIGNATURE, true, RequestAttribute.DEVICE, device));
  }
}

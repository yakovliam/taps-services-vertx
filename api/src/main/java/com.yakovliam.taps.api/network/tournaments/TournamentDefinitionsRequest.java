package com.yakovliam.taps.api.network.tournaments;

import com.yakovliam.taps.api.model.Device;
import com.yakovliam.taps.api.model.Identity;
import com.yakovliam.taps.api.model.RequestMethod;
import com.yakovliam.taps.api.network.RequestAttribute;
import com.yakovliam.taps.api.network.TriumphRequest;
import java.util.Map;

public class TournamentDefinitionsRequest extends TriumphRequest {

  /**
   * Game ID
   */
  private final String gameId;

  /**
   * Identity
   */
  private final Identity identity;

  /**
   * Request
   *
   * @param gameId the game id
   */
  public TournamentDefinitionsRequest(String gameId, Device device, Identity identity) {
    super(RequestMethod.GET, "tournaments/async_group/v3/definitions", device);
    this.gameId = gameId;
    this.identity = identity;
  }

  @Override
  public Map<RequestAttribute, Object> getAttributes() {
    Map<RequestAttribute, Object> attributes = super.getAttributes();
    attributes.put(RequestAttribute.INCLUDE_GAME_ID, true);
    attributes.put(RequestAttribute.INCLUDE_TRIUMPH_ID_TOKEN, true);
    attributes.put(RequestAttribute.INCLUDE_FIREBASE_ID_TOKEN, true);
    attributes.put(RequestAttribute.GAME_ID, gameId);
    attributes.put(RequestAttribute.IDENTITY, identity);
    attributes.put(RequestAttribute.APP, identity.getApp());
    return attributes;
  }
}

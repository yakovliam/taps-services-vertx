package com.yakovliam.taps.api.session;

import com.yakovliam.taps.api.model.JsonSerializableObject;
import com.yakovliam.taps.api.network.NetworkClient;
import com.yakovliam.taps.api.network.TriumphRequest;
import com.yakovliam.taps.api.network.TriumphRequestBuilder;
import java.util.concurrent.CompletableFuture;

public abstract class TriumphServerWebRequestService {

  protected <T extends JsonSerializableObject> CompletableFuture<T> sendRequest(
      TriumphRequest request, Class<T> responseClass) {
    return NetworkClient.getInstance()
        .send(TriumphRequestBuilder.getInstance().build(request), responseClass);
  }
}

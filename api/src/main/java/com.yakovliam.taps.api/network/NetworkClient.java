package com.yakovliam.taps.api.network;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.yakovliam.taps.api.model.JsonSerializableObject;
import com.yakovliam.taps.api.model.internal.GameConfigType;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;

public class NetworkClient {

  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(NetworkClient.class);

  private static final ObjectMapper MAPPER = new ObjectMapper();

  static {
    // register deserializers
    MAPPER.registerModule(new SimpleModule().addDeserializer(GameConfigType.class,
        new GameConfigType.Deserializer()));
  }

  private static final OkHttpClient CLIENT =
      new OkHttpClient().newBuilder().followRedirects(false).followSslRedirects(false).proxy(null)
          .retryOnConnectionFailure(false).callTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
          .build();

  private static NetworkClient instance;

  public static NetworkClient getInstance() {
    if (instance == null) {
      instance = new NetworkClient();
    }
    return instance;
  }

  /**
   * Sends a request
   *
   * @param request the request
   */
  public void sendWithHandler(Request request, Consumer<Response> responseHandler) {
    LOGGER.info("Sending request to: {}", request.url());

    try {
      okhttp3.Response response = CLIENT.newCall(request).execute();

      // handle the response
      responseHandler.accept(response);
    } catch (Exception e) {
      LOGGER.error("Failed to send request", e);
    }
  }

  public CompletableFuture<Void> sendWithoutResponse(Request request) {
    CompletableFuture<Void> future = new CompletableFuture<>();
    sendWithHandler(request, response -> {
      if (response == null) {
        future.completeExceptionally(new Exception("Response is null"));
        return;
      }

      if (!response.isSuccessful()) {
        future.completeExceptionally(new Exception("Request failed with code: " + response.code()));
        return;
      }

      future.complete(null);
    });

    return future;
  }


  public <S extends JsonSerializableObject> CompletableFuture<S> send(Request request,
                                                                      Class<S> responseType) {

    CompletableFuture<S> future = new CompletableFuture<>();
    sendWithHandler(request, response -> {
      if (response == null) {
        future.completeExceptionally(new Exception("Response is null"));
        return;
      }

      if (!response.isSuccessful()) {
        Optional<ResponseBody> body = Optional.ofNullable(response.body());
        body.ifPresentOrElse(responseBody -> {
          try {
            LOGGER.info("Response body: {}", responseBody.string());
          } catch (IOException e) {
            LOGGER.error("Failed to read response body", e);
          }
        }, () -> LOGGER.info("Response body is null"));
        future.completeExceptionally(new Exception("Request failed with code: " + response.code()));
        return;
      }

      if (responseType == null) {
        future.complete(null);
        return;
      }

      okhttp3.ResponseBody body = response.body();

      // TODO: implement the above, correctly...
      try {
        if (body == null) {
          future.completeExceptionally(new Exception("Response body is null"));
          return;
        }

        String bodyString = body.string();

        if (bodyString.isEmpty()) {
          future.completeExceptionally(new Exception("Response body is empty"));
          return;
        }

        S responseObj = MAPPER.readValue(bodyString, responseType);
        future.complete(responseObj);
      } catch (Exception e) {
        LOGGER.error("Failed to parse response", e);
        future.completeExceptionally(e);
      }
    });

    return future;
  }
}

package com.yakovliam.taps.api.network;

import static com.yakovliam.taps.api.network.TriumphConstants.APPSFLYER_ID;
import static com.yakovliam.taps.api.network.TriumphConstants.TRIUMPH_SIGNATURE_KEY;

import com.yakovliam.taps.api.cookie.TriumphGoogleAppEngineSessionAffinityCookieService;
import com.yakovliam.taps.api.model.App;
import com.yakovliam.taps.api.model.Device;
import com.yakovliam.taps.api.model.Identity;
import com.yakovliam.taps.api.model.JsonSerializableObject;
import com.yakovliam.taps.api.model.NoncableUUID;
import com.yakovliam.taps.api.signature.triumph.TriumphSignatureGenerator;
import com.yakovliam.taps.api.signature.triumph.TriumphSignatureGeneratorContext;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.locationtech.jts.geom.Point;
import org.slf4j.Logger;

/**
 * Triumph request builder
 * <p>
 * Builds a network request for the Triumph API
 */
public class TriumphRequestBuilder extends RequestBuilder<TriumphRequest> {

  private static TriumphRequestBuilder instance;

  public static TriumphRequestBuilder getInstance() {
    if (instance == null) {
      instance = new TriumphRequestBuilder();
    }
    return instance;
  }

  private static final Logger LOGGER =
      org.slf4j.LoggerFactory.getLogger(TriumphRequestBuilder.class);

  public static class OkHttpRequestBuilder {
    private Request.Builder okHttpRequestBuilder;

    public OkHttpRequestBuilder() {
      this.okHttpRequestBuilder = new Request.Builder();
    }

    public OkHttpRequestBuilder url(String url) {
      this.okHttpRequestBuilder = this.okHttpRequestBuilder.url(url);
      return this;
    }

    public OkHttpRequestBuilder get() {
      this.okHttpRequestBuilder = this.okHttpRequestBuilder.get();
      return this;
    }

    public OkHttpRequestBuilder head() {
      this.okHttpRequestBuilder = this.okHttpRequestBuilder.head();
      return this;
    }

    public OkHttpRequestBuilder method(String method, RequestBody body) {
      this.okHttpRequestBuilder = this.okHttpRequestBuilder.method(method, body);
      return this;
    }

    public OkHttpRequestBuilder header(String name, String value) {
      this.okHttpRequestBuilder = this.okHttpRequestBuilder.header(name, value);
      return this;
    }

    public OkHttpRequestBuilder addHeader(String name, String value) {
      this.okHttpRequestBuilder = this.okHttpRequestBuilder.addHeader(name, value);
      return this;
    }

    public Request build() {
      return this.okHttpRequestBuilder.build();
    }
  }

  @Override
  public Request build(TriumphRequest input) {
    App app = (App) input.getAttributes().getOrDefault(RequestAttribute.APP, null);

    if (app == null) {
      throw new IllegalStateException("App is required to build a Triumph request");
    }

    NoncableUUID nonce = NoncableUUID.from(UUID.randomUUID());

    OkHttpRequestBuilder builder = new OkHttpRequestBuilder();
    builder.url(input.getBaseUrl() + "/" + input.getPath());
    Optional<JsonSerializableObject> body = Optional.ofNullable(input.getBody());

    switch (input.getMethod()) {
      case GET:
        builder.get();
        break;
      case HEAD:
        builder.head();
        break;
      case OPTIONS, TRACE, PATCH, DELETE, PUT, POST:
        body.ifPresent(jsonSerializableObject -> {
          builder.method(input.getMethod().name(),
              RequestBody.create(jsonSerializableObject.write().getBytes(StandardCharsets.UTF_8)));
        });
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + input.getMethod());
    }

    Device device = getDevice(input).orElseThrow(
        () -> new IllegalStateException("Device is required to build a Triumph request"));

    builder.header("App-Id", app.getUid()).header("Accept", "*/*") // TODO: this may be dynamic
        .header("App-Version", app.getAppVersion()).header("Nonce", nonce.toString())
        .header("Device-ID", device.getDeviceId().toString().toUpperCase())
        .header("Accept-Language", "en-US,en;q=0.9") // TODO: add to constants
        .header("Accept-Encoding", "gzip, deflate, br") // TODO: add to constants
        .header("Content-Type", "application/json; charset=utf-8") // TODO: this may be dynamic
        .header("X-Request-ID", UUID.randomUUID().toString().toUpperCase())
        .header("User-Agent", app.getUserAgent()).header("Triumph-SDK-Version", app.getSdkVersion())
        .header("AppsFlyer-ID", APPSFLYER_ID);

    TriumphGoogleAppEngineSessionAffinityCookieService.getInstance().cookie().ifPresent(cookie -> {
      builder.addHeader("Cookie", cookie);
    });

    getBoolean(input, RequestAttribute.INCLUDE_LOCATION).ifPresent(includeLocation -> {
      if (includeLocation) {
        Point location = device.getLocation();
        double longitude = location.getX();
        double latitude = location.getY();
        builder.header("Triumph-Longitude", String.valueOf(longitude))
            .header("Triumph-Latitude", String.valueOf(latitude));
      }
    });

    getBoolean(input, RequestAttribute.USE_SIGNATURE).ifPresent(useSignature -> {
      if (useSignature) {
        JsonSerializableObject bodyObject = input.getBody();
        String bodyString = bodyObject != null ? bodyObject.write() : null;

        TriumphSignatureGeneratorContext signatureContext =
            TriumphSignatureGeneratorContext.builder().nonce(nonce).body(bodyString).build();
        String signature = TriumphSignatureGenerator.getInstance().generate(signatureContext);
        builder.header(TRIUMPH_SIGNATURE_KEY, signature);
      }
    });

    getBoolean(input, RequestAttribute.INCLUDE_TRIUMPH_ID_TOKEN).ifPresent(
        includeTriumphIdToken -> {
          if (includeTriumphIdToken) {
            getIdentity(input).ifPresent(identity -> {
              if (identity.getJwt() != null) {
                builder.header("Triumph-Id-Token", identity.getJwt());
              }
            });
          }
        });

    getBoolean(input, RequestAttribute.INCLUDE_FIREBASE_ID_TOKEN).ifPresent(
        includeFirebaseIdToken -> {
          if (includeFirebaseIdToken) {
            getIdentity(input).ifPresent(identity -> {
              if (identity.getJwt() != null) {
                builder.header("Firebase-Id-Token", identity.getJwt());
              }
            });
          }
        });

    getBoolean(input, RequestAttribute.INCLUDE_GAME_ID).ifPresent(includeGameId -> {
      if (includeGameId) {
        getString(input, RequestAttribute.GAME_ID).ifPresent(
            gameId -> builder.header("Game-Id", gameId));
      }
    });

    return builder.build();
  }

  private Optional<Identity> getIdentity(TriumphRequest input) {
    return Optional.ofNullable((Identity) input.getAttributes().get(RequestAttribute.IDENTITY));
  }

  private Optional<Device> getDevice(TriumphRequest input) {
    return Optional.ofNullable((Device) input.getAttributes().get(RequestAttribute.DEVICE));
  }

  private Optional<Boolean> getBoolean(TriumphRequest input, RequestAttribute attribute) {
    return Optional.ofNullable((Boolean) input.getAttributes().get(attribute));
  }

  private Optional<String> getString(TriumphRequest input, RequestAttribute attribute) {
    return Optional.ofNullable((String) input.getAttributes().get(attribute));
  }
}

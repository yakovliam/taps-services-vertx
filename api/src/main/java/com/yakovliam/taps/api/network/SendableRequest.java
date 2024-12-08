package com.yakovliam.taps.api.network;

import com.yakovliam.taps.api.model.JsonSerializableObject;
import com.yakovliam.taps.api.model.RequestMethod;
import org.jetbrains.annotations.Nullable;

/**
 * SendableRequest model
 */
public final class SendableRequest {

  /**
   * Method
   */
  private final RequestMethod method;

  /**
   * Base URL
   */
  private final String baseUrl;

  /**
   * Path
   */
  private final String path;

  /**
   * Headers
   */
  private final RequestHeaders headers;

  /**
   * Body
   */
  @Nullable
  private final JsonSerializableObject body;

  /**
   * Request
   *
   * @param method  the method
   * @param baseUrl the base url
   * @param path    the path
   * @param headers the headers
   * @param body    the body
   */
  public SendableRequest(RequestMethod method, String baseUrl, String path, RequestHeaders headers,
                         @Nullable JsonSerializableObject body) {
    this.method = method;
    this.baseUrl = baseUrl;
    this.path = path;
    this.headers = headers;
    this.body = body;
  }

  /**
   * Method
   *
   * @return the method
   */
  public RequestMethod method() {
    return method;
  }

  /**
   * Base URL
   *
   * @return the base url
   */
  public String baseUrl() {
    return baseUrl;
  }

  /**
   * Path
   *
   * @return the path
   */
  public String path() {
    return path;
  }

  /**
   * Headers
   *
   * @return the headers
   */
  public RequestHeaders headers() {
    return headers;
  }

  /**
   * Body
   *
   * @return the body
   */
  public JsonSerializableObject body() {
    return body;
  }
}


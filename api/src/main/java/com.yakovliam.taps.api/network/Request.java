package com.yakovliam.taps.api.network;

import com.yakovliam.taps.api.model.JsonSerializableObject;
import com.yakovliam.taps.api.model.RequestMethod;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * Request model
 */
public abstract class Request {

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
   * Body
   */
  @org.jetbrains.annotations.Nullable
  private final JsonSerializableObject body;

  /**
   * Request
   *
   * @param method  the method
   * @param baseUrl the base url
   * @param path    the path
   * @param body    the body
   */
  protected Request(RequestMethod method, String baseUrl, String path,
                    @org.jetbrains.annotations.Nullable JsonSerializableObject body) {
    this.method = method;
    this.baseUrl = baseUrl;
    this.path = path;
    this.body = body;
  }

  /**
   * Request
   *
   * @param method  the method
   * @param baseUrl the base url
   * @param path    the path
   */
  protected Request(RequestMethod method, String baseUrl, String path) {
    this.method = method;
    this.baseUrl = baseUrl;
    this.path = path;
    this.body = null;
  }

  public RequestMethod getMethod() {
    return method;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public String getPath() {
    return path;
  }

  public @Nullable JsonSerializableObject getBody() {
    return body;
  }

  /**
   * Gets the attributes for the request
   *
   * @return attributes
   */
  public abstract Map<RequestAttribute, Object> getAttributes();
}


package com.yakovliam.taps.api.network;

import java.util.HashMap;
import java.util.Map;

public class RequestHeaders {

  /**
   * Headers
   */
  private final Map<String, String> headers;


  /**
   * Request headers
   *
   * @param headers the headers
   */
  public RequestHeaders(Map<String, String> headers) {
    this.headers = headers;
  }

  public RequestHeaders() {
    this(new HashMap<>());
  }

  /**
   * The headers
   *
   * @return the headers
   */
  public Map<String, String> headers() {
    return this.headers;
  }

  public RequestHeaders headers(Map<String, String> headers) {
    this.headers.clear();
    this.headers.putAll(headers);
    return this;
  }

  public RequestHeaders addHeader(String key, String value) {
    this.headers.put(key, value);
    return this;
  }

  public RequestHeaders addHeaderOptional(String key, String value) {
    if (value != null) {
      this.headers.put(key, value);
    }
    return this;
  }

  public RequestHeaders removeHeader(String key) {
    this.headers.remove(key);
    return this;
  }

  public RequestHeaders clearHeaders() {
    this.headers.clear();
    return this;
  }

  public String getHeader(String key) {
    return this.headers.get(key);
  }

  public String[] toKeysAndValues() {
    String[] keysAndValues = new String[this.headers.size() * 2];
    int i = 0;
    for (Map.Entry<String, String> entry : this.headers.entrySet()) {
      keysAndValues[i] = entry.getKey();
      keysAndValues[i + 1] = entry.getValue();
      i += 2;
    }
    return keysAndValues;
  }

  public boolean hasHeader(String key) {
    return this.headers.containsKey(key);
  }

  public static RequestHeaders emptyMutable() {
    return new RequestHeaders();
  }
}

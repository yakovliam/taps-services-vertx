package com.yakovliam.taps.api.network;

/**
 * Though it appears this class builds requests, it's
 * really an 'injector' that takes an existing request
 * and based on provided context, injects additional
 * information/attributes/headers/whatever into the request.
 *
 * @param <T> the request type
 */
public abstract class RequestBuilder<T extends Request> {

  /**
   * Builds the request
   *
   * @return the request
   */
  public abstract okhttp3.Request build(T input);
}

package com.yakovliam.taps.api.signature.triumph;

import com.yakovliam.taps.api.model.NoncableUUID;
import com.yakovliam.taps.api.signature.SignatureGeneratorContext;
import java.util.UUID;

public class TriumphSignatureGeneratorContext extends SignatureGeneratorContext {

  /**
   * The nonce
   */
  private NoncableUUID nonce;

  /**
   * The body
   */
  private String body;

  /**
   * Triumph signature generator context
   *
   * @param nonce the nonce
   * @param body  the body
   */
  public TriumphSignatureGeneratorContext(NoncableUUID nonce, String body) {
    this.nonce = nonce;
    this.body = body;
  }

  public TriumphSignatureGeneratorContext() {
  }

  public NoncableUUID nonce() {
    return nonce;
  }

  public String body() {
    return body;
  }

  public TriumphSignatureGeneratorContext nonce(NoncableUUID nonce) {
    this.nonce = nonce;
    return this;
  }

  public TriumphSignatureGeneratorContext body(String body) {
    this.body = body;
    return this;
  }

  public static TriumphSignatureGeneratorContextBuilder builder() {
    return new TriumphSignatureGeneratorContextBuilder();
  }

  public static class TriumphSignatureGeneratorContextBuilder {
    private NoncableUUID nonce;
    private String body;

    TriumphSignatureGeneratorContextBuilder() {
    }

    public TriumphSignatureGeneratorContextBuilder nonce(NoncableUUID nonce) {
      this.nonce = nonce;
      return this;
    }

    public TriumphSignatureGeneratorContextBuilder body(String body) {
      this.body = body;
      return this;
    }

    public TriumphSignatureGeneratorContext build() {
      return new TriumphSignatureGeneratorContext(nonce, body);
    }

    public String toString() {
      return "TriumphSignatureGeneratorContext.TriumphSignatureGeneratorContextBuilder(nonce=" +
          this.nonce + ", body=" + this.body + ")";
    }
  }
}

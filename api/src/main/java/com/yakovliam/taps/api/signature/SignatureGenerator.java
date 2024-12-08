package com.yakovliam.taps.api.signature;

public interface SignatureGenerator<C extends SignatureGeneratorContext> {

  /**
   * Generates a signature
   *
   * @param context the context
   * @return the generated signature
   */
  String generate(C context);
}

package com.yakovliam.taps.api.cookie;

public class TriumphGoogleAppEngineSessionAffinityCookieService
    extends GoogleAppEngineSessionAffinityCookieService {

  public TriumphGoogleAppEngineSessionAffinityCookieService() {
    super(
        "GAESA=CowBMDA3OTg5ZjJhMTYxYzNjMDk1YWI2ZWZjZGFiNTIzNGNkZDQ2NTQ2MjdmYmE2NWRiNDhlOWRkMmExNmY1NWI1YWExYzRiMzdmZmQyYTRhZDk5MjIxYjBhMGFkNTdlZTcxNzU4NzE3MzBjNmU4MDkwMmUwYjEyNDNhZTU1YmRkNWRlNGM4Nzk0NGFiNGIQqaSr0aYy");
  }

  private static TriumphGoogleAppEngineSessionAffinityCookieService instance;

  public static TriumphGoogleAppEngineSessionAffinityCookieService getInstance() {
    if (instance == null) {
      instance = new TriumphGoogleAppEngineSessionAffinityCookieService();
    }
    return instance;
  }
}

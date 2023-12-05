package org.example.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import lombok.Getter;
import org.example.containers.WireMockContainer;

/**
 .
 */
public class WiremockModule extends AbstractModule {

  @Getter
  private static final WireMockContainer wiremockContainer =
          new WireMockContainer(WireMockContainer.WIREMOCK_2_LATEST)
                  .withMappingFromResource("users", "users.json")
                  .withMappingFromResource("courses", "courses.json")
                  .withMappingFromResource("score", "score.json");

  @Provides
  public WireMockContainer getContainer() {
    return wiremockContainer;
  }
}

package org.example.suite;

import lombok.Getter;
import org.example.containers.WireMockContainer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.suite.api.Suite;

@Suite
public class WiremockSuite {

  @Getter
  private static final WireMockContainer wiremock =
      new WireMockContainer(WireMockContainer.WIREMOCK_2_LATEST)
          .withMappingFromResource("users.json")
          .withMappingFromResource("courses.json")
          .withMappingFromResource("score.json");

  @BeforeAll
  static void beforeAll() {
    wiremock.start();
  }

  @AfterAll
  static void afterAll() {
    if (wiremock.isRunning()) {
      wiremock.stop();
    }
  }
}

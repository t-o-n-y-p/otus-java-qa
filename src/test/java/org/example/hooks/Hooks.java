package org.example.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.example.factory.WiremockModule;
import org.example.support.GuiceScoped;
import org.wiremock.integrations.testcontainers.WireMockContainer;

/**
 * .
 */
public class Hooks {

  @Inject
  private GuiceScoped scoped;

  /**
   * .
   */
  @After
  public void afterScenario() {
    if (scoped.getDriver() != null) {
      scoped.getDriver().quit();
    }
  }
}

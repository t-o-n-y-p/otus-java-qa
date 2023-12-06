package org.example.support;

import io.cucumber.guice.ScenarioScoped;
import lombok.Getter;
import lombok.Setter;
import org.example.factory.WiremockModule;
import org.openqa.selenium.WebDriver;
import org.wiremock.integrations.testcontainers.WireMockContainer;

/**
 * .
 */
@ScenarioScoped
@Getter
@Setter
public class GuiceScoped {

  private WebDriver driver;

  private String browserName;

  private WireMockContainer container = WiremockModule.getWiremockContainer();

}

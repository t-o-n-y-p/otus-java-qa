package org.example.support;

import io.cucumber.guice.ScenarioScoped;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

/**
 .
 */
@ScenarioScoped
@Getter
@Setter
public class GuiceScoped {

  private WebDriver driver;

  private String browserName;

}

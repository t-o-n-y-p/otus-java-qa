package org.example.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import org.example.support.GuiceScoped;

/**
 .
 */
public class Hooks {

  @Inject
  private GuiceScoped scoped;

  /**
   .
   */
  @After
  public void afterScenario() {
    if (scoped.getDriver() != null) {
      scoped.getDriver().quit();
    }
  }
}

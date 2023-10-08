package org.example.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import org.example.factory.WebDriverFactory;
import org.example.support.GuiceScoped;

/**
 .
 */
public class CommonSteps {

  @Inject
  private GuiceScoped scoped;
  @Inject
  private WebDriverFactory factory;

  @Пусть("Я открываю браузер {}")
  public void openBrowser(String browserName) {
    scoped.setDriver(factory.create(browserName));
    scoped.setBrowserName(browserName);
  }

}

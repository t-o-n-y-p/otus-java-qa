package org.example.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;

public class GuiceModule extends AbstractModule {

  @Provides
  @Singleton
  public WebDriver getDriver(WebDriverFactory factory) {
    return factory.create();
  }
}

package org.example.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.factory.impl.BrowserSettings;
import org.example.factory.impl.ChromeSettings;
import org.openqa.selenium.WebDriver;

@SuppressWarnings("unused")
public class GuiceModule extends AbstractModule {

  private final Browser browser = Browser.valueOf(System.getProperty("browser").toUpperCase());

  @Provides
  @Singleton
  private WebDriverManager webDriverManager() {
    return switch (browser) {
      case CHROME -> WebDriverManager.chromedriver();
    };
  }

  @Provides
  @Singleton
  private BrowserSettings<?> browserSettings() {
    return switch (browser) {
      case CHROME -> new ChromeSettings();
    };
  }

  @Provides
  @Singleton
  private WebDriver getDriver(WebDriverFactory factory) {
    return factory.create();
  }

  private enum Browser {
    CHROME
  }
}

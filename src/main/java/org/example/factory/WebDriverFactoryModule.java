package org.example.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.factory.impl.BrowserSettings;
import org.example.factory.impl.ChromeSettings;

@SuppressWarnings("unused")
public class WebDriverFactoryModule extends AbstractModule {

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

  private enum Browser {
    CHROME
  }
}

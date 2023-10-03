package org.example.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.example.pages.MainPage;
import org.openqa.selenium.WebDriver;

/**
 .
 */
public class GuiceModule extends AbstractModule {

  private final WebDriver driver = new WebDriverFactory().create();

  @Provides
  public WebDriver getDriver() {
    return driver;
  }

  @Provides
  public MainPage getMainPage() {
    return new MainPage(driver);
  }

}

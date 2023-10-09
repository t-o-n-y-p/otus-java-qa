package org.example.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.pages.LessonPage;
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
  @Singleton
  public MainPage getMainPage() {
    return new MainPage(driver);
  }

  @Provides
  @Singleton
  public LessonPage getLessonPage() {
    return new LessonPage(driver);
  }

}

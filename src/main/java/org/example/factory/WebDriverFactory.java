package org.example.factory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import org.example.factory.impl.BrowserSettings;
import org.openqa.selenium.WebDriver;

@Singleton
@AllArgsConstructor(onConstructor_ = @Inject)
public class WebDriverFactory {

  private WebDriverManager webDriverManager;
  private BrowserSettings<?> browserSettings;

  public WebDriver create() {
    return webDriverManager.capabilities(browserSettings.configureDriver()).create();
  }

  public void quit(WebDriver driver) {
    webDriverManager.quit(driver);
  }
}

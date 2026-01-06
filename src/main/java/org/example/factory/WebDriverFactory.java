package org.example.factory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import org.example.factory.impl.BrowserSettings;
import org.example.listeners.StyleUpdateListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.decorators.Decorated;
import org.openqa.selenium.support.events.EventFiringDecorator;

@Singleton
@AllArgsConstructor(onConstructor_ = @Inject)
public class WebDriverFactory {

  private WebDriverManager webDriverManager;
  private BrowserSettings<?> browserSettings;
  private StyleUpdateListener styleUpdateListener;

  public WebDriver create() {
    WebDriver driver = webDriverManager.capabilities(browserSettings.configureDriver()).create();
    return new EventFiringDecorator<>(styleUpdateListener).decorate(driver);
  }

  @SuppressWarnings("unchecked")
  public void quit(WebDriver driver) {
    WebDriver original = ((Decorated<WebDriver>) driver).getOriginal();
    webDriverManager.quit(original);
  }
}

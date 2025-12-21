package org.example.factory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import org.example.exceptions.BrowserNotSupportedException;
import org.example.factory.impl.BrowserSettings;
import org.example.factory.impl.ChromeSettings;
import org.example.listeners.StyleUpdateListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;

@Singleton
@AllArgsConstructor(onConstructor_ = @Inject)
public class WebDriverFactory {

  private StyleUpdateListener styleUpdateListener;

  public WebDriver create() {
    String browser = System.getProperty("browser");
    switch (browser) {
      case "chrome" -> {
        WebDriverManager.chromedriver().setup();
        BrowserSettings<ChromeOptions> browserSettings = new ChromeSettings();
        WebDriver driver =
            new EventFiringDecorator<>(styleUpdateListener)
                .decorate(new ChromeDriver(browserSettings.configureDriver()));
        driver.manage().window().maximize();
        return driver;
      }
      default -> throw new BrowserNotSupportedException(browser);
    }
  }

  public static void init() {
    String browser = System.getProperty("browser");
    switch (browser) {
      case "chrome" -> WebDriverManager.chromedriver().setup();
      default -> throw new BrowserNotSupportedException(browser);
    }
  }
}

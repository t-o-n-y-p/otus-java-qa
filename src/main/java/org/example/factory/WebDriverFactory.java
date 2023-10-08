package org.example.factory;

import com.google.inject.Inject;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.example.exceptions.BrowserNotSupportedException;
import org.example.factory.impl.BrowserSettings;
import org.example.factory.impl.ChromeSettings;
import org.example.factory.impl.FirefoxSettings;
import org.example.listeners.ActionsListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;

/**
 .
 */
public class WebDriverFactory {

  public static final String BROWSER_NAME = System.getProperty("browser", "chrome");

  @Inject
  public WebDriverFactory() {
  }

  public WebDriver create() {
    return create(BROWSER_NAME);
  }

  /**
   .
   */
  public WebDriver create(String browserName) {
    if (browserName.toLowerCase().contains("chrome")) {
      WebDriverManager.chromedriver().setup();
      BrowserSettings<ChromeOptions> browserSettings = new ChromeSettings();
      WebDriver driver = new EventFiringDecorator<>(new ActionsListener())
          .decorate(new ChromeDriver(browserSettings.configureDriver()));
      driver.manage().window().maximize();
      return driver;
    } else if (browserName.toLowerCase().contains("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      BrowserSettings<FirefoxOptions> browserSettings = new FirefoxSettings();
      WebDriver driver = new EventFiringDecorator<>(new ActionsListener())
          .decorate(new FirefoxDriver(browserSettings.configureDriver()));
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      return driver;
    }
    throw new BrowserNotSupportedException(browserName);
  }

}

package org.example.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.example.exceptions.BrowserNotSupportedException;
import org.example.factory.impl.BrowserSettings;
import org.example.factory.impl.ChromeSettings;
import org.example.factory.impl.FirefoxSettings;
import org.example.listeners.ActionsListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

/**
 .
 */
public class WebDriverFactory {

  public static final String BROWSER_NAME = System.getProperty("browser", "chrome");

  /**
   .
   */
  @SneakyThrows
  public WebDriver create() {
    switch (BROWSER_NAME) {
      case "chrome" -> {
        WebDriverManager.chromedriver().setup();
        BrowserSettings browserSettings = new ChromeSettings();
        WebDriver remoteDriver = new RemoteWebDriver(browserSettings.configureDriver());
        WebDriver driver = new EventFiringDecorator<>(new ActionsListener()).decorate(remoteDriver);
        driver.manage().window().maximize();
        return driver;
      }
      case "firefox" -> {
        WebDriverManager.firefoxdriver().setup();
        BrowserSettings browserSettings = new FirefoxSettings();
        WebDriver remoteDriver = new RemoteWebDriver(browserSettings.configureDriver());
        WebDriver driver = new EventFiringDecorator<>(new ActionsListener()).decorate(remoteDriver);
        driver.manage().window().maximize();
        return driver;
      }
      default -> throw new BrowserNotSupportedException(BROWSER_NAME);
    }
  }

}

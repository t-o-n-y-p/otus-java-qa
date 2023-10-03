package org.example.actions;

import java.time.Duration;
import org.example.factory.WebDriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 .
 */
public class CommonActions {

  private final WebDriver driver;
  private final Actions actions;

  public CommonActions(WebDriver driver) {
    this.driver = driver;
    actions = new Actions(driver);
  }

  /**
   .
   */
  public void click(WebElement element) {
    if (WebDriverFactory.BROWSER_NAME.equals("firefox")) {
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
      actions.pause(Duration.ofSeconds(1));
    }
    actions
        .moveToElement(element)
        .pause(Duration.ofSeconds(1))
        .click()
        .pause(Duration.ofSeconds(1))
        .perform();
  }

}

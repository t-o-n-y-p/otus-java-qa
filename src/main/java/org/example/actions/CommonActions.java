package org.example.actions;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CommonActions {

  private final WebDriver driver;
  private final Actions actions;

  public CommonActions(WebDriver driver) {
    this.driver = driver;
    actions = new Actions(driver);
  }

  public void click(WebElement element) {
    actions
        .moveToElement(element)
        .pause(Duration.ofSeconds(1)) // исключительно для демонстрации видимости рамки
        .click()
        .perform();
  }
}

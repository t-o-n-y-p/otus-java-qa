package org.example.waiters;

import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 .
 */
public class CommonWaiter {

  private static final Duration duration = Duration.ofMillis(
      Integer.parseInt(System.getProperty("webdriver.timeouts.implicitly-wait", "5000")));

  private final WebDriverWait wait;

  public CommonWaiter(WebDriver driver) {
    wait = new WebDriverWait(driver, duration);
  }

  /**
   .
   */
  public boolean waitForCondition(ExpectedCondition<Boolean> condition) {
    try {
      return wait.until(condition);
    } catch (TimeoutException ex) {
      return false;
    }
  }

}

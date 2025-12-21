package org.example.waiters;

import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonWaiter {

  private final WebDriverWait wait;

  public CommonWaiter(WebDriver driver) {
    Duration explicitWaitTimeout =
        Duration.ofMillis(
            Integer.parseInt(System.getProperty("webdriver.timeouts.implicitly-wait", "5000")));
    wait = new WebDriverWait(driver, explicitWaitTimeout);
  }

  public boolean waitForCondition(ExpectedCondition<?> condition) {
    try {
      wait.until(condition);
      return true;
    } catch (TimeoutException ignored) {
      return false;
    }
  }
}

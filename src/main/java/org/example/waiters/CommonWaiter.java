package org.example.waiters;

import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonWaiter {

  private final WebDriverWait wait;

  public CommonWaiter(WebDriver driver) {
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

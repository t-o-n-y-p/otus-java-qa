package org.example.listeners;

import com.google.inject.Singleton;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

@Singleton
public class StyleUpdateListener implements WebDriverListener {

  @SneakyThrows
  @Override
  public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
    for (WebElement webElement : result) {
      ((JavascriptExecutor) driver)
          .executeScript(
              "arguments[0].setAttribute(\"style\",\"border:5px solid yellow\");", webElement);
    }
    TimeUnit.SECONDS.sleep(1); // исключительно для демонстрации подсветки
  }
}

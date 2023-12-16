package org.example.waiter;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import java.util.function.Supplier;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonWaiter {

  public void until(Supplier<Boolean> condition, String message) {
    Selenide.Wait()
        .withTimeout(Duration.ofSeconds(30))
        .withMessage(message)
        .until(driver -> {
          try {
            return condition.get();
          } catch (Exception e) {
            return false;
          }
        });
  }

  public void stalenessOf(SelenideElement element) {
    Selenide.Wait().until(ExpectedConditions.stalenessOf(element.getWrappedElement()));
  }

}

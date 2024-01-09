package org.example.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$x;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

public class OnboardingPage extends AbsBasePage {

  private final SelenideElement onboardingPageControlItemNextButton =
      $x(
          "//android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup"
              + "/android.view.ViewGroup/android.view.ViewGroup[1]");

  @Step("Пропустить онбординг")
  public void skipOnboardingItems() {
    while (onboardingPageControlItemNextButton.is(visible)) {
      onboardingPageControlItemNextButton.click();
      try {
        waiter.stalenessOf(onboardingPageControlItemNextButton);
      } catch (TimeoutException | NoSuchElementException | ElementNotFound ignored) {
      }
    }
  }

}

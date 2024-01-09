package org.example.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class ExercisePage extends AbsBasePage {

  private final SelenideElement startButton =
      $x("//android.widget.ScrollView//android.widget.TextView[@text='Start']");

  @Step("Кликнуть Start на экране Exercise")
  public void clickStart() {
    startButton.shouldBe(visible.because("Start button not found on the exercise page")).click();
  }

}

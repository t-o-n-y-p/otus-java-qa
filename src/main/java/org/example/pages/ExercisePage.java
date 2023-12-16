package org.example.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class ExercisePage extends AbsBasePage {

  private final SelenideElement startButton =
      $x("//android.widget.ScrollView//android.widget.TextView[@text='Start']");

  public void clickStart() {
    startButton.shouldBe(visible.because("Start button not found on the exercise page")).click();
  }

}

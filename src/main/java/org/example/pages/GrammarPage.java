package org.example.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

import io.qameta.allure.Step;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class GrammarPage extends AbsBasePage {

  @Step("Выбрать урок {lesson}")
  public void selectLesson(Lesson lesson) {
    $x(
        "//android.widget.ScrollView/android.view.ViewGroup"
            + "/android.view.ViewGroup[.//android.widget.TextView[@text='%s']]"
            .formatted(lesson.getName()))
        .shouldBe(
            visible.because(
                "Lesson '%s' not found on the grammar page".formatted(lesson.getName())))
        .click();
  }

  @AllArgsConstructor
  @Getter
  public enum Lesson {
    MUCH_MANY("Much - Many");

    private final String name;
  }

}

package org.example.pages;

import static org.assertj.core.api.Assertions.assertThat;

import io.qameta.allure.Step;
import org.example.annotations.Template;
import org.example.annotations.UrlTemplate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 .
 */
@UrlTemplate(
    @Template(name = "lesson", template = "lessons/%s")
)
public class LessonPage extends AbsBasePage<LessonPage> {

  public LessonPage(WebDriver driver) {
    super(driver);
  }

  public String getLessonName() {
    return driver.findElement(By.xpath("(//section)[2]//h1")).getText();
  }

  /**
   .
   */
  @Step("Проверить, что название курса не пустое")
  public void checkLessonNameIsNotNull() {
    assertThat(getLessonName())
        .as("Страница курса не открылась, название курса пустое")
        .isNotNull();
  }

  /**
   .
   */
  @Step("Проверить, что название курса равно {lessonName}")
  public void checkLessonNameEquals(String lessonName) {
    assertThat(getLessonName())
        .as("Ожидаемая страница курса не открылась")
        .isEqualTo(lessonName);
  }
}

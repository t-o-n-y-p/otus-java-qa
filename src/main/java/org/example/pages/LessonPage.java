package org.example.pages;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import org.example.annotations.Template;
import org.example.annotations.UrlTemplate;
import org.example.support.GuiceScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 .
 */
@UrlTemplate(
    @Template(name = "lesson", template = "lessons/%s")
)
public class LessonPage extends AbsBasePage<LessonPage> {

  @Inject
  public LessonPage(GuiceScoped scoped) {
    super(scoped);
  }

  public LessonPage(WebDriver driver) {
    super(driver);
  }

  public String getLessonName() {
    return driver.findElement(By.xpath("(//section)[2]//h1")).getText();
  }

  /**
   .
   */
  public void checkLessonNameIsNotNull() {
    assertThat(getLessonName())
        .as("Страница курса не открылась, название курса пустое")
        .isNotNull();
  }

  /**
   .
   */
  public void checkLessonNameEquals(String lessonName) {
    assertThat(getLessonName())
        .as("Ожидаемая страница курса не открылась")
        .isEqualTo(lessonName);
  }
}

package org.example.steps.pages;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import org.example.pages.LessonPage;

/**
 .
 */
public class LessonPageSteps {

  @Inject
  private LessonPage lessonPage;

  @Тогда("Открылась страница курса {}")
  public void lessonShouldBeOpened(String name) {
    assertThat(lessonPage.getLessonName())
        .as("Название курса %s не найдено на странице курса", name)
        .isEqualTo(name);
  }

}

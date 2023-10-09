package org.example.steps.pages;

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
    lessonPage.checkLessonNameEquals(name);
  }

}

package org.example;

import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.example.extensions.GuiceExtension;
import org.example.pages.LessonPage;
import org.example.pages.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 .
 */
@ExtendWith(GuiceExtension.class)
public class FindProgramTest {

  @Inject
  private MainPage mainPage;

  @Inject
  private LessonPage lessonPage;

  @ParameterizedTest
  @ValueSource(strings = {
      "Специализация Java-разработчик",
      "Специализация Administrator Linux"
  })
  void testProgramByName(String programName) {
    mainPage
        .open()
        .selectProgramByName(programName);
    lessonPage.checkLessonNameEquals(
        StringUtils.substringAfter(programName, "Специализация "));

  }

  @Test
  void testProgramByEarliestStartDate() {
    mainPage
        .open()
        .selectProgramByEarliestStartDate();
    lessonPage.checkLessonNameIsNotNull();
  }

  @Test
  void testProgramByLatestStartDate() {
    mainPage
        .open()
        .selectProgramByLatestStartDate();
    lessonPage.checkLessonNameIsNotNull();
  }
}

package org.example;

import com.google.inject.Inject;
import io.qameta.allure.Epic;
import org.apache.commons.lang3.StringUtils;
import org.example.extensions.GuiceExtension;
import org.example.pages.LessonPage;
import org.example.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 .
 */
@ExtendWith(GuiceExtension.class)
@Epic("UI тесты сайта Otus на Selenoid")
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
  @DisplayName("Выбор специализации по названию")
  void testProgramByName(String programName) {
    mainPage
        .open()
        .selectProgramByName(programName);
    lessonPage.checkLessonNameEquals(
        StringUtils.substringAfter(programName, "Специализация "));

  }

  @Test
  @DisplayName("Выбор специализации, стартующей раньше всех")
  void testProgramByEarliestStartDate() {
    mainPage
        .open()
        .selectProgramByEarliestStartDate();
    lessonPage.checkLessonNameIsNotNull();
  }

  @Test
  @DisplayName("Выбор специализации, стартующей позже всех")
  void testProgramByLatestStartDate() {
    mainPage
        .open()
        .selectProgramByLatestStartDate();
    lessonPage.checkLessonNameIsNotNull();
  }
}

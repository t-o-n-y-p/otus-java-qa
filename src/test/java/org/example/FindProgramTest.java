package org.example;

import com.google.inject.Inject;
import org.example.extensions.GuiceExtension;
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

  @ParameterizedTest
  @ValueSource(strings = {
      "Специализация Java-разработчик",
      "Специализация Administrator Linux"
  })
  void testProgramByName(String programName) {
    mainPage
        .open()
        .selectProgramByName(programName);

  }

  @Test
  void testProgramByEarliestStartDate() {
    mainPage
        .open()
        .selectProgramByEarliestStartDate();
  }
}

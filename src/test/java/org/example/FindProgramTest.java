package org.example;

import com.google.inject.Inject;
import org.example.extensions.AfterEachExtension;
import org.example.extensions.BeforeEachExtension;
import org.example.pages.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

/**
 .
 */
@ExtendWith({
    BeforeEachExtension.class,
    AfterEachExtension.class
})
public class FindProgramTest {

  @Inject
  private WebDriver driver;

  @ParameterizedTest
  @ValueSource(strings = {
      "Специализация Java-разработчик",
      "Специализация Administrator Linux"
  })
  void testProgramByName(String programName) {
    new MainPage(driver)
        .open()
        .selectProgramByName(programName);

  }

  @Test
  void testProgramByEarliestStartDate() {
    new MainPage(driver)
        .open()
        .selectProgramByEarliestStartDate();
  }
}

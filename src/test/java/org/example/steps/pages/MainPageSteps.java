package org.example.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import org.example.pages.MainPage;

/**
 .
 */
public class MainPageSteps {

  @Inject
  private MainPage mainPage;

  @Если("Я открываю главную страницу")
  public void openMainPage() {
    mainPage.open();
  }

  @И("Я выбираю специализацию {}")
  public void pickProgram(String programName) {
    mainPage.selectProgramByName("Специализация " + programName);
  }

}

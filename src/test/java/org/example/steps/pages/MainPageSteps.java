package org.example.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import java.time.LocalDate;
import org.example.components.CourseCard;
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

  @Тогда("Вывести в консоль специализации, стартующие {programStartDate} или позже")
  public void printProgramsFromDate(LocalDate date) {
    System.out.println(mainPage.getProgramCardsWithDateEqualOrAfter(date));
  }

}

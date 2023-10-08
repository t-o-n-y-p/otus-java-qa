package org.example.steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import org.example.pages.PreliminaryLessonsPage;

/**
 .
 */
public class PreliminaryLessonsPageSteps {

  @Inject
  private PreliminaryLessonsPage preliminaryLessonsPage;

  @Если("Я открываю страницу подготовительных курсов")
  public void openPage() {
    preliminaryLessonsPage.open();
  }

  @Тогда("Вывести в консоль самый дешевый подготовительный курс")
  public void printCheapest() {
    System.out.println(preliminaryLessonsPage.getCheapestCourse());
  }

  @Тогда("Вывести в консоль самый дорогой подготовительный курс")
  public void printMostExpensive() {
    System.out.println(preliminaryLessonsPage.getMostExpensiveCourse());
  }

}

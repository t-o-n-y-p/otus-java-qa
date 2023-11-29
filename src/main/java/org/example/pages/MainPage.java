package org.example.pages;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BinaryOperator;
import org.example.annotations.Path;
import org.example.components.CourseCard;
import org.example.exceptions.ProgramCardNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 .
 */
@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

  public MainPage(WebDriver driver) {
    super(driver);
  }

  private List<CourseCard> getProgramCards() {
    return driver.findElements(By.xpath("//section[./h2[text()='Специализации']]/div/div")).stream()
        .map(e -> new CourseCard(driver, e)).toList();
  }

  /**
   .
   */
  public void selectProgramByName(String programName) {
    getProgramCards()
        .stream()
        .filter(card -> programName.equals(card.getTitle()))
        .findFirst()
        .ifPresentOrElse(
            CourseCard::click,
            () -> {
              throw new ProgramCardNotFoundException(programName);
            }
        );
  }

  /**
   .
   */
  public void selectProgramByEarliestStartDate() {
    selectProgramByReduce(
        (e1, e2) -> e1.getValue().isBefore(e2.getValue()) ? e1 : e2);
  }

  /**
   .
   */
  public void selectProgramByLatestStartDate() {
    selectProgramByReduce(
        (e1, e2) -> e1.getValue().isAfter(e2.getValue()) ? e1 : e2);
  }

  private void selectProgramByReduce(BinaryOperator<Map.Entry<CourseCard, LocalDate>> reduce) {
    getProgramCards()
        .stream()
        .map(card -> {
          LocalDate startDate = card.getStartDate();
          return startDate == null ? null : Map.entry(card, startDate);
        })
        .filter(Objects::nonNull)
        .reduce(reduce)
        .ifPresentOrElse(
            e -> e.getKey().click(),
            () -> {
              throw new ProgramCardNotFoundException();
            }
        );
  }
}

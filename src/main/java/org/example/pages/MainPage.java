package org.example.pages;

import java.util.List;
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
        (card1, card2) -> card1.getStartDate().isBefore(card2.getStartDate()) ? card1 : card2);
  }

  /**
   .
   */
  public void selectProgramByLatestStartDate() {
    selectProgramByReduce(
        (card1, card2) -> card1.getStartDate().isAfter(card2.getStartDate()) ? card1 : card2);
  }

  private void selectProgramByReduce(BinaryOperator<CourseCard> reduce) {
    getProgramCards()
        .stream()
        .reduce(reduce)
        .ifPresentOrElse(
            CourseCard::click,
            () -> {
              throw new ProgramCardNotFoundException();
            }
        );
  }
}

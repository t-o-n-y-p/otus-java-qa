package org.example.pages;

import com.google.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import org.example.annotations.Path;
import org.example.components.CourseCard;
import org.example.exceptions.ProgramCardNotFoundException;
import org.example.support.GuiceScoped;
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

  @Inject
  public MainPage(GuiceScoped scoped) {
    super(scoped);
  }

  private List<CourseCard> getProgramCards() {
    return getCardsWithFilter(c -> true);
  }

  public List<CourseCard> getProgramCardsWithDateEqualOrAfter(LocalDate date) {
    return getCardsWithFilter(c -> !c.getStartDate().isBefore(date));
  }

  private List<CourseCard> getCardsWithFilter(Predicate<CourseCard> condition) {
    String elementSelector = "(//section[./h2[text()='Специализации']]/div/div)[%d]";
    AtomicInteger atomicInteger = new AtomicInteger(1);
    return driver.findElements(By.xpath("//section[./h2[text()='Специализации']]/div/div")).stream()
        .map(e -> {
          int index = atomicInteger.getAndIncrement();
          return scoped == null
              ? new CourseCard(driver, elementSelector.formatted(index))
              : new CourseCard(scoped, elementSelector.formatted(index));
        })
        .filter(condition)
        .toList();
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

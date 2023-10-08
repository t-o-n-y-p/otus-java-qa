package org.example.pages;

import com.google.inject.Inject;
import java.util.List;
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
    return driver.findElements(By.xpath("//section[./h2[text()='Специализации']]/div/div")).stream()
        .map(e -> scoped == null ? new CourseCard(driver, e) : new CourseCard(scoped, e))
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
    getProgramCards()
        .stream()
        .reduce((card1, card2) ->
            card1.getStartDate().isBefore(card2.getStartDate()) ? card1 : card2)
        .ifPresentOrElse(
            CourseCard::click,
            () -> {
              throw new ProgramCardNotFoundException();
            }
        );
  }
}

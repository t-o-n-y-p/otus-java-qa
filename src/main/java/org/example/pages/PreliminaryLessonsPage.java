package org.example.pages;

import com.google.inject.Inject;
import java.util.Comparator;
import java.util.List;
import org.example.annotations.Path;
import org.example.components.PreliminaryCourseCard;
import org.example.exceptions.ProgramCardNotFoundException;
import org.example.support.GuiceScoped;
import org.openqa.selenium.By;

/**
 .
 */
@Path("online")
public class PreliminaryLessonsPage extends AbsBasePage<PreliminaryLessonsPage> {

  @Inject
  public PreliminaryLessonsPage(GuiceScoped scoped) {
    super(scoped);
  }

  private List<PreliminaryCourseCard> getCourseCards() {
    return driver.findElements(By.xpath("//div[@class='lessons']/a")).stream()
        .map(e -> new PreliminaryCourseCard(scoped, e))
        .toList();
  }

  /**
   .
   */
  public PreliminaryCourseCard getCheapestCourse() {
    return getCourseCards().stream()
        .min(Comparator.comparingInt(PreliminaryCourseCard::getPrice))
        .orElseThrow(ProgramCardNotFoundException::new);
  }

  /**
   .
   */
  public PreliminaryCourseCard getMostExpensiveCourse() {
    return getCourseCards().stream()
        .max(Comparator.comparingInt(PreliminaryCourseCard::getPrice))
        .orElseThrow(ProgramCardNotFoundException::new);
  }
}

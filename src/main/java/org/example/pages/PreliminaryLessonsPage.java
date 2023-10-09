package org.example.pages;

import com.google.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
    String collectionSelector = "//div[@class='lessons']/a";
    AtomicInteger atomicInteger = new AtomicInteger(1);
    return driver.findElements(By.xpath(collectionSelector)).stream()
        .map(e -> new PreliminaryCourseCard(
            scoped,
            "(%s)[%d]".formatted(collectionSelector, atomicInteger.getAndIncrement())))
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

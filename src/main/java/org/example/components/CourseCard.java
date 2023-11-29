package org.example.components;

import java.time.LocalDate;
import java.util.List;
import org.example.utils.DateTimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 .
 */
public class CourseCard extends AbsComponent {

  private static final String DATE_TBA = "О дате старта будет объявлено позже";

  public CourseCard(WebDriver driver, WebElement root) {
    super(driver, root);
  }

  public String getTitle() {
    return root.findElement(By.tagName("h5")).getText();
  }

  /**
   .
   */
  public LocalDate getStartDate() {
    List<WebElement> spans = root.findElements(By.tagName("span"));
    String text = spans.get(spans.size() - 1).getText();
    if (DATE_TBA.equals(text)) {
      return null;
    }
    String[] parsedText = text.split("\\s+");
    if (parsedText.length == 5) {
      return LocalDate.parse(
          String.join(" ", parsedText[1], parsedText[2]),
          DateTimeUtil.COURSE_START_DAY_MONTH_FORMATTER);
    }
    return LocalDate.parse(
        String.join(" ", parsedText[1], parsedText[2], parsedText[3]),
        DateTimeUtil.COURSE_START_DAY_MONTH_YEAR_FORMATTER);
  }
}

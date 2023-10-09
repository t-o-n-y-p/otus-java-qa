package org.example.components;

import java.time.LocalDate;
import java.util.List;
import org.example.support.GuiceScoped;
import org.example.utils.DateTimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 .
 */
public class CourseCard extends AbsComponent {

  public CourseCard(WebDriver driver, String xpath) {
    super(driver, xpath);
  }

  public CourseCard(GuiceScoped scoped, String xpath) {
    super(scoped, xpath);
  }

  public String getTitle() {
    return driver.findElement(By.xpath(xpath + "//h5")).getText();
  }

  /**
   .
   */
  public LocalDate getStartDate() {
    List<WebElement> spans = driver.findElements(By.xpath(xpath + "//span"));
    String[] parsedText = spans.get(spans.size() - 1).getText().split("\\s+");
    if (parsedText.length == 5) {
      return LocalDate.parse(
          String.join(" ", parsedText[1], parsedText[2]),
          DateTimeUtil.COURSE_START_DAY_MONTH_FORMATTER);
    }
    return LocalDate.parse(
        String.join(" ", parsedText[1], parsedText[2], parsedText[3]),
        DateTimeUtil.COURSE_START_DAY_MONTH_YEAR_FORMATTER);
  }

  @Override
  public String toString() {
    return "Курс %s, стартует %s".formatted(getTitle(), getStartDate());
  }
}

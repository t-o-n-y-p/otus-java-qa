package org.example.components;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import org.example.factory.WebDriverFactory;
import org.example.utils.DateTimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 .
 */
public class CourseCard extends AbsComponent {

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

  /**
   .
   */
  public void click() {
    if (WebDriverFactory.BROWSER_NAME.equals("firefox")) {
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", root);
      actions.pause(Duration.ofSeconds(1));
    }
    actions
        .moveToElement(root)
        .pause(Duration.ofSeconds(1))
        .click()
        .pause(Duration.ofSeconds(1))
        .perform();
  }
}

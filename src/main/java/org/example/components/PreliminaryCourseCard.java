package org.example.components;

import org.example.support.GuiceScoped;
import org.openqa.selenium.By;

/**
 .
 */
public class PreliminaryCourseCard extends AbsComponent {

  public PreliminaryCourseCard(GuiceScoped scoped, String xpath) {
    super(scoped, xpath);
  }

  public String getTitle() {
    return driver.findElement(By.xpath(xpath + "/div/div[last() - 1]")).getText();
  }

  /**
   .
   */
  public Integer getPrice() {
    return Integer.parseInt(
        driver.findElement(By.xpath(xpath + "/div/div[last()]/div")).getText()
            .replaceAll("\\D", ""));
  }

  @Override
  public String toString() {
    return "Курс %s, цена %s".formatted(getTitle(), getPrice());
  }
}

package org.example.components;

import org.example.support.GuiceScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 .
 */
public class PreliminaryCourseCard extends AbsComponent {

  public PreliminaryCourseCard(GuiceScoped scoped, WebElement root) {
    super(scoped, root);
  }

  public String getTitle() {
    return root.findElement(By.xpath("./div/div[last() - 1]")).getText();
  }

  /**
   .
   */
  public Integer getPrice() {
    return Integer.parseInt(
        root.findElement(By.xpath("./div/div[last()]/div")).getText()
            .replaceAll("\\D", ""));
  }

  @Override
  public String toString() {
    return "Курс %s, цена %s".formatted(getTitle(), getPrice());
  }
}

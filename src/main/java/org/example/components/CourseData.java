package org.example.components;

import org.example.support.GuiceScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CourseData extends AbsComponent {

  public CourseData(GuiceScoped scoped, WebElement root) {
    super(scoped, root);
  }

  public String getName() {
    return root.findElement(By.tagName("h3")).getText();
  }

  public int getPrice() {
    return Integer.parseInt(root.findElement(By.tagName("h4")).getText());
  }
}

package org.example.components;

import org.example.support.GuiceScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class UserData extends AbsComponent {

  public UserData(GuiceScoped scoped, WebElement root) {
    super(scoped, root);
  }

  public String getName() {
    return root.findElement(By.tagName("h3")).getText();
  }

  public String getCourse() {
    return root.findElement(By.tagName("h4")).getText();
  }

  public String getEmail() {
    return root.findElement(By.tagName("h5")).getText();
  }

  public int getAge() {
    return Integer.parseInt(root.findElement(By.tagName("h6")).getText());
  }
}

package org.example.components;

import org.example.AbsPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 .
 */
public class AbsComponent extends AbsPageObject {

  protected WebElement root;

  public AbsComponent(WebDriver driver, WebElement root) {
    super(driver);
    this.root = root;
  }
}

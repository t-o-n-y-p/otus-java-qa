package org.example.components;

import org.example.pageobject.AbsPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 .
 */
public class AbsComponent extends AbsPageObject {

  protected final WebElement root;

  public AbsComponent(WebDriver driver, WebElement root) {
    super(driver);
    this.root = root;
  }

  public void click() {
    actions.click(root);
  }
}
package org.example.components;

import org.example.pageobject.AbsPageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 .
 */
public abstract class AbsComponent extends AbsPageObject {

  protected final WebElement root;

  public AbsComponent(WebDriver driver, WebElement root) {
    super(driver);
    this.root = root;
  }

  public void click() {
    waiter.waitForCondition(ExpectedConditions.stalenessOf(root));
    actions.click(root);
  }
}

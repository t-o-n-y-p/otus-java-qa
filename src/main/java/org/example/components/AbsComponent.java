package org.example.components;

import org.example.pageobject.AbsPageObject;
import org.example.support.GuiceScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 .
 */
public abstract class AbsComponent extends AbsPageObject {

  protected final String xpath;

  public AbsComponent(WebDriver driver, String xpath) {
    super(driver);
    this.xpath = xpath;
  }

  public AbsComponent(GuiceScoped scoped, String xpath) {
    super(scoped);
    this.xpath = xpath;
  }

  protected WebElement getRoot() {
    return driver.findElement(By.xpath(xpath));
  }

  public void click() {
    waiter.waitForCondition(ExpectedConditions.stalenessOf(getRoot()));
    actions.click(getRoot());
  }
}

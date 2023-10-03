package org.example.pageobject;

import org.example.actions.CommonActions;
import org.example.waiters.CommonWaiter;
import org.openqa.selenium.WebDriver;

/**
 .
 */
public abstract class AbsPageObject {

  protected final WebDriver driver;
  protected final CommonActions actions;
  protected final CommonWaiter waiter;

  /**
   .
   */
  public AbsPageObject(WebDriver driver) {
    this.driver = driver;
    actions = new CommonActions(driver);
    waiter = new CommonWaiter(driver);
  }
}

package org.example.pageobject;

import com.google.inject.Inject;
import org.example.actions.CommonActions;
import org.example.support.GuiceScoped;
import org.example.waiters.CommonWaiter;
import org.openqa.selenium.WebDriver;

/**
 .
 */
public abstract class AbsPageObject {

  protected GuiceScoped scoped;

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

  /**
   .
   */
  @Inject
  public AbsPageObject(GuiceScoped scoped) {
    this.scoped = scoped;
    this.driver = scoped.getDriver();
    actions = new CommonActions(scoped);
    waiter = new CommonWaiter(driver);
  }
}

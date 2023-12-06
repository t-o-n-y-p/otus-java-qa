package org.example.pageobject;

import com.google.inject.Inject;
import org.example.support.GuiceScoped;
import org.openqa.selenium.WebDriver;

/**
 .
 */
public abstract class AbsPageObject {

  protected GuiceScoped scoped;

  protected final WebDriver driver;

  /**
   .
   */
  @Inject
  public AbsPageObject(GuiceScoped scoped) {
    this.scoped = scoped;
    this.driver = scoped.getDriver();
  }
}

package org.example.components;

import com.google.inject.Inject;
import org.example.pageobject.AbsPageObject;
import org.example.support.GuiceScoped;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 .
 */
public abstract class AbsComponent extends AbsPageObject {

  protected final WebElement root;

  public AbsComponent(WebDriver driver, WebElement root) {
    super(driver);
    this.root = root;
  }

  public AbsComponent(GuiceScoped scoped, WebElement root) {
    super(scoped);
    this.root = root;
  }

  public void click() {
    actions.click(root);
  }
}

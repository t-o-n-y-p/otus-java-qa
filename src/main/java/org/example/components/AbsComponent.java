package org.example.components;

import org.example.pageobject.AbsPageObject;
import org.example.support.GuiceScoped;
import org.openqa.selenium.WebElement;

/**
 * .
 */
public abstract class AbsComponent extends AbsPageObject {

  protected final WebElement root;

  public AbsComponent(GuiceScoped scoped, WebElement root) {
    super(scoped);
    this.root = root;
  }

}

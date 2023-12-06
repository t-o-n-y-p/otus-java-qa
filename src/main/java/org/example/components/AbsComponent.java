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

  protected final WebElement root;

  public AbsComponent(GuiceScoped scoped, WebElement root) {
    super(scoped);
    this.root = root;
  }

}

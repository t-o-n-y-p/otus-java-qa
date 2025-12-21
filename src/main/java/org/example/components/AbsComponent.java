package org.example.components;

import java.util.Optional;
import org.example.annotations.Component;
import org.example.exceptions.ComponentSelectorNotFoundException;
import org.example.pageobject.AbsPageObject;
import org.junit.platform.commons.support.AnnotationSupport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbsComponent extends AbsPageObject {

  public AbsComponent(WebDriver driver) {
    super(driver);
  }

  public WebElement getRoot() {
    return $(getSelector());
  }

  private By getSelector() {
    Class<?> clazz = getClass();
    Optional<Component> component = AnnotationSupport.findAnnotation(clazz, Component.class);
    if (component.isEmpty()) {
      throw new ComponentSelectorNotFoundException(clazz);
    }
    return component.get().type().getSelector(component.get().value());
  }
}

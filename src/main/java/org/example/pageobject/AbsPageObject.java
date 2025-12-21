package org.example.pageobject;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import org.example.waiters.CommonWaiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class AbsPageObject {

  protected final WebDriver driver;

  // protected final CommonActions actions;
  protected final CommonWaiter waiter;

  protected void clickFirstElementByPredicate(
      List<WebElement> elements, Predicate<WebElement> filter) {
    Optional<WebElement> element = elements.stream().filter(filter).findFirst();
    assertThat(element).as("Элемент по фильтру не найден на странице").isPresent();
    waiter.waitForCondition(ExpectedConditions.stalenessOf(element.get()));
    element.get().click();
    // actions.click(element.get());
  }

  public AbsPageObject(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    // actions = new CommonActions(driver);
    waiter = new CommonWaiter(driver);
  }

  public WebElement $(By selector) {
    return driver.findElement(selector);
  }
}

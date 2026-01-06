package org.example.pageobject;

import java.util.List;
import java.util.function.Predicate;
import org.example.waiters.CommonWaiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class AbsPageObject {

  protected final WebDriver driver;
  protected final CommonWaiter waiter;

  public AbsPageObject(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
    waiter = new CommonWaiter(driver);
  }

  public WebElement $(By selector) {
    return driver.findElement(selector);
  }

  protected void clickFirstElementByPredicate(
      List<WebElement> elements, Predicate<WebElement> filter) {
    for (WebElement element : elements) {
      if (filter.test(element)) {
        waiter.waitForCondition(ExpectedConditions.stalenessOf(element));
        element.click();
        return;
      }
    }
    throw new AssertionError("Элемент по фильтру не найден на странице");
  }
}

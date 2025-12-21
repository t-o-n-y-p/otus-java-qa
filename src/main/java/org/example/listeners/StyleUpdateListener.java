package org.example.listeners;

import com.google.inject.Singleton;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.events.WebDriverListener;

@Singleton
public class StyleUpdateListener implements WebDriverListener {

  /**
   * Слушатель для показа на занятиях по слушателям.
   *
   * @param driver - decorated WebDriver instance
   * @param locator - locator used to find the elements
   * @param result - list of found WebElements
   */
  @Override
  public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
    for (WebElement webElement : result) {
      ((JavascriptExecutor) driver)
          .executeScript(
              "arguments[0].setAttribute(\"style\",\"border:5px solid yellow\");", webElement);
    }
  }

  /**
   * Слушатель для показа на практике с actions.
   *
   * @param driver - decorated WebDriver instance
   * @param actions - sequence of actions to be performed
   */
  @Override
  public void beforePerform(WebDriver driver, Collection<Sequence> actions) {
    actions.stream()
        .flatMap(this::getOrigins)
        .forEach(
            origin ->
                ((JavascriptExecutor) driver)
                    .executeScript(
                        "arguments[0].setAttribute("
                            + "\"onmouseover\", \"style='border:5px solid red';\");"
                            + "arguments[0].setAttribute("
                            + "\"onclick\", \"style=null;\");",
                        origin));
  }

  @SuppressWarnings("unchecked")
  private Stream<WebElement> getOrigins(Sequence sequence) {
    Map<String, Object> encodedSequence = sequence.encode();
    if (encodedSequence.containsKey("actions")) {
      return ((List<Map<String, Object>>) encodedSequence.get("actions"))
          .stream()
              .filter(action -> action.get("origin") instanceof WebElement)
              .map(action -> (WebElement) action.get("origin"));
    }
    return Stream.empty();
  }
}

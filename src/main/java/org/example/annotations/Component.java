package org.example.annotations;

import java.util.function.Function;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;

public @interface Component {

  SelectorType type();

  String value();

  @AllArgsConstructor
  enum SelectorType {
    CSS(By::cssSelector),
    XPATH(By::xpath);

    private final Function<String, By> selectorFunction;

    public By getSelector(String value) {
      return selectorFunction.apply(value);
    }
  }
}

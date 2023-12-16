package org.example.components;

import static com.codeborne.selenide.appium.SelenideAppium.$x;

import com.codeborne.selenide.WebElementCondition;
import lombok.RequiredArgsConstructor;
import org.example.pageobject.AbsPageObject;

/**
 * .
 */
@RequiredArgsConstructor
public abstract class AbsComponent extends AbsPageObject {

  protected final String xpath;

  public boolean is(WebElementCondition condition) {
    return $x(xpath).is(condition);
  }

}

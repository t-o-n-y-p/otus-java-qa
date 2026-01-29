package org.example.components;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import lombok.AllArgsConstructor;
import org.example.pageobject.AbsPageObject;

@AllArgsConstructor
@SuppressWarnings("unchecked")
public abstract class AbsComponent<T extends AbsComponent<T>> extends AbsPageObject {

  protected final SelenideElement root;

  public T shouldBe(WebElementCondition... conditions) {
    root.shouldBe(conditions);
    return (T) this;
  }

}

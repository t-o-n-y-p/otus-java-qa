package org.example.components;

import org.example.annotations.Component;
import org.openqa.selenium.WebDriver;

@Component(type = Component.SelectorType.CSS, value = "daynews__main")
public class MainDayNewsComponent extends AbsComponent {

  public MainDayNewsComponent(WebDriver driver) {
    super(driver);
  }
}

package org.example.components;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.example.annotations.Component;
import org.openqa.selenium.WebDriver;

@Singleton
@Component(type = Component.SelectorType.CSS, value = "daynews__main")
public class MainDayNewsComponent extends AbsComponent {

  @Inject
  public MainDayNewsComponent(WebDriver driver) {
    super(driver);
  }
}

package org.example.components.popups;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.example.components.AbsComponent;
import org.openqa.selenium.WebDriver;

@Singleton
public class AuthPopup extends AbsComponent implements Popup<AuthPopup> {

  @Inject
  public AuthPopup(WebDriver driver) {
    super(driver);
  }

  @Override
  public AuthPopup shouldBeVisible() {
    return this;
  }

  @Override
  public AuthPopup shouldNotBeVisible() {
    return this;
  }
}

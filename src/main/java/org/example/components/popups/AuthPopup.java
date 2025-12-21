package org.example.components.popups;

import org.example.components.AbsComponent;
import org.openqa.selenium.WebDriver;

public class AuthPopup extends AbsComponent implements Popup<AuthPopup> {

  public AuthPopup(WebDriver driver) {
    super(driver);
  }

  @Override
  public AuthPopup shouldBeVisible() {
    return null;
  }

  @Override
  public AuthPopup shouldNotBeVisible() {
    return null;
  }
}

package org.example.factory.impl;

import org.openqa.selenium.firefox.FirefoxOptions;

/**
 .
 */
public class FirefoxSettings implements BrowserSettings<FirefoxOptions> {

  @Override
  public FirefoxOptions configureDriver() {
    return new FirefoxOptions();
  }
}

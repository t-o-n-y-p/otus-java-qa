package org.example.factory.impl;

import org.openqa.selenium.chrome.ChromeOptions;

/**
 * .
 */
public class ChromeSettings implements BrowserSettings<ChromeOptions> {

  @Override
  public ChromeOptions configureDriver() {
    return new ChromeOptions();
  }
}

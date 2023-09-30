package org.example.factory.impl;

import org.openqa.selenium.chrome.ChromeOptions;

/**
 .
 */
public class ChromeSettings implements BrowserSettings<ChromeOptions> {

  @Override
  public ChromeOptions configureDriver() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--start-fullscreen");
    chromeOptions.addArguments("--homepage=about:blank");
    chromeOptions.addArguments("--enable-extensions");
    return chromeOptions;
  }
}

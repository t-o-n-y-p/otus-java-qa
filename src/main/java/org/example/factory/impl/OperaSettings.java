package org.example.factory.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 .
 */
public class OperaSettings extends ChromeSettings {

  @Override
  public ChromeOptions configureDriver() {
    ChromeOptions chromeOptions = super.configureDriver();
    chromeOptions.setBinary(WebDriverManager.operadriver().getBrowserPath().toString());
    return chromeOptions;
  }
}

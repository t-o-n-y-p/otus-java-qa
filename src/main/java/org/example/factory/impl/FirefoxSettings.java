package org.example.factory.impl;

import java.time.Duration;
import java.util.Map;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 .
 */
public class FirefoxSettings implements BrowserSettings {

  @Override
  public DesiredCapabilities configureDriver() {
    DesiredCapabilities capabilities = new DesiredCapabilities(
        new FirefoxOptions()
            .setImplicitWaitTimeout(Duration.ofSeconds(10)));
    capabilities.setBrowserName("firefox");
    capabilities.setVersion("118.0");
    capabilities.setCapability(SELENOID_OPTIONS, Map.of(ENABLE_VNC, true, ENABLE_VIDEO, false));
    return capabilities;
  }
}

package org.example.factory.impl;

import java.util.Map;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * .
 */
public class ChromeSettings implements BrowserSettings {

  @Override
  public DesiredCapabilities configureDriver() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName("chrome");
    capabilities.setVersion("119.0");
    capabilities.setCapability(SELENOID_OPTIONS, Map.of(ENABLE_VNC, true, ENABLE_VIDEO, false));
    return capabilities;
  }
}

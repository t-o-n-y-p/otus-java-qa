package org.example.factory.impl;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 .
 */
public interface BrowserSettings {

  String SELENOID_OPTIONS = "selenoid:options";
  String ENABLE_VNC = "enableVNC";
  String ENABLE_VIDEO = "enableVideo";

  DesiredCapabilities configureDriver();

}

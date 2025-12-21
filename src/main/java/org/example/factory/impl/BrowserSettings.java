package org.example.factory.impl;

import org.openqa.selenium.Capabilities;

public interface BrowserSettings<T extends Capabilities> {

  T configureDriver();
}

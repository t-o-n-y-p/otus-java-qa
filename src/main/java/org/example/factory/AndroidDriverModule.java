package org.example.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;

@SuppressWarnings("unused")
public class AndroidDriverModule extends AbstractModule {

  @Provides
  @Singleton
  private WebDriver webDriver(AndroidDriverFactory factory) {
    return factory.create();
  }
}

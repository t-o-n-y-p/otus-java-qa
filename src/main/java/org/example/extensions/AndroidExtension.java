package org.example.extensions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.factory.AndroidDriverProvider;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AndroidExtension implements BeforeAllCallback, BeforeEachCallback, AfterEachCallback {

  private Injector injector;

  @Override
  public void beforeAll(ExtensionContext extensionContext) throws Exception {
    Configuration.browserSize = null;
    Configuration.browser = AndroidDriverProvider.class.getName();
    SelenideLogger.addListener(
            "AllureSelenide",
            new AllureSelenide()
                    .screenshots(true)
                    .savePageSource(true));
  }

  @Override
  public void beforeEach(ExtensionContext extensionContext) throws Exception {
    Selenide.open();
    extensionContext.getTestInstance()
        .ifPresent(instance -> {
          injector = Guice.createInjector(new AndroidModule());
          injector.injectMembers(instance);
        });
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) throws Exception {
    Selenide.closeWebDriver();
  }
}

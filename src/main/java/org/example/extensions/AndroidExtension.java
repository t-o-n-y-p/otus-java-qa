package org.example.extensions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.factory.AndroidDriverProvider;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class AndroidExtension implements BeforeAllCallback, BeforeEachCallback, AfterEachCallback, AfterTestExecutionCallback {

  private Injector injector;

  @Override
  public void beforeAll(ExtensionContext extensionContext) throws Exception {
    Configuration.browserSize = null;
    Configuration.browser = AndroidDriverProvider.class.getName();
    SelenideLogger.addListener(
            "AllureSelenide",
            new AllureSelenide().includeSelenideSteps(false));
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

  @Override
  public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
    extensionContext.getExecutionException()
            .ifPresent(e -> addScreenshot(Selenide.screenshot(OutputType.BYTES)));
  }

  @Attachment(value = "Screenshot", type = "image/png")
  private byte[] addScreenshot(byte[] screenshot) {
    return screenshot;
  }
}

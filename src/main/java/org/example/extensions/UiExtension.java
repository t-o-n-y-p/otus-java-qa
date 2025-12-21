package org.example.extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.factory.GuiceModule;
import org.example.factory.WebDriverFactory;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;
import org.openqa.selenium.WebDriver;

public class UiExtension
    implements TestInstancePostProcessor, AfterEachCallback, TestExecutionListener {

  private final ThreadLocal<Injector> injector = new ThreadLocal<>();

  @Override
  public void postProcessTestInstance(Object instance, ExtensionContext extensionContext) {
    Injector currentInjector = Guice.createInjector(new GuiceModule());
    this.injector.set(currentInjector);
    currentInjector.injectMembers(instance);
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) {
    Injector currentInjector = injector.get();
    if (currentInjector != null) {
      currentInjector.getInstance(WebDriver.class).quit();
    }
  }

  @Override
  public void testPlanExecutionStarted(TestPlan testPlan) {
    WebDriverFactory.init();
  }
}

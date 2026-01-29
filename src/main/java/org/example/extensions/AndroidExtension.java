package org.example.extensions;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.google.inject.Injector;
import org.example.factory.AndroidDriverFactory;
import org.example.factory.AndroidDriverModule;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.openqa.selenium.WebDriver;
import java.util.Objects;

@NullMarked
public class AndroidExtension
    implements TestInstancePostProcessor, BeforeEachCallback, AfterEachCallback {

  private final Injector parentInjector = InjectorProvider.getParent();
  private final ThreadLocal<@Nullable Injector> injectors = new ThreadLocal<>();

  @Override
  public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
    Injector currentInjector = parentInjector.createChildInjector(new AndroidDriverModule());
    injectors.set(currentInjector);
    currentInjector.injectMembers(testInstance);
  }

  @Override
  public void beforeEach(ExtensionContext context) {
    WebDriver driver = Objects.requireNonNull(injectors.get()).getInstance(WebDriver.class);
    WebDriverRunner.setWebDriver(driver);
    Selenide.open();
  }

  @Override
  public void afterEach(ExtensionContext extensionContext) {
    Injector currentInjector = Objects.requireNonNull(injectors.get());
    AndroidDriverFactory factory = currentInjector.getInstance(AndroidDriverFactory.class);
    WebDriver driver = currentInjector.getInstance(WebDriver.class);
    factory.quit(driver);
  }
}

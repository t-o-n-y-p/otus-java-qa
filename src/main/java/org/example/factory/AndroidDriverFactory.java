package org.example.factory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.remote.AutomationName;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.example.emulator.Emulator;
import org.example.emulator.EmulatorProvider;
import org.jspecify.annotations.NullMarked;
import org.openqa.selenium.WebDriver;

@NullMarked
@Singleton
@AllArgsConstructor(onConstructor_ = @Inject)
public class AndroidDriverFactory {

  private EmulatorProvider emulatorProvider;

  @SneakyThrows
  public WebDriver create() {
    Emulator emulator = emulatorProvider.takeAndGet();
    AndroidDriver driver =
        new AndroidDriver(
            new URL("http://127.0.0.1:%d/".formatted(emulator.getPort())),
            getCapabilities(emulator));
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    return driver;
  }

  @SneakyThrows
  public void quit(WebDriver driver) {
    emulatorProvider.putBack();
    driver.quit();
  }

  private UiAutomator2Options getCapabilities(Emulator emulator) {
    UiAutomator2Options options = new UiAutomator2Options();
    options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
    options.setPlatformName("Android");
    options.setApp("http://wiremock:8080/wishlist.apk");
    options.setOptionalIntentArguments("--es session_id " + emulator.getSessionId());
    options.fullReset();
    return options;
  }
}

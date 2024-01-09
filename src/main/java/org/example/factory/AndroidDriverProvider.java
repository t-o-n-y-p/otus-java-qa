package org.example.factory;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import javax.annotation.Nonnull;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public class AndroidDriverProvider implements WebDriverProvider {

  @Nonnull
  @Override
  @SneakyThrows
  public WebDriver createDriver(@Nonnull Capabilities capabilities) {
    UiAutomator2Options options = new UiAutomator2Options();
    options.merge(capabilities);
    options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
    options.setPlatformName("Android");
    options.setDeviceName("Pixel_3a_API_34_extension_level_7_x86_64");
    options.setPlatformVersion("14.0");
    options.setApp("http://localhost:50000/android/Andy.apk");
    options.setAppActivity(".MainActivity");
    options.fullReset();

    AndroidDriver driver = new AndroidDriver(new URL("http://192.168.56.1:4723/"), options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    return driver;
  }
}

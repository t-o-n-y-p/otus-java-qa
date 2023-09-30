package factory;

import exceptions.BrowserNotSupportedException;
import factory.impl.BrowserSettings;
import factory.impl.ChromeSettings;
import factory.impl.FirefoxSettings;
import factory.impl.OperaSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    private final String browserName = System.getProperty("browser", "chrome");

    public WebDriver create() {
        switch (browserName) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                BrowserSettings<ChromeOptions> browserSettings = new ChromeSettings();
                return new ChromeDriver(browserSettings.configureDriver());
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                BrowserSettings<FirefoxOptions> browserSettings = new FirefoxSettings();
                return new FirefoxDriver(browserSettings.configureDriver());
            }
            case "opera" -> {
                WebDriverManager.operadriver().setup();
                BrowserSettings<ChromeOptions> browserSettings = new OperaSettings();
                return new ChromeDriver(browserSettings.configureDriver());
            }
            default -> throw new BrowserNotSupportedException(browserName);
        }
    }

}

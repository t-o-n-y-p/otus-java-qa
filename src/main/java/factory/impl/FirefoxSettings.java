package factory.impl;

import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxSettings implements BrowserSettings<FirefoxOptions> {

    @Override
    public FirefoxOptions configureDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        return firefoxOptions;
    }
}

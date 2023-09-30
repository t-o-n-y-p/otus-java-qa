package factory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.openqa.selenium.WebDriver;

public class DriverModule extends AbstractModule {

    @Provides
    public WebDriver getDriver() {
        return new WebDriverFactory().create();
    }

}

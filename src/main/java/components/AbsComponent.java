package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobject.AbsPageObject;

public class AbsComponent extends AbsPageObject {

    protected WebElement root;

    public AbsComponent(WebDriver driver, WebElement root) {
        super(driver);
        this.root = root;
    }
}

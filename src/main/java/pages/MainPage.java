package pages;

import annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private List<WebElement> getProgramCards() {
        return driver.findElements(By.xpath("//section[./h2[text()='Специализации']]/div/div"));
    }
}

package org.example;

import java.util.List;
import org.example.annotations.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 .
 */
@Path("/")
public class MainPage extends AbsBasePage<MainPage> {

  public MainPage(WebDriver driver) {
    super(driver);
  }

  private List<WebElement> getProgramCards() {
    return driver.findElements(By.xpath("//section[./h2[text()='Специализации']]/div/div"));
  }
}

package org.example.pages;

import com.google.inject.Inject;
import org.example.annotations.Template;
import org.example.annotations.UrlTemplate;
import org.example.support.GuiceScoped;
import org.openqa.selenium.By;

@UrlTemplate(
    @Template(name = "score", template = "user/get/%d")
)
public class UserScorePage extends AbsBasePage<UserScorePage> {

  @Inject
  public UserScorePage(GuiceScoped scoped) {
    super(scoped);
  }

  public String getName() {
    return driver.findElement(By.tagName("h3")).getText();
  }

  public int getScore() {
    return Integer.parseInt(driver.findElement(By.tagName("h4")).getText());
  }
}

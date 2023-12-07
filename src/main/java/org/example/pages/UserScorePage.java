package org.example.pages;

import com.google.inject.Inject;
import org.assertj.core.api.SoftAssertions;
import org.example.annotations.Template;
import org.example.annotations.UrlTemplate;
import org.example.models.UserScore;
import org.example.support.GuiceScoped;
import org.openqa.selenium.By;

@UrlTemplate(
    @Template(name = "score", template = "user/get/%s")
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

  public void compareUserScoreTo(UserScore userScore) {
    SoftAssertions.assertSoftly(softly -> {
      softly.assertThat(getName())
              .as("User name is incorrect")
              .isEqualTo(userScore.getName());
      softly.assertThat(getScore())
              .as("User score is incorrect")
              .isEqualTo(userScore.getScore());
    });
  }
}

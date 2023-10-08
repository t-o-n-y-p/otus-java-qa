package org.example.pages;

import com.google.inject.Inject;
import org.example.annotations.Template;
import org.example.annotations.UrlTemplate;
import org.example.support.GuiceScoped;
import org.openqa.selenium.By;

/**
 .
 */
@UrlTemplate(
    @Template(name = "lesson", template = "lessons/%s")
)
public class LessonPage extends AbsBasePage<LessonPage> {

  @Inject
  public LessonPage(GuiceScoped scoped) {
    super(scoped);
  }

  public String getLessonName() {
    return driver.findElement(By.xpath("(//section)[2]//h1")).getText();
  }
}

package org.example.pages;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.example.annotations.UrlTemplate;
import org.openqa.selenium.WebDriver;

@Singleton
@UrlTemplate(value = "/news")
public class NewsPage extends AbsBasePage<NewsPage> {

  @Inject
  public NewsPage(WebDriver driver) {
    super(driver);
  }
}

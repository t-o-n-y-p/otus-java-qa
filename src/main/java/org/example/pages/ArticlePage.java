package org.example.pages;

import com.google.inject.Inject;
import org.example.annotations.UrlTemplate;
import org.openqa.selenium.WebDriver;

@UrlTemplate(template = "article/%1$s")
public class ArticlePage extends AbsBasePage<ArticlePage> {

  @Inject
  public ArticlePage(WebDriver driver) {
    super(driver);
  }
}

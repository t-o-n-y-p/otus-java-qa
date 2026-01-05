package org.example;

import com.google.inject.Inject;
import org.example.extensions.UiExtension;
import org.example.pages.ArticlePage;
import org.example.pages.MainPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings("unused")
@ExtendWith(UiExtension.class)
public class MainPageTest {

  @Inject private MainPage mainPage;
  @Inject private ArticlePage articlePage;

  @Test
  void checkClickArticleTile() {
    String title = mainPage.open().getPhotoTitleByIndex(1);
    mainPage.clickArticleByTitle(title);
    articlePage.pageHeaderShouldBeSameAs(title);
  }
}

package org.example;

import com.google.inject.Inject;
import org.example.extensions.UiExtension;
import org.example.pages.MainPage;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings("unused")
@ExtendWith(UiExtension.class)
public class MainPageTest {

  @Inject private MainPage mainPage;

  @RepeatedTest(3)
  void checkClickArticleTile() {
    String title = mainPage.open().getPhotoTitleByIndex(1);
    mainPage.clickArticleByTitle(title).pageHeaderShouldBeSameAs(title);
  }
}

package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import com.google.inject.Singleton;
import org.example.components.WishlistItem;
import org.example.components.WishlistsContent;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;
import static io.appium.java_client.AppiumBy.id;

@Singleton
public class MyWishlistsPage extends AbsBasePage {

  private final WishlistsContent wishlistsContent =
      new WishlistsContent($(id("ru.otus.wishlist:id/wishlists")));

  public MyWishlistsPage assertNumberOfWishlists(int value) {
    wishlistsContent
        .shouldBe(visible.because("Список списков желаний не виден на экране"))
        .assertSizeEqualTo(value);
    return this;
  }

  public MyWishlistsPage assertWishlistTitle(int index, String expected) {
    getWishlistItem(index).assertTitleEqualsTo(expected);
    return this;
  }

  public MyWishlistsPage assertWishlistSubtitle(int index, String expected) {
    getWishlistItem(index).assertSubtitleEqualsTo(expected);
    return this;
  }

  public void tapEditWishlist(int index) {
    getWishlistItem(index).tapEdit();
  }

  private WishlistItem getWishlistItem(int index) {
    return wishlistsContent.get(index)
        .shouldBe(visible.because("Список желаний номер %d не виден на экране".formatted(index)));
  }
}

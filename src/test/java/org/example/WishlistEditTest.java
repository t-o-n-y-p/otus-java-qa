package org.example;

import com.google.inject.Inject;
import org.example.extensions.AndroidExtension;
import org.example.pages.EditWishlistPage;
import org.example.pages.LoginPage;
import org.example.pages.MyWishlistsPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@SuppressWarnings("unused")
@ExtendWith(AndroidExtension.class)
public class WishlistEditTest {

  @Inject private LoginPage loginPage;
  @Inject private MyWishlistsPage myWishlistsPage;
  @Inject private EditWishlistPage editWishlistPage;

  @Test
  void editWishlist() {
    loginPage.login("tonyp90", "12345678");
    String wishlistTitle = "Новый год";
    String newWishlistDescription = "К нам уже не мчится";
    myWishlistsPage
        .assertNumberOfWishlists(1)
        .assertWishlistTitle(1, wishlistTitle)
        .assertWishlistSubtitle(1, "К нам мчится, скоро все случится")
        .tapEditWishlist(1);
    editWishlistPage
        .assertEditWishlistTitle("Изменить список желаний")
        .editDescription(newWishlistDescription);
    myWishlistsPage
        .assertNumberOfWishlists(1)
        .assertWishlistTitle(1, wishlistTitle)
        .assertWishlistSubtitle(1, newWishlistDescription);
  }
}

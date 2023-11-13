package org.example;

import static org.hamcrest.Matchers.equalTo;

import com.google.inject.Inject;
import org.example.clients.PetClient;
import org.example.clients.UserClient;
import org.example.extensions.PetStoreExtension;
import org.example.petstore.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PetStoreExtension.class)
@SuppressWarnings("unused")
public class PetStoreNegativeTest {

  @Inject
  private User user;

  @Inject
  private UserClient userClient;

  @Inject
  private PetClient petClient;

  /**
   Тест проверяет, что пользователь не создается с пустым телом запроса
   (без всех параметров).
   */
  @Test
  public void testCreateEmptyUser() {
    userClient
        .postUser(user)
        .body("code", equalTo(200))
        .body("message", equalTo("0"));
  }

  /**
   Тест проверяет, что при получении питомцев по несуществующему статусу
   возвращается пустой список.
   */
  @Test
  public void testGetPetByInvalidStatus() {
    petClient
        .findPetsByStatus("abcde")
        .body(equalTo("[]"));
  }

}

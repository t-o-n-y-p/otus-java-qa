package org.example;

import static org.hamcrest.Matchers.equalTo;

import com.google.inject.Inject;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.example.clients.PetClient;
import org.example.clients.UserClient;
import org.example.extensions.PetStoreExtension;
import org.example.petstore.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PetStoreExtension.class)
@SuppressWarnings("unused")
@Epic("Тесты PetStore API")
@Story("Негативные тесты")
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
  @DisplayName("Проверка создания пользователя с пустым телом запроса")
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
  @DisplayName("Проверка поиска питомцев в несуществующем статусе")
  public void testGetPetByInvalidStatus() {
    petClient
        .findPetsByStatus("abcde")
        .body(equalTo("[]"));
  }

}

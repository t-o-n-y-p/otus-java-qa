package org.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import com.google.inject.Inject;
import io.restassured.common.mapper.TypeRef;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.example.clients.PetClient;
import org.example.clients.UserClient;
import org.example.extensions.PetStoreExtension;
import org.example.petstore.Pet;
import org.example.petstore.PetStatus;
import org.example.petstore.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 .
 */
@ExtendWith(PetStoreExtension.class)
@SuppressWarnings("unused")
public class PetStorePositiveTest {

  @Inject
  private User user;

  @Inject
  private Pet pet;

  @Inject
  private UserClient userClient;

  @Inject
  private PetClient petClient;

  /**
   Тест проверяет, что можно создать пользователя с полным телом запроса
   (со всеми параметрами).
   */
  @Test
  public void testCreateFullUser() {
    String message =
        userClient
            .postUser(
                user
                    .setUsername("JSMITH" + System.currentTimeMillis())
                    .setFirstName("John")
                    .setLastName("Smith")
                    .setEmail("jsmith@example.com")
                    .setPassword("password")
                    .setPhone("phone")
                    .setUserStatus(0))
            .body("code", equalTo(200))
            .body("message", not(equalTo("0")))
            .extract()
            .jsonPath()
            .getString("message");
    user.setId(Long.parseLong(message));
    User actualUser =
        userClient
            .getUserByUsername(user.getUsername())
            .extract()
            .as(User.class);
    assertThat(actualUser)
        .as("Полученный пользователь не совпадает с созданным пользователем")
        .isEqualTo(user);
  }

  /**
   Тест создает питомца с заданным статусом
   и проверяет, что при получении питомцев по заданному статусу:
   1) в списке присутствует созданный питомец;
   2) в списке присутствуют только питомцы с заданным статусом.
   */
  @ParameterizedTest
  @EnumSource(PetStatus.class)
  public void testGetPetByValidStatus(PetStatus status) {
    Pet createdPet =
        petClient
            .postPet(pet.setStatus(status))
            .extract()
            .as(Pet.class);
    pet.setId(createdPet.getId());
    await()
        .untilAsserted(() -> {
          List<Pet> pets =
              petClient
                  .findPetsByStatus(status)
                  .extract()
                  .as(new TypeRef<>(){});
          SoftAssertions.assertSoftly(softly ->
              softly.assertThat(pets)
                  .as("Среди полученных питомцев не содержится созданный")
                  .contains(createdPet)
                  .as("Не все полученные питомцы удовлетворяют условиям")
                  .allSatisfy(pet -> softly.assertThat(pet.getStatus()).isEqualTo(status)));
        });
  }


}

package org.example;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import com.google.inject.Inject;
import io.restassured.common.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.example.annotations.FindPetsByStatus;
import org.example.annotations.PostPet;
import org.example.annotations.PostUser;
import org.example.annotations.UserByUsername;
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
  @PostUser
  private RequestSpecification postUserRequestSpecification;

  @Inject
  @PostUser
  private ResponseSpecification postUserResponseSpecification;

  @Inject
  @UserByUsername
  private RequestSpecification userByUsernameRequestSpecification;

  @Inject
  @UserByUsername
  private ResponseSpecification userByUsernameResponseSpecification;

  @Inject
  @PostPet
  private RequestSpecification postPetRequestSpecification;

  @Inject
  @PostPet
  private ResponseSpecification postPetResponseSpecification;

  @Inject
  @FindPetsByStatus
  private RequestSpecification findPetsByStatusRequestSpecification;

  @Inject
  @FindPetsByStatus
  private ResponseSpecification findPetsByStatusResponseSpecification;

  /**
   Тест проверяет, что можно создать пользователя с полным телом запроса
   (со всеми параметрами).
   */
  @Test
  public void testCreateFullUser() {
    String message =
        given(postUserRequestSpecification)
            .body(user
                .setUsername("JSMITH" + System.currentTimeMillis())
                .setFirstName("John")
                .setLastName("Smith")
                .setEmail("jsmith@example.com")
                .setPassword("password")
                .setPhone("phone")
                .setUserStatus(0))
            .when()
            .post()
            .then()
            .spec(postUserResponseSpecification)
            .body("code", equalTo(200))
            .body("message", not(equalTo("0")))
            .extract()
            .jsonPath()
            .getString("message");
    user.setId(Long.parseLong(message));
    User actualUser =
        given(userByUsernameRequestSpecification)
            .when()
            .pathParam("username", user.getUsername())
            .get()
            .then()
            .spec(userByUsernameResponseSpecification)
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
        given(postPetRequestSpecification)
            .body(pet.setStatus(status))
            .when()
            .post()
            .then()
            .spec(postPetResponseSpecification)
            .extract()
            .as(Pet.class);
    pet.setId(createdPet.getId());
    List<Pet> pets =
        given(findPetsByStatusRequestSpecification)
            .when()
            .queryParam("status", status.name().toLowerCase())
            .get()
            .then()
            .spec(findPetsByStatusResponseSpecification)
            .extract()
            .as(new TypeRef<>(){});
    SoftAssertions.assertSoftly(softly ->
        softly.assertThat(pets)
            .as("Среди полученных питомцев не содержится созданный")
            .contains(createdPet)
            .as("Не все полученные питомцы удовлетворяют условиям")
            .allSatisfy(pet -> softly.assertThat(pet.getStatus()).isEqualTo(status)));

  }


}

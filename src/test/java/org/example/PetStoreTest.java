package org.example;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

import com.google.inject.Inject;
import io.restassured.common.mapper.TypeRef;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.List;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.example.extensions.PetStoreExtension;
import org.example.petstore.Pet;
import org.example.petstore.PetStatus;
import org.example.petstore.User;
import org.example.utils.PetStorePath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 .
 */
@ExtendWith(PetStoreExtension.class)
public class PetStoreTest {

  @Inject
  private User user;

  @Inject
  private Pet pet;

  @Inject
  private RequestSpecification requestSpecification;

  @Inject
  private ResponseSpecification responseSpecification;

  /**
   Тест проверяет, что пользователь не создается с пустым телом запроса
   (без всех параметров).
   */
  @Test
  public void testCreateEmptyUser() {
    given(requestSpecification)
        .body(user)
        .when()
        .post(PetStorePath.POST_USER)
        .then()
        .spec(responseSpecification)
        .statusCode(equalTo(HttpStatus.SC_OK))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/apiResponse.json"))
        .body("code", equalTo(200))
        .body("message", equalTo("0"));
  }

  /**
   Тест проверяет, что можно создать пользователя с полным телом запроса
   (со всеми параметрами).
   */
  @Test
  public void testCreateFullUser() {
    String message = given(requestSpecification)
        .body(user
            .setUsername("JSMITH" + System.currentTimeMillis())
            .setFirstName("John")
            .setLastName("Smith")
            .setEmail("jsmith@example.com")
            .setPassword("password")
            .setPhone("phone")
            .setUserStatus(0))
        .when()
        .post(PetStorePath.POST_USER)
        .then()
        .spec(responseSpecification)
        .statusCode(equalTo(HttpStatus.SC_OK))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/apiResponse.json"))
        .body("code", equalTo(200))
        .body("message", not(equalTo("0")))
        .extract()
        .jsonPath()
        .getString("message");
    user.setId(Long.parseLong(message));
    User actualUser = given(requestSpecification)
        .when()
        .pathParam("username", user.getUsername())
        .get(PetStorePath.USER_BY_USERNAME)
        .then()
        .spec(responseSpecification)
        .statusCode(equalTo(HttpStatus.SC_OK))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/getUser.json"))
        .extract()
        .as(User.class);
    assertThat(actualUser)
        .as("Полученный пользователь не совпадает с созданным пользователем")
        .isEqualTo(user);
  }

  /**
   Тест проверяет, что при получении питомцев по несуществующему статусу
   возвращается пустой список.
   */
  @Test
  public void testGetPetByInvalidStatus() {
    given(requestSpecification)
        .when()
        .queryParam("status", "abcde")
        .get(PetStorePath.FIND_PET_BY_STATUS)
        .then()
        .spec(responseSpecification)
        .statusCode(equalTo(HttpStatus.SC_OK))
        .body(equalTo("[]"));
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
    Pet createdPet = given(requestSpecification)
        .body(pet.setStatus(status))
        .when()
        .post(PetStorePath.POST_PET)
        .then()
        .spec(responseSpecification)
        .statusCode(equalTo(HttpStatus.SC_OK))
        .extract()
        .as(Pet.class);
    pet.setId(createdPet.getId());
    List<Pet> pets =
        given(requestSpecification)
            .when()
            .queryParam("status", status.name().toLowerCase())
            .get(PetStorePath.FIND_PET_BY_STATUS)
            .then()
            .spec(responseSpecification)
            .statusCode(equalTo(HttpStatus.SC_OK))
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

package org.example;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.google.inject.Inject;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.example.annotations.FindPetsByStatus;
import org.example.annotations.PostUser;
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
  @PostUser
  private RequestSpecification postUserRequestSpecification;

  @Inject
  @PostUser
  private ResponseSpecification postUserResponseSpecification;

  @Inject
  @FindPetsByStatus
  private RequestSpecification findPetsByStatusRequestSpecification;

  @Inject
  @FindPetsByStatus
  private ResponseSpecification findPetsByStatusResponseSpecification;

  /**
   Тест проверяет, что пользователь не создается с пустым телом запроса
   (без всех параметров).
   */
  @Test
  public void testCreateEmptyUser() {
    given(postUserRequestSpecification)
        .body(user)
        .when()
        .post()
        .then()
        .spec(postUserResponseSpecification)
        .body("code", equalTo(200))
        .body("message", equalTo("0"));
  }

  /**
   Тест проверяет, что при получении питомцев по несуществующему статусу
   возвращается пустой список.
   */
  @Test
  public void testGetPetByInvalidStatus() {
    given(findPetsByStatusRequestSpecification)
        .when()
        .queryParam("status", "abcde")
        .get()
        .then()
        .spec(findPetsByStatusResponseSpecification)
        .body(equalTo("[]"));
  }

}

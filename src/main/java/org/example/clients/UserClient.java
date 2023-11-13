package org.example.clients;

import static io.restassured.RestAssured.given;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.example.petstore.User;

public class UserClient extends BaseClient {

  @Override
  protected String getBasePath() {
    return "/user";
  }

  public ValidatableResponse postUser(User user) {
    return given(getDefaultRequestSpecification())
        .body(user)
        .when()
        .post()
        .then()
        .spec(getDefaultResponseSpecification())
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/apiResponse.json"));
  }

  public ValidatableResponse getUserByUsername(String username) {
    return given(getDefaultRequestSpecification())
        .when()
        .pathParam("username", username)
        .get("/{username}")
        .then()
        .spec(getDefaultResponseSpecification())
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/getUser.json"));
  }

  public void deleteUser(String username) {
    given(getDefaultRequestSpecification())
        .when()
        .pathParam("username", username)
        .delete("/{username}");
  }

}

package org.example.extensions.modules;

import static org.hamcrest.Matchers.equalTo;

import com.google.inject.Provides;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.example.annotations.PostUser;
import org.example.annotations.UserByUsername;
import org.example.petstore.User;

public class UserModule extends BaseModule {

  private final User user = new User();

  @Provides
  public User getUser() {
    return user;
  }

  @Provides
  @PostUser
  public RequestSpecification getPostUserRequestSpecification() {
    return getRequestSpecification()
        .basePath("/user");
  }

  @Provides
  @PostUser
  public ResponseSpecification getPostUserResponseSpecification() {
    return getResponseSpecification()
        .statusCode(equalTo(HttpStatus.SC_OK))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/apiResponse.json"));
  }

  @Provides
  @UserByUsername
  public RequestSpecification getUserByUsernameRequestSpecification() {
    return getRequestSpecification()
        .basePath("/user/{username}");
  }

  @Provides
  @UserByUsername
  public ResponseSpecification getUserByUsernameResponseSpecification() {
    return getResponseSpecification()
        .statusCode(equalTo(HttpStatus.SC_OK))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/getUser.json"));
  }

}

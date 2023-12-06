package org.example.clients;

import static io.restassured.RestAssured.given;

import com.google.inject.Inject;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.example.support.GuiceScoped;
import org.wiremock.integrations.testcontainers.WireMockContainer;

public class JsonUserMockClient extends BaseClient {

  public JsonUserMockClient(WireMockContainer wireMockContainer) {
    super(wireMockContainer);
  }

  @Inject
  public JsonUserMockClient(GuiceScoped scoped) {
    super(scoped);
  }

  @Override
  protected String getBasePath() {
    return "/api/user";
  }

  @Override
  protected ContentType getContentType() {
    return ContentType.JSON;
  }

  public ValidatableResponse getAllUsers() {
    return given(getDefaultRequestSpecification())
        .when()
        .get("/get/all")
        .then()
        .spec(getDefaultResponseSpecification())
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/users.json"));
  }

  public ValidatableResponse getUserScore(int id) {
    return given(getDefaultRequestSpecification())
        .when()
        .pathParam("id", id)
        .get("/get/{id}")
        .then()
        .spec(getDefaultResponseSpecification())
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/score.json"));
  }
}

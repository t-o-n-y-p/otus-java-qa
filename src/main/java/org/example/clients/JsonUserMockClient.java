package org.example.clients;

import com.google.inject.Inject;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.example.containers.WireMockContainer;

import static io.restassured.RestAssured.given;

public class JsonUserMockClient extends BaseClient {

    @Inject
    public JsonUserMockClient(WireMockContainer wireMockContainer) {
        super(wireMockContainer);
    }

    @Override
    protected String getBasePath() {
        return "/user";
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

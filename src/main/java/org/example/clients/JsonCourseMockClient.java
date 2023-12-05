package org.example.clients;

import com.google.inject.Inject;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.example.containers.WireMockContainer;

import static io.restassured.RestAssured.given;

public class JsonCourseMockClient extends BaseClient {

    @Inject
    public JsonCourseMockClient(WireMockContainer wireMockContainer) {
        super(wireMockContainer);
    }

    @Override
    protected String getBasePath() {
        return "/course";
    }

    @Override
    protected ContentType getContentType() {
        return ContentType.JSON;
    }

    public ValidatableResponse getAllCourses() {
        return given(getDefaultRequestSpecification())
                .when()
                .get("/get/all")
                .then()
                .spec(getDefaultResponseSpecification())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/courses.json"));
    }
}

package org.example.clients;

import com.google.inject.Inject;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;
import org.example.support.GuiceScoped;
import org.wiremock.integrations.testcontainers.WireMockContainer;

import static io.restassured.RestAssured.given;

public class JsonCourseMockClient extends BaseClient {

    public JsonCourseMockClient(WireMockContainer wireMockContainer) {
        super(wireMockContainer);
    }

    @Inject
    public JsonCourseMockClient(GuiceScoped scoped) {
        super(scoped);
    }

    @Override
    protected String getBasePath() {
        return "/api/course";
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

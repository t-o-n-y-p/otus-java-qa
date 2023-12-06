package org.example.clients;

import com.google.inject.Inject;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.example.containers.WireMockContainer;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;

public class XmlCourseMockClient extends BaseClient {

    @Inject
    public XmlCourseMockClient(WireMockContainer wireMockContainer) {
        super(wireMockContainer);
    }

    @Override
    protected String getBasePath() {
        return "/soap";
    }

    @Override
    protected ContentType getContentType() {
        return ContentType.XML;
    }

    public ValidatableResponse getAllCourses() {
        return given(getDefaultRequestSpecification())
                .when()
                .headers(Map.of(SOAP_ACTION, "/CourseGetAll"))
                .post()
                .then()
                .spec(getDefaultResponseSpecification())
                .body(matchesXsdInClasspath("schema/courses.xsd"));
    }
}

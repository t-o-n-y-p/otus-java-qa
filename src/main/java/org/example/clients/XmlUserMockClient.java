package org.example.clients;

import com.google.inject.Inject;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.example.support.GuiceScoped;
import org.wiremock.integrations.testcontainers.WireMockContainer;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;

public class XmlUserMockClient extends BaseClient {

    public XmlUserMockClient(WireMockContainer wireMockContainer) {
        super(wireMockContainer);
    }

    @Inject
    public XmlUserMockClient(GuiceScoped scoped) {
        super(scoped);
    }

    @Override
    protected String getBasePath() {
        return "/soap";
    }

    @Override
    protected ContentType getContentType() {
        return ContentType.XML;
    }

    public ValidatableResponse getAllUsers() {
        return given(getDefaultRequestSpecification())
                .when()
                .headers(Map.of(SOAP_ACTION, "/UserGetAll"))
                .post()
                .then()
                .spec(getDefaultResponseSpecification())
                .body(matchesXsdInClasspath("/schema/users.xsd"));
    }

    public ValidatableResponse getUserScore(int id) {
        return given(getDefaultRequestSpecification())
                .when()
                .headers(Map.of(SOAP_ACTION, "/UserGetOne"))
                .body("""
                        <soap:Envelope xmlns:soap="http://www.w3.org/2003/05/soap-envelope/">
                            <soap:Body>
                                <soap:UserGetOne>
                                    <soap:Id>%d</soap:Id>
                                </soap:UserGetOne>
                            </soap:Body>
                        </soap:Envelope>
                        """.formatted(id))
                .post()
                .then()
                .spec(getDefaultResponseSpecification())
                .body(matchesXsdInClasspath("/schema/score.xsd"));
    }
}

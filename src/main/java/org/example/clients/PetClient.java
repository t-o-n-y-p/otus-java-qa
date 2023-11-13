package org.example.clients;

import static io.restassured.RestAssured.given;

import io.restassured.response.ValidatableResponse;
import org.example.petstore.Pet;
import org.example.petstore.PetStatus;

public class PetClient extends BaseClient {

  @Override
  protected String getBasePath() {
    return "/pet";
  }

  public ValidatableResponse postPet(Pet pet) {
    return given(getDefaultRequestSpecification())
        .body(pet)
        .when()
        .post()
        .then()
        .spec(getDefaultResponseSpecification());
  }

  public ValidatableResponse findPetsByStatus(PetStatus status) {
    return findPetsByStatus(status.name().toLowerCase());
  }

  public ValidatableResponse findPetsByStatus(String status) {
    return given(getDefaultRequestSpecification())
        .when()
        .queryParam("status", status)
        .get("/findByStatus")
        .then()
        .spec(getDefaultResponseSpecification());
  }

  public void deletePet(Long id) {
    given(getDefaultRequestSpecification())
        .when()
        .pathParam("petId", id)
        .delete("/{petId}");
  }

}

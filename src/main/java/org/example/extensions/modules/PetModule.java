package org.example.extensions.modules;

import static org.hamcrest.Matchers.equalTo;

import com.google.inject.Provides;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.example.annotations.FindPetsByStatus;
import org.example.annotations.PetById;
import org.example.annotations.PostPet;
import org.example.petstore.Pet;

public class PetModule extends BaseModule {

  private final Pet pet = new Pet();

  @Provides
  public Pet getPet() {
    return pet;
  }

  @Provides
  @PostPet
  public RequestSpecification getPostPetRequestSpecification() {
    return getRequestSpecification()
        .basePath("/pet");
  }

  @Provides
  @PostPet
  public ResponseSpecification getPostPetResponseSpecification() {
    return getResponseSpecification()
        .statusCode(equalTo(HttpStatus.SC_OK));
  }

  @Provides
  @FindPetsByStatus
  public RequestSpecification getFindPetsByStatusRequestSpecification() {
    return getRequestSpecification()
        .basePath("/pet/findByStatus");
  }

  @Provides
  @FindPetsByStatus
  public ResponseSpecification getFindPetsByStatusResponseSpecification() {
    return getResponseSpecification()
        .statusCode(equalTo(HttpStatus.SC_OK));
  }

  @Provides
  @PetById
  public RequestSpecification getPetByIdRequestSpecification() {
    return getRequestSpecification()
        .basePath("/pet/{petId}");
  }

}

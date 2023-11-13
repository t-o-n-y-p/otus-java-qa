package org.example.extensions.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.clients.PetClient;
import org.example.petstore.Pet;

@SuppressWarnings("unused")
public class PetModule extends AbstractModule {

  private final Pet pet = new Pet();

  @Provides
  public Pet getPet() {
    return pet;
  }

  @Provides
  @Singleton
  public PetClient getPetClient() {
    return new PetClient();
  }

}

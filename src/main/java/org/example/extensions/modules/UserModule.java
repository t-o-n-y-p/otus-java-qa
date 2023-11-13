package org.example.extensions.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.example.clients.UserClient;
import org.example.petstore.User;

@SuppressWarnings("unused")
public class UserModule extends AbstractModule {

  private final User user = new User();

  @Provides
  public User getUser() {
    return user;
  }

  @Provides
  @Singleton
  public UserClient getUserClient() {
    return new UserClient();
  }

}

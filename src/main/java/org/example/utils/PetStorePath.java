package org.example.utils;

import lombok.experimental.UtilityClass;

/**\
 .
 */
@UtilityClass
public class PetStorePath {

  public static final String BASE_PATH = System.getProperty("base.url");
  public static final String POST_USER = "/user";
  public static final String USER_BY_USERNAME = "/user/{username}";
  public static final String POST_PET = "/pet";
  public static final String PET_BY_ID = "/pet/{petId}";
  public static final String FIND_PET_BY_STATUS = "/pet/findByStatus";

}

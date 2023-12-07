package org.example.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(namespace = "soap", localName = "UserGetOneResponse")
public class UserGetOneResponse {

  @JacksonXmlProperty(namespace = "soap", localName = "UserScore")
  private UserScore userScore;

}

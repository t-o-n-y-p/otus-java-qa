package org.example.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;
import lombok.Data;

@Data
@JacksonXmlRootElement(namespace = "soap", localName = "Body")
public class UserGetAllResponse {

  @JacksonXmlProperty(namespace = "soap", localName = "User")
  @JacksonXmlElementWrapper(namespace = "soap", localName = "Users")
  private List<UserData> userData;

}

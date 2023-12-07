package org.example.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(namespace = "soap", localName = "UserScore")
public class UserScore {

  @JacksonXmlProperty(namespace = "soap", localName = "Name")
  private String name;
  @JacksonXmlProperty(namespace = "soap", localName = "Score")
  private Integer score;

}

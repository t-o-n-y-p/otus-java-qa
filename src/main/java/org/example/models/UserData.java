package org.example.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(namespace = "soap", localName = "User")
public class UserData {

  @JacksonXmlProperty(namespace = "soap", localName = "Name")
  private String name;
  @JacksonXmlProperty(namespace = "soap", localName = "Course")
  private String course;
  @JacksonXmlProperty(namespace = "soap", localName = "Email")
  private String email;
  @JacksonXmlProperty(namespace = "soap", localName = "Age")
  private Integer age;

}

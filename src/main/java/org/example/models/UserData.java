package org.example.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(namespace = "soap", name = "User")
public class UserData {

  @XmlElement(namespace = "soap", name = "Name")
  private String name;
  @XmlElement(namespace = "soap", name = "Course")
  private String course;
  @XmlElement(namespace = "soap", name = "Email")
  private String email;
  @XmlElement(namespace = "soap", name = "Age")
  private Integer age;

}

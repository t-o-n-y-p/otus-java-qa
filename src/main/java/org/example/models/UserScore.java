package org.example.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(namespace = "soap", name = "Score")
public class UserScore {

  @XmlElement(namespace = "soap", name = "Name")
  private String name;
  @XmlElement(namespace = "soap", name = "Score")
  private Integer score;

}

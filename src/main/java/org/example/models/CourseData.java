package org.example.models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(namespace = "soap", name = "Course")
public class CourseData {

  @XmlElement(namespace = "soap", name = "Name")
  private String name;
  @XmlElement(namespace = "soap", name = "Price")
  private Integer price;

}

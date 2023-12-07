package org.example.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(namespace = "soap", localName = "Course")
public class CourseData {

  @JacksonXmlProperty(namespace = "soap", localName = "Name")
  private String name;
  @JacksonXmlProperty(namespace = "soap", localName = "Price")
  private Integer price;

}

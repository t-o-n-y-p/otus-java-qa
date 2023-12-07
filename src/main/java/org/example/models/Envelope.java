package org.example.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(namespace = "soap", localName = "Envelope")
public class Envelope {

  @JacksonXmlProperty(namespace = "soap", localName = "Body")
  private Body body;

}

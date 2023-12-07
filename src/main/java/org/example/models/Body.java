package org.example.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(namespace = "soap", localName = "Body")
public class Body {

    @JacksonXmlProperty(namespace = "soap", localName = "UserGetAllResponse")
    private UserGetAllResponse userGetAllResponse;
    @JacksonXmlProperty(namespace = "soap", localName = "UserGetOneResponse")
    private UserGetOneResponse userGetOneResponse;
    @JacksonXmlProperty(namespace = "soap", localName = "CourseGetAllResponse")
    private CourseGetAllResponse courseGetAllResponse;

}

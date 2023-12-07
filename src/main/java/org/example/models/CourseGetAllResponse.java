package org.example.models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(namespace = "soap", localName = "CourseGetAllResponse")
public class CourseGetAllResponse {

    @JacksonXmlProperty(namespace = "soap", localName = "Course")
    @JacksonXmlElementWrapper(namespace = "soap", localName = "Courses")
    private List<CourseData> courseData;

}

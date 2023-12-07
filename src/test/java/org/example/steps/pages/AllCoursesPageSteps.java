package org.example.steps.pages;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import io.restassured.common.mapper.TypeRef;
import io.restassured.internal.mapping.Jackson2Mapper;
import org.example.clients.JsonCourseMockClient;
import org.example.clients.XmlCourseMockClient;
import org.example.models.*;
import org.example.pages.AllCoursesPage;

import java.util.List;

public class AllCoursesPageSteps {

    @Inject
    private AllCoursesPage allCoursesPage;

    @Inject
    private JsonCourseMockClient jsonCourseMockClient;

    @Inject
    private XmlCourseMockClient xmlCourseMockClient;

    @Если("Я открываю страницу со списком курсов")
    public void open() {
        allCoursesPage.open();
    }

    @Тогда("Список курсов актуален JSON")
    public void checkUsersJson() {
        List<CourseData> expectedCourseData =
                jsonCourseMockClient.getAllCourses().extract().as(new TypeRef<>() {
                });
        allCoursesPage.compareCourseDataTo(expectedCourseData);
    }

    @Тогда("Список курсов актуален XML")
    public void checkUsersXml() {
        List<CourseData> expectedUserData =
                xmlCourseMockClient.getAllCourses().extract()
                        .as(Envelope.class, new Jackson2Mapper((t, s) -> new XmlMapper()))
                        .getBody()
                        .getCourseGetAllResponse()
                        .getCourseData();
        allCoursesPage.compareCourseDataTo(expectedUserData);
    }

}

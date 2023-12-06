package org.example.pages;

import com.google.inject.Inject;
import org.example.annotations.Path;
import org.example.components.CourseData;
import org.example.support.GuiceScoped;
import org.openqa.selenium.By;

import java.util.List;

@Path("course/get/all")
public class AllCoursesPage extends AbsBasePage<AllCoursesPage> {

    @Inject
    public AllCoursesPage(GuiceScoped scoped) {
        super(scoped);
    }

    public List<CourseData> getCourseData() {
        return driver.findElements(By.tagName("td"))
                .stream()
                .map(e -> new CourseData(scoped, e))
                .toList();
    }
}

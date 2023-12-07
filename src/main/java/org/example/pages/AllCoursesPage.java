package org.example.pages;

import com.google.inject.Inject;
import java.util.List;
import java.util.function.Consumer;
import org.assertj.core.api.SoftAssertions;
import org.example.annotations.Path;
import org.example.components.CourseData;
import org.example.support.GuiceScoped;
import org.openqa.selenium.By;

@Path("course/get/all")
@SuppressWarnings("unchecked")
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

  public void compareCourseDataTo(List<org.example.models.CourseData> expectedCourseData) {
    SoftAssertions softly = new SoftAssertions();
    Consumer<CourseData>[] checks = expectedCourseData.stream()
        .map(expected -> (Consumer<CourseData>) actual -> {
          softly.assertThat(actual.getName())
              .as("Course name is incorrect")
              .isEqualTo(expected.getName());
          softly.assertThat(actual.getPrice())
              .as("Course price is incorrect")
              .isEqualTo(expected.getPrice());
        }).toArray(Consumer[]::new);

    softly.assertThat(getCourseData())
        .as("Course data from page and API don't match")
        .satisfiesExactly(checks);

    softly.assertAll();

  }
}

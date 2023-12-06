package org.example.pages;

import com.google.inject.Inject;
import java.util.Arrays;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.example.annotations.Path;
import org.example.annotations.Template;
import org.example.annotations.UrlTemplate;
import org.example.exceptions.PathNotFoundException;
import org.example.factory.WiremockModule;
import org.example.pageobject.AbsPageObject;
import org.example.support.GuiceScoped;

/**
 * .
 */
public abstract class AbsBasePage<T extends AbsBasePage<T>> extends AbsPageObject {

  @Inject
  public AbsBasePage(GuiceScoped scoped) {
    super(scoped);
  }

  private String getPath(String name, String... params) {
    Class<?> clazz = getClass();
    if (!clazz.isAnnotationPresent(UrlTemplate.class)) {
      throw new PathNotFoundException(clazz);
    }
    Optional<Template> template = Arrays.stream(clazz.getAnnotation(UrlTemplate.class).value())
        .filter(t -> name.equals(t.name()))
        .findFirst();
    if (template.isEmpty()) {
      throw new PathNotFoundException(name);
    }
    return template.get().template().formatted((Object[]) params);
  }

  private String getPath() {
    Class<?> clazz = getClass();
    if (!clazz.isAnnotationPresent(Path.class)) {
      throw new PathNotFoundException(clazz);
    }
    return clazz.getAnnotation(Path.class).value();
  }

  @SuppressWarnings("unchecked")
  public T open(String name, String... params) {
    driver.get(
        StringUtils.appendIfMissing(WiremockModule.getWiremockContainer().getBaseUrl(), "/")
            + getPath(name, params));
    return (T) this;
  }

  /**
   * .
   */
  @SuppressWarnings("unchecked")
  public T open() {
    String url = StringUtils.appendIfMissing(WiremockModule.getWiremockContainer().getBaseUrl(), "/")
        + getPath();
    driver.get(url);
    return (T) this;
  }
}

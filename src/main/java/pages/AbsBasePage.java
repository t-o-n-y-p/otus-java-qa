package pages;

import annotations.Path;
import annotations.Template;
import annotations.UrlTemplate;
import exceptions.PathNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;

import java.util.Arrays;
import java.util.Optional;

public class AbsBasePage<T extends AbsBasePage<T>> extends AbsPageObject {

    public static final String BASE_URL = System.getProperty("base.url");

    public AbsBasePage(WebDriver driver) {
        super(driver);
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
        driver.get(StringUtils.appendIfMissing(BASE_URL, "/") + getPath(name, params));
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T open() {
        driver.get(StringUtils.appendIfMissing(BASE_URL, "/") + getPath());
        return (T) this;
    }
}

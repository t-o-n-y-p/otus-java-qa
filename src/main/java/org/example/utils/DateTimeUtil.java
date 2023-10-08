package org.example.utils;

import io.cucumber.java.ParameterType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

/**
 .
 */
@UtilityClass
public class DateTimeUtil {

  public static final DateTimeFormatter COURSE_START_DAY_MONTH_FORMATTER =
      new DateTimeFormatterBuilder()
          .parseCaseInsensitive()
          .parseDefaulting(ChronoField.YEAR, LocalDate.now().getYear())
          .appendOptional(DateTimeFormatter.ofPattern("dd' 'MMMM"))
          .appendOptional(DateTimeFormatter.ofPattern("d' 'MMMM"))
          .toFormatter(Locale.forLanguageTag("ru"));

  public static final DateTimeFormatter COURSE_START_DAY_MONTH_YEAR_FORMATTER =
      new DateTimeFormatterBuilder()
          .parseCaseInsensitive()
          .appendOptional(DateTimeFormatter.ofPattern("dd' 'MMMM' 'yyyy"))
          .appendOptional(DateTimeFormatter.ofPattern("d' 'MMMM' 'yyyy"))
          .toFormatter(Locale.forLanguageTag("ru"));

  /**
   .
   */
  @ParameterType("(\\d{1,2} \\D+( \\d{4}( года)?)?)")
  public LocalDate programStartDate(String dateString) {
    try {
      return LocalDate.parse(dateString, COURSE_START_DAY_MONTH_FORMATTER);
    } catch (DateTimeParseException ignored) {
      return LocalDate.parse(
          StringUtils.substringBefore(dateString, " года"), COURSE_START_DAY_MONTH_YEAR_FORMATTER);
    }
  }
}

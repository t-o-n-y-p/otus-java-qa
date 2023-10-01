package org.example.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;
import lombok.experimental.UtilityClass;

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
}

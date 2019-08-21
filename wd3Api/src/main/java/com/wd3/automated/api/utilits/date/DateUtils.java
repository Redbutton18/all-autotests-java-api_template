package com.wd3.automated.api.utilits.date;

import com.wd3.automated.api.properties.DBProp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

@Slf4j
public class DateUtils {

        public enum DayOfMonth
        {
            FIRST_DAY,
            LAST_DAY;

        }
        public static final List<DateTimeFormatter> DATETIME_FORMATTER_PARSE_LIST = Arrays.asList(
                DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.SSS"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.SS"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.S"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.S"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        );

        private static final List<DateTimeFormatter> CONSOLE_FORMATTER_PARSE_LIST = Arrays.asList(
                DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy' 'HH:mm:ss"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy' 'HH:mm"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy' 'HH"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy' 'hh:mm:ss a"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy' 'hh:mm a"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy' 'hh a"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd' 'hh:mm:ss a"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd' 'hh:mm a"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd' 'hh a")
        );

        private static final List<DateTimeFormatter> YEARMONTH_FORMATTER_PARSE_LIST = Arrays.asList(
                DateTimeFormatter.ofPattern("yyyy-MM"),
                DateTimeFormatter.ofPattern("yyMM"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy")
        );

        private static final List<DateTimeFormatter> DATE_FORMATTER_PARSE_LIST = Arrays.asList(
                DateTimeFormatter.ofPattern("yyyy-MM-dd"),
                DateTimeFormatter.ofPattern("MM/dd/yyyy")
        );

        private static final DateTimeFormatter DATABASE_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd[ HH:mm:ss]"); //optional hours/minute/seconds block so pattern can also format LocalDate.

        private static final List<DateTimeFormatter> DATETIME_WITH_TZ_FORMATTER_PARSE_LIST = Arrays.asList(
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSXXX"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SXXX"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmXXX"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.SSSXXX"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.SSXXX"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss.SXXX"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mmXXX")
        );

        public static String toDatabaseUTC(OffsetDateTime source)
        {
            return source == null ? "" : source.withOffsetSameInstant(ZoneOffset.UTC).format(DATABASE_DATETIME_FORMATTER);
        }

        public static OffsetDateTime now()
        {
            return OffsetDateTime.now(DBProp.ZONE_ID);
        }

        public static LocalDate parseYearMonth(String dateStr)
        {
            return parseYearMonth(dateStr, DayOfMonth.LAST_DAY);
        }

        /**
         * Parse a string containing only year and month and return the parsed date
         * with the day set to either the first or last day of the month.
         * Typical use case: Credit card expiration date
         *
         * @param dateStr    The string to parse
         * @param dayOfMonth How to set the day, first or last day of month
         * @return A local date
         */
        public static LocalDate parseYearMonth(String dateStr, DayOfMonth dayOfMonth)
        {
            if (StringUtils.isEmpty(dateStr))
                return null;

            for (DateTimeFormatter pattern : YEARMONTH_FORMATTER_PARSE_LIST)
            {
                try
                {
                    if (dayOfMonth == DayOfMonth.FIRST_DAY)
                        return YearMonth.parse(dateStr, pattern).atDay(1);
                    else
                        return YearMonth.parse(dateStr, pattern).atEndOfMonth();
                }
                catch (Exception ex)
                { /* Wrong format */ }
            }

            log.error("Wrong Year Month format {}", dateStr);

            return parseDateWithHour(dateStr).with(dayOfMonth == DayOfMonth.FIRST_DAY ? TemporalAdjusters.firstDayOfMonth() : TemporalAdjusters.lastDayOfMonth());
        }

        public static LocalDate parseDateWithHour(String date)
        {
            if (StringUtils.isEmpty(date))
                return null;

            LocalDate localDate = parseLocalDate(DATE_FORMATTER_PARSE_LIST, date);
            if (localDate != null)
                return localDate;

            OffsetDateTime offsetDateTime = parseDateTime(date);
            return offsetDateTime.toLocalDate();
        }

        private static LocalDate parseLocalDate(List<DateTimeFormatter> formatterList, String date)
        {
            for (DateTimeFormatter formatter : formatterList)
            {
                try
                {
                    return LocalDate.parse(date, formatter);
                }
                catch (Exception e)
                {
                    /* Bad formatter, proceed to next one */
                }
            }

            return null;
        }

        private static OffsetDateTime parseOffsetDateTime(List<DateTimeFormatter> formatterList, String date)
        {
            for (DateTimeFormatter formatter : formatterList)
            {
                try
                {
                    return OffsetDateTime.parse(date, formatter);
                }
                catch (Exception e)
                {
                    /* Bad formatter, proceed to next one */
                }
            }

            return null;
        }
        public static OffsetDateTime parseDateTime(String date)
        {
            if (date == null)
            {
                return null;
            }

            // Accept as well timezone offset (e.g. 2017-12-31T23:59:59+05:00)
            OffsetDateTime offsetDateTime = parseOffsetDateTime(DATETIME_WITH_TZ_FORMATTER_PARSE_LIST, date);
            if (offsetDateTime != null)
                return offsetDateTime;

            // Accept datetime without timezone (e.g. 2017-12-31T23:59:59)
            LocalDateTime localDateTime = parseLocalDateTime(DATETIME_FORMATTER_PARSE_LIST, date);
            if (localDateTime != null)
                return ZonedDateTime.of(localDateTime, DBProp.ZONE_ID).toOffsetDateTime();

            // Accept non-standard datetime
            localDateTime = parseLocalDateTime(CONSOLE_FORMATTER_PARSE_LIST, date);
            if (localDateTime != null)
                return ZonedDateTime.of(localDateTime, DBProp.ZONE_ID).toOffsetDateTime();

            throw new DateTimeParseException(format("%s is not a supported DateTime format ", date), date, 0);
        }

        private static LocalDateTime parseLocalDateTime(List<DateTimeFormatter> formatterList, String date)
        {
            for (DateTimeFormatter formatter : formatterList)
            {
                try
                {
                    return LocalDateTime.parse(date, formatter);
                }
                catch (Exception e)
                {
                    /* Bad formatter, proceed to next one */
                }
            }

            return null;
        }
}

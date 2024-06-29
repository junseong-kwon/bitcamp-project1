package bitcamp.project1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {
    public static int getMonthOfYear(LocalDate date) {
        return date.getMonthValue();
    }

    public static LocalDate parseDate(String dateString) {
        LocalDate date = null;
        try {
            date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            try {
                date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
            } catch (DateTimeParseException ignored) {
                // 무시
            }
        }
        if (date == null) {
            throw new DateTimeParseException("날짜 형식을 입력해주세요 (yyyy-MM-dd 또는 yyyyMMdd).", dateString, 0);
        }
        return date;
    }
}

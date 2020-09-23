package application.utils.date;

import application.services.AimService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static application.logger.LoggerJ.logError;

public class DateParser {

    private final static String dateParsingPattern = "yyyy-MM-dd";

    public static Optional<Date> parseStringToDateForDefaultPattern(String date) {
        try {
            Date parsedDate = new SimpleDateFormat(dateParsingPattern).parse(date);
            return Optional.of(parsedDate);
        } catch (ParseException e) {
            logError(AimService.class, "DateParser.parseStringToDateForDefaultPattern("
                    + date + "), error message:" + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

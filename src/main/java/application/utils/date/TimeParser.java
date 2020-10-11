package application.utils.date;

import java.time.LocalTime;
import java.util.Optional;

import static application.logger.LoggerJ.logError;

public class TimeParser {

    public static Optional<LocalTime> parseToLocalTime(String hourAndMinutes) {
            String[] hoursAndMinutesArray = hourAndMinutes.split("[-:;,\\s+]");
            if (hoursAndMinutesArray.length != 2) {
                throw new IllegalArgumentException();
            } else {
                String hour = hoursAndMinutesArray[0];
                String minutes = hoursAndMinutesArray[1];
                try {
                    int hourInt = Integer.parseInt(hour);
                    int minutesInt = Integer.parseInt(minutes);
                    LocalTime time = LocalTime.of(hourInt, minutesInt);
                    return Optional.of(time);
                } catch (NumberFormatException  e) {
                    logError(TimeParser.class, "TimeParser.parseToLocalTime("
                            + hourAndMinutes + "), error message:" + e.getMessage());
                }
            }
        return Optional.empty();
    }
}

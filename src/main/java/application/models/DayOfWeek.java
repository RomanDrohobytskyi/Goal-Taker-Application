package application.models;

import java.util.ArrayList;
import java.util.List;

public enum DayOfWeek {
    SUNDAY(1, "Sunday"),
    MONDAY(2, "Monday"),
    TUESDAY(3, "Tuesday"),
    WEDNESDAY(4, "Wednesday"),
    THURSDAY(5, "Thursday"),
    FRIDAY(6, "Friday"),
    SATURDAY(7, "Saturday");

    private final int number;
    private final String day;
    private static final List<String> sortedDays = new ArrayList<>();

    DayOfWeek(int number, String day) {
        this.number = number;
        this.day = day;
    }

    public int getNumber() {
        return number;
    }

    public String getDay() {
        return day;
    }

}

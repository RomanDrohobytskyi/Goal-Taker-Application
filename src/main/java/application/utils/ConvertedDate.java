package application.utils;

import application.entities.time.data.Time;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ConvertedDate{

    private int day;
    private int month;
    private Long year;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public ConvertedDate(Time time){
        Date date = time.getDate();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        this.day = localDate.getDayOfMonth();
        this.month = localDate.getMonthValue();
        this.year = (long) localDate.getYear();
    }

    @Override
    public String toString() {
        return getDay() + "/" + getMonth() + "/" + getYear();
    }
}

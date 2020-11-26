package application.models;

import lombok.Getter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Getter
public class ConvertedDate {

    private int day;
    private int month;
    private Long year;

    public ConvertedDate(Date date){
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

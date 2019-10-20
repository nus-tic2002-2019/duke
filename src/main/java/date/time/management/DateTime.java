package date.time.management;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class DateTime {

    private LocalDateTime localDateTime = LocalDateTime.now();
    private LocalDate localDate = LocalDate.now();
    private LocalTime localTime = LocalTime.now();

    public boolean DateInputAfterLocal(String date){
        try {
            LocalDate LD = LocalDate.parse(date);
        }
        catch(DateTimeParseException e){
            System.out.println("The input date format wrongly.");
        }

        return (LocalDate.parse(date).isAfter(localDate));
    }

    public boolean TimeInputAfterLocal(String time){
        try{
            LocalTime LD = LocalTime.parse(time);
        }catch(DateTimeParseException e){
            System.out.println("The input time format wrongly.");
        }

        return (LocalTime.parse(time).isAfter(localTime));
    }

    public boolean DateTimeInputAfterLocal(String datetime){
        try{
            LocalDateTime LDT = LocalDateTime.parse(datetime);
        }catch (DateTimeParseException e){
            System.out.println("The input datetime format is wrongly");
        }

        return (LocalDateTime.parse(datetime).isAfter(localDateTime));
    }

    //MMM dd yyyy Print format;
    public void DateTimePrint_A (String TaskDate, String TaskTime){

    }
}

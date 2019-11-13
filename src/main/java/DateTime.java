import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class DateTime {
    private LocalDate date;
    private LocalTime time;
    private DayOfWeek dayOfWeek;
    private boolean hasTime = false;


    public DateTime(LocalDate date){
        this.dayOfWeek = date.getDayOfWeek();
        this.date = date;
    }

    public DateTime(LocalDate date, LocalTime time){
        this.dayOfWeek = date.getDayOfWeek();
        this.date = date;
        this.time = time;
        hasTime = true;
    }

    public boolean isHasTime(){
        return hasTime;
    }
    public LocalDate getDate(){
        return date;
    }
    public LocalTime getTime(){
        return time;
    }
    public void editTime(LocalTime time){
        this.time = time;
        hasTime = true;
    }
    public void editDate(LocalDate date){
        this.date = date;
    }


}

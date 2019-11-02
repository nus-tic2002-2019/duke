package Task;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeParseException;



public class Event extends Task {

    protected boolean isEvent;
    protected String at;
    protected Date date_by;
    SimpleDateFormat format1 = new SimpleDateFormat("dd-MMM-yy hh:mm a");



    public Event(String description, String at) {
        super(description);
        this.at = at;
        isEvent = false;
    }

    @Override
    public String toString(){
//        LocalDate d1 = LocalDate.parse(" 2019-12-01");
//        event meeting /at 2019-01-01 13:00

        String atDate  = at.trim();
        System.out.println(atDate);
        int timeIndex = atDate.indexOf(" ");

//        LocalTime t1 = LocalTime.parse(atDate.substring(timeIndex));
//        System.out.println(t1);

        LocalDate d1 = LocalDate.parse(atDate);

//        String atTime = t1.format(DateTimeFormatter.ofPattern("HH:mm"));
        atDate = d1.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        return "[E]" + super.toString() +  "(at:" + atDate + ")" ;

//        return "[E]" + super.toString() +  "(at:" + at  + ")" ;
    }

}

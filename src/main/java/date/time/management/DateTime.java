package date.time.management;

import thrownexceptions.InputDateTimeTooEarly;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTime {

    private LocalTime Time_Input;
    private LocalDate Date_Input;
    private static LocalDateTime DateTime_Input;
    private static LocalDateTime DateTime_Creation = LocalDateTime.now();
    private LocalDate Date_Creation = LocalDate.now();
    private LocalTime Time_Creation = LocalTime.now();

    /**
     * Constructs a DateTime with input String date and String time;
     * This constructor will assign Time_Input(LocalTime), Date_Input(LocalDate) and DateTime_Input(LocalDateTime);
     * @param date date String;
     * @param time time String;
     */
    public DateTime(String date, String time) {
        this.Date_Input = LocalDate.parse(date);

        //To make time format become hh:mm;
        if (time.length() == 4) time = "0" + time;
        this.Time_Input = LocalTime.parse(time);

        //To assign DateTime_Input;
        DateTime_Input = LocalDateTime.of(Date_Input, Time_Input);

    }

    /**
     * Constructs a DateTime with input String of date and time;
     * This constructor will assign Time_Input(LocalTime), Date_Input(LocalDate) and DateTime_Input(LocalDateTime);
     * @param InfoFromText Information text which include date and time in LocalDateTime pattern;
     */
    public DateTime(String InfoFromText){
        DateTime_Input = LocalDateTime.parse(InfoFromText);
        Date_Input = DateTime_Input.toLocalDate();
        Time_Input = DateTime_Input.toLocalTime();
    }

    /**
     * To compare the input date and time is after the DateTime creation date and time;
     * @param input the date and time which going to compare in LocalDateTime type;
     * @throws InputDateTimeTooEarly If the input is before creation datetime, throw the error;
     */
    public static void Comparision(LocalDateTime input) throws InputDateTimeTooEarly {
        if(!input.isAfter(DateTime_Creation)){
            throw new InputDateTimeTooEarly();
        }
    }

    /**
     * To compare the input date and time is after the DateTime creation date and time;
     * @param date the date which going to compare current date(LocalDate.now());
     * @throws InputDateTimeTooEarly If the input is before creation datetime, throw the error;
     */
    public static boolean Comparision(String date) throws InputDateTimeTooEarly {
        LocalDate localDate = LocalDate.now();
        LocalDate input_date = LocalDate.parse(date);

        return !input_date.isBefore(localDate);
    }

    /**
     * Change the LocalDateTime type "input" to String
     * @param input LocalDateTime type "input"
     * @return return String type of "input"
     */
    public String DateTimeToString(LocalDateTime input){
        return input.format(DateTimeFormatter.ISO_DATE);
    }

    /**
     * To get the Date_Input
     * @return return LocalDate Date_Input
     */
    public LocalDate getDate_Input(){
        return Date_Input;
    }

    /**
     * To get the Time_Input
     * @return return LocalDate Time_Input
     */
    public LocalTime getTime_Input() { return Time_Input; }

    /**
     * Adjust day or hour or minutes of the DateTime;
     * @param input input is the value of change;
     * @param DayOrHourOrMinus The changing target, Day/Hour/Minutes;
     * @throws InputDateTimeTooEarly if the updated DateTime is before DateTime_Creation, throw;
     */
    public void AdjustDateTime(long input, String DayOrHourOrMinus) throws InputDateTimeTooEarly {

        switch (DayOrHourOrMinus){
            case "D":
                if(input>0){
                    DateTime_Input.plusDays(input);
                    if(DateTime_Input.isBefore(DateTime_Creation)){
                        DateTime_Input.minusDays(input);
                        throw new InputDateTimeTooEarly();
                    }
                }
                else {
                    DateTime_Input.minusDays(input);
                    if(DateTime_Input.isBefore(DateTime_Creation)){
                        DateTime_Input.plusDays(input);
                        throw new InputDateTimeTooEarly();
                    }
                }

                break;
            case "H":
                if(input>0){
                    DateTime_Input.plusHours(input);
                    if(DateTime_Input.isBefore(DateTime_Creation)){
                        DateTime_Input.minusHours(input);
                        throw new InputDateTimeTooEarly();
                    }
                }
                else {
                    DateTime_Input.minusHours(input);
                    if(DateTime_Input.isBefore(DateTime_Creation)){
                        DateTime_Input.plusHours(input);
                        throw new InputDateTimeTooEarly();
                    }
                }
                break;
            case "M":
                if(input>0){
                    DateTime_Input.plusMinutes(input);
                    if(DateTime_Input.isBefore(DateTime_Creation)){
                        DateTime_Input.minusMinutes(input);
                        throw new InputDateTimeTooEarly();
                    }
                }
                else {
                    DateTime_Input.minusMinutes(input);
                    if(DateTime_Input.isBefore(DateTime_Creation)){
                        DateTime_Input.plusMinutes(input);
                        throw new InputDateTimeTooEarly();
                    }
                }
                break;
        }
    }

    /**
     * To get the DateTime_Input, LocalDateTime type;
     * @return return DateTime_Input;
     */
    public static LocalDateTime getDateTime_Input(){
        return DateTime_Input;
    }
}
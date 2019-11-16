package date.time.management;

import taskclasses.*;
import thrownexceptions.InputChoiceOutOfRange;
import thrownexceptions.InputDateTimeTooEarly;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import static ui.Ui.Separated_Line;

public class DateTime {

    protected static LocalTime Time_Input;
    protected static LocalDate Date_Input;
    protected static LocalDateTime DateTime_Input;
    protected static LocalDateTime DateTime_Creation = LocalDateTime.now();

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
     */
    public static boolean Comparision(String date) {
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
     * To get the DateTime_Input, LocalDateTime type;
     * @return return DateTime_Input;
     */
    public static LocalDateTime getDateTime_Input(){
        return DateTime_Input;
    }

    /**
     * To get the DateTime_Creation, LocalDateTime type;
     * @return return DateTime_Creation;
     */
    private LocalDateTime getDateTime_Creation(){
        return DateTime_Creation;
    }

    /**
     * Adjust day or hour or minutes of the DateTime;
     * @param input input is the value of change;
     * @param DayOrHourOrMinus The changing target, Day/Hour/Minutes;
     * @throws InputDateTimeTooEarly if the updated DateTime is before DateTime_Creation, throw;
     */
    private static void DateTimeUpdater(int input, String DayOrHourOrMinus, DateTime dateTime) throws InputDateTimeTooEarly {

        LocalDateTime localDateTime = dateTime.getDateTime_Input();
        LocalDateTime creation = dateTime.getDateTime_Creation();
        LocalDate localDate = dateTime.getDate_Input();
        LocalTime localTime = dateTime.getTime_Input();

        switch (DayOrHourOrMinus){
            case "D":
                if(input>0){
                    localDateTime = localDateTime.plusDays(input);
                    localDate = localDate.plusDays(input);
                    dateTime.Date_Input = localDate;
                    dateTime.DateTime_Input = localDateTime;

                    if(localDateTime.isBefore(creation)){
                        dateTime.DateTime_Input = localDateTime.minusDays(input);
                        dateTime.Date_Input = localDate.minusDays(input);
                        throw new InputDateTimeTooEarly();
                    }
                }
                else {
                    localDateTime = localDateTime.minusDays(input);
                    localDate = localDate.minusDays(input);
                    dateTime.Date_Input = localDate;
                    dateTime.DateTime_Input = localDateTime;


                    if(localDateTime.isBefore(creation)){
                        dateTime.DateTime_Input = localDateTime.plusDays(input);
                        dateTime.Date_Input = localDate.plusDays(input);
                        throw new InputDateTimeTooEarly();
                    }
                }
                break;
            case "H":
                if(input>0){
                    localDateTime = localDateTime.plusHours(input);
                    localTime = localTime.plusHours(input);
                    dateTime.Time_Input = localTime;
                    dateTime.DateTime_Input = localDateTime;

                    if(localDateTime.isBefore(creation)){
                        dateTime.DateTime_Input = localDateTime.minusHours(input);
                        dateTime.Time_Input = localTime.minusHours(input);
                        throw new InputDateTimeTooEarly();
                    }
                }
                else {
                    localDateTime = localDateTime.minusHours(input);
                    localTime = localTime.minusHours(input);
                    dateTime.Time_Input = localTime;
                    dateTime.DateTime_Input = localDateTime;

                    if(localDateTime.isBefore(creation)){
                        dateTime.DateTime_Input = localDateTime.plusHours(input);
                        dateTime.Time_Input = localTime.plusHours(input);
                        throw new InputDateTimeTooEarly();
                    }
                }
                break;
            case "M":
                if(input>0){
                    localDateTime = localDateTime.plusMinutes(input);
                    localTime = localTime.plusMinutes(input);
                    dateTime.Time_Input = localTime;
                    dateTime.DateTime_Input = localDateTime;

                    if(localDateTime.isBefore(creation)){
                        dateTime.DateTime_Input = localDateTime.minusMinutes(input);
                        dateTime.Time_Input = localTime.minusMinutes(input);
                        throw new InputDateTimeTooEarly();
                    }
                }
                else {
                    localDateTime = localDateTime.minusMinutes(input);
                    localTime = localTime.minusMinutes(input);
                    dateTime.Time_Input = localTime;
                    dateTime.DateTime_Input = localDateTime;
                    if(localDateTime.isBefore(creation)){
                        dateTime.DateTime_Input = localDateTime.plusMinutes(input);
                        dateTime.Time_Input = localTime.plusMinutes(input);
                        throw new InputDateTimeTooEarly();
                    }
                }
                break;
        }
    }

    private static int ChangingValue (){
        System.out.println("      Please input the value you want to change. \n" +
                "(positive integer means increase, negative integer means minus)");

        Scanner ss = new Scanner(System.in);
        return Integer.parseInt(ss.nextLine());
    }

    private static String getDayHourMinute(){
        System.out.println("     Please choose one of the following:\n" +
                "1. Day\n" +
                "2. Hour\n" +
                "3. Minute");

        Separated_Line();
        Scanner s = new Scanner(System.in);
        String D_H_M = s.nextLine().toLowerCase();

        switch (D_H_M){
            case "1":
                return "D";
            case "2":
                return "H";
            case "3":
                return "M";
            default:
                System.out.println("     The index you chose does not correct. Please try again.");
                return getDayHourMinute();
        }
    }

    private static String getEventTaskDateTimeUpdateChoice() throws InputChoiceOutOfRange {
        System.out.println("The task is 'event' type task. Please choose:\n" +
                "1. Starting DateTime\n" +
                "2. Ending DateTime\n" +
                "3. Both of above");

        Scanner s = new Scanner(System.in);
        String choice = s.nextLine();

        switch (choice){
            case "1":
                return "1";
            case "2":
                return "2";
            case "3":
                return "3";
            default:
                throw new InputChoiceOutOfRange();
        }
    }

    /**
     * To get the whether user want to update task date or description and details of date or time or description changes
     * @param task The task which is going to be updated
     * @param type The type of the task
     * @throws InputDateTimeTooEarly If the updated date is earlier than the task creation date, then the error will throw to user.
     */
    public static void DateTimeChangingInformationCollector(Task task, String type) throws InputDateTimeTooEarly {
        String D_H_M  = getDayHourMinute();
        int ChangingValue_Deadline, ChangingValue_Starting, ChangingValue_Ending;
        DateTime deadline_datetime, starting_DateTime, ending_DateTime;

        try {
            if ("D".equals(type)) {
                deadline_datetime = task.getDeadline_timing();
                ChangingValue_Deadline = ChangingValue();
                DateTimeUpdater(ChangingValue_Deadline, D_H_M, deadline_datetime);
            } else { //event;
                String DateTimeUpdateChoice = getEventTaskDateTimeUpdateChoice();
                switch (DateTimeUpdateChoice){
                    case "1":
                        starting_DateTime = task.getStarting_Time();
                        ChangingValue_Starting = ChangingValue();
                        DateTimeUpdater(ChangingValue_Starting, D_H_M, starting_DateTime);
                        break;
                    case "2":
                        ending_DateTime = task.getEnding_Time();
                        ChangingValue_Ending = ChangingValue();
                        DateTimeUpdater(ChangingValue_Ending, D_H_M, ending_DateTime);
                        break;
                    default:
                        starting_DateTime = task.getStarting_Time();
                        ChangingValue_Starting = ChangingValue();
                        DateTimeUpdater(ChangingValue_Starting, D_H_M, starting_DateTime);
                        ending_DateTime = task.getEnding_Time();
                        ChangingValue_Ending = ChangingValue();
                        DateTimeUpdater(ChangingValue_Ending, D_H_M, ending_DateTime);
                }
            }
        }
        catch (NumberFormatException e){
            Separated_Line();
            System.out.println("     The changing value you input is not integer. Please try again. ");
            Separated_Line();
            DateTimeChangingInformationCollector(task, type);
        }
        catch (InputChoiceOutOfRange e){
            Separated_Line();
            System.out.println("     The index of event datetime you is not correct. Please try again.");
            Separated_Line();
            DateTimeChangingInformationCollector(task, type);
        }
    }
}
package parser;

import date.time.management.DateTime;
import enumlist.Days;
import enumlist.Month;
import taskclasses.Task;
import thrownexceptions.*;

import java.io.IOException;
import java.security.PublicKey;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Parser {
    /**
     * To check customer input String. If the input only has one word and not "list" and "bye".
     * This function will throw an error which will tell user that the input in incorrect and please try again;
     * @param First_Word The first word of the customer input which used to compare the condition;
     * @param Input_Words The customer input which used to check length;
     * @throws DukeException The error which the input is not correct.
     */
    public static void Input_Length_Checking(String First_Word, String[] Input_Words) throws DukeException {
        if(!First_Word.equals("list") && !First_Word.equals("bye") && !First_Word.equals("search") && Input_Words.length == 1){
            throw new DukeException(First_Word);
        }
    }

    /**
     * To show Date format choices to customer and let them to choose.
     * Choices:
     * 1. MMM D YYY
     * 2. Day of Month Year, e.g. 2nd of December 2019;
     * @return  return the choice customer choose whether one or two.
     * @throws DateTypeChooseWrongly throw error if the choice customer chose is not "1" or "2";
     */
    public static String Date_Display_Format() throws DateTypeChooseWrongly {
        String Date_Type;

        System.out.println("Please choose the Date format:");
        System.out.println("1. MMM D YYY\n" +
                "2. Day of Month Year");

        Scanner date_choice = new Scanner(System.in);
        Date_Type = date_choice.nextLine();

        if(!Date_Type.equals("1") && !Date_Type.equals("2")){
            throw new DateTypeChooseWrongly();
        }

        return Date_Type;
    }

    /**
     * To show Time format choices to customer and let them to choose.
     * Choices:
     * 1. hh:mm  e.g. 16:22
     * 2. H:mm  e.g. 06:22 pm
     * @return return the customer choice;
     * @throws TimeTypeChooseWrongly To throught an error is the customer choice is not "1" or "2";
     */
    public static String Time_Display_Format() throws TimeTypeChooseWrongly {
        String Time_Type;

        System.out.println("Please choose Time display format:\n" +
                "1. hh:mm\n" +
                "2. h:mm AM/PM");

        Scanner time_choice = new Scanner(System.in);
        Time_Type = time_choice.nextLine();

        if(!Time_Type.equals("1") && !Time_Type.equals("2")){
            throw new TimeTypeChooseWrongly();
        }


        return Time_Type;
    }

    /**
     * Date print type 1
     * After Customer choose the Date format 1, the program will call this function for Print method;
     * This function will convert the task date into String accordingly;
     * Output type: "MMM d yyyy"
     * @param task The task which needs to be convert;
     * @param START_END To let program know which type of task needs to convert;
     * @return return converted Date;
     */
    public static String Date_Type_One(Task task, String START_END){
        String date;
        DateTime deadline;

        deadline = task.getDeadline_timing();
        LocalDate localDate= deadline.getDate_Input();
        date =  localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        //System.out.println(date);

        switch (START_END){
            case "start":
                DateTime starting = task.getStarting_Time();
                date = starting.getDate_Input().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                break;
            case "end":
                DateTime ending = task.getEnding_Time();
                date = ending.getDate_Input().format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                break;
        }

        return date;
    }

    /**
     * Date print type 2
     * After Customer choose the Date format 2, the program will call this function for Print method;
     * This function will convert the task date into String accordingly;
     * Output type: "2nd of December 2019"
     * @param task The task which needs to be convert;
     * @param START_END To let program know which type of task needs to convert;
     * @return return converted Date;
     */
    public static String Date_Type_Two(Task task, String START_END) throws DateTimeInputWrongly, MonthIndexWrong, EnumDayIndexWrongly {
        DateTime DATE;
        String date;

        switch (START_END){
            case "deadline":
                DATE = task.getDeadline_timing();
                date=date_generator(DATE);
                break;
            case "start":
                DATE = task.getStarting_Time();
                date=date_generator(DATE);
                break;
            case "end":
                DATE = task.getEnding_Time();
                date=date_generator(DATE);
                break;
            default:
                throw new DateTimeInputWrongly();
        }

        return date;
    }

    /**
     * Time print type 1
     * After Customer choose the Date format 1, the program will call this function for Print method;
     * This function will convert the task date into String accordingly;
     * Output type: "hh:mm", 24hrs base;
     * @return return converted Time;
     */
    public static String Time_Type_One(DateTime dateTime){
        return dateTime.getDateTime_Input().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Time print type 2
     * After Customer choose the Date format 2, the program will call this function for Print method;
     * This function will convert the task date into String accordingly;
     * Output type: "h:mm AM/PM", 12hrs base with AM/PM indicated;
     * @return return converted Time;
     */
    public static String Time_Type_Two(DateTime dateTime){
        return dateTime.getDateTime_Input().format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    /**
     * This function is used to convert DateTime date to String in "day of month year" format;
     * @param DATE DateTime which needs to be convert;
     * @return return converted String which in "day of month year" format;
     * @throws MonthIndexWrong throw an error if the month value in the DATE is not valid (not in Jan to Dec range, 1-12);
     * @throws EnumDayIndexWrongly throw an error if the day of month value in the DATE is not valid;
     */
    private static String date_generator(DateTime DATE) throws MonthIndexWrong, EnumDayIndexWrongly {
        int MONTH, YEAR, DAY;
        String date, month, day;

        LocalDate localDate = DATE.getDate_Input();
        MONTH = localDate.getMonthValue(); //int
        YEAR = localDate.getYear(); //int
        DAY = localDate.getDayOfMonth();    //int

        month = Month.getMonth_FullName(MONTH);
        day = Days.getDay(DAY);

        date = day + " of " + month + " " + YEAR;

        return date;
    }

    /**
     * To get description from the customer input;
     * @param Input the customer input String
     * @return return description in the input in String type;
     */
    public static String Description(String Input){
        String D;
        String[] body = Input.split(" ");

        int D_index = Input.indexOf(" ") + 1;

        if(body[0].equals("todo")){
            D = Input.substring(D_index);
        }
        else{

            int slash_index = Input.length();

            if(body[0].equals("deadline")){
                slash_index = Input.lastIndexOf("by");
            }
            else {
                slash_index = Input.lastIndexOf("at");
            }

            D = Input.substring(D_index, slash_index - 1).trim();
        }

        return D.trim();
    }

    /**
     * Extract deadline time from input;
     * @param Input the deadline type input;
     * @return return deadline time in String type;
     */
    public static String deadline_time(String Input){
        //Take time out from Input
        String ST;
        int ST_index = Input.lastIndexOf("by") + 3;
        ST = Input.substring(ST_index);
        String[] input = ST.split(" ");

        return ST;
    }

    /**
     * Extract event time information from input String;
     * @param Input the event type String input;
     * @return return event time inforamtion in String type;
     */
    public static String Event_time(String Input){
        String ET;

        int ET_index = Input.lastIndexOf("at");

        if(ET_index == -1){
            ET_index = Input.lastIndexOf("on") + 3;
        }
        else {
            ET_index = ET_index + 3;
        }

        ET = Input.substring(ET_index);
        String[] ET_Array = ET.split(" ");

        if(ET_Array.length == 1){
            ET = ET + "59:59:99";
        }

        return ET.trim();
    }

    /**
     * Extract starting time from the event time String;
     * @param time Event time information in String type;
     * @return return event  starting time in String type;
     */
    public static String Starting_Time(String time){
//        String period = Event_time(time);
        String Starting;
        int Dash_index = time.lastIndexOf(" -> ");

        Starting = time.substring(0, Dash_index).trim();

        return Starting;
    }

    /**
     * Extract ending time from the event time String;
     * @param time Event time String;
     * @return return event ending time in String;
     */
    public static String Ending_Time(String time){
        String Ending;
        int Dash_index = time.lastIndexOf(" -> ");

        Ending = time.substring(Dash_index+4).trim();

        return Ending;
    }

    /**
     * Extract starting date information from input String
     * @param datetime Input time information in String which be extract from customer input information;
     * @return return date inforamtion of the input/task;
     */
    public static String date(String datetime){

        return datetime.substring(0, 10);
    }

    /**
     * Extract ending date information from input String
     * @param startingDate The starting date string.
     * @param datetime Input time information in String which be extract from customer input information;
     * @return return date inforamtion of the input/task;
     */
    public static String date(String startingDate, String datetime){

        if(datetime.length() < 6){
            return startingDate;
        }

        return datetime.substring(0, 10);
    }

    /**
     * Extract time information which is after date information of the time extract from customer input information;
     * @param datetime date and time information extracted from customer input information;
     * @return return time in String;
     */
    public static String time(String datetime){
        if(datetime.length() < 6){
            return datetime;
        }
        return datetime.substring(11);
    }

    /**
     * Extract date and time inforamtion from the input String and combine together in the format: "date time"
     * @param datetime input String which contain date and time information of the task;
     * @return return date+time in String;
     */
    public static String datetime(String datetime){
        String date_D = date(datetime).trim();
        String time_D = time(datetime).trim();

        return date_D + " " + time_D;
    }

    /**
     * Extract task type from the information input which is the first word or character;
     * @param Input the String input which from customer task input or from Storage text file;
     * @return return task type in String;
     */
    public static String Input_Type(String Input){
        String[] Input_Words = Input.split(" ");    //To split input by " " into String Array;

        return Input_Words[0];
    }

    /**
     * Part of search function. To check whether the search type is correct.
     * There is only three type is valid;
     * 1. date
     * 2. time
     * 3. description
     * @param input The customer choice of search type
     * @throws SearchTypeWrong throw error if the choice is not "date", "time" or "description";
     */
    public static void Search_Type_Checking(String input) throws SearchTypeWrong {
        if(!input.equals("date") && !input.equals("time") && !input.equals("description")) throw new SearchTypeWrong();
    }
}

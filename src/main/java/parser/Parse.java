package parser;

import exceptions.ErrType;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represent class of Parse.
 * Make sense of user input and update data into the data structure.
 */
public class Parse{

    private static boolean isExit ;

    public Parse(){
        this.isExit = false;
    }

    public static Boolean isExit(){
        return isExit;
    }

    /**
     * This method will take in an user command from the console and either
     * create new task item, update status or delete existing task.
     * @param t : this is the data structure of user tasks.
     */
    public void parser(ArrayList<Task> t){
        Integer idx = 0;
        String command = "";
        String user_input = "";
        String taskString = "";
        String timeString = "";

        LocalDateTime resultDateTime = null;
        LocalDateTime notDate = LocalDateTime.parse("1900-01-01T00:00"); //error flag

        Scanner in = new Scanner(System.in);
        user_input = in.nextLine();

        try {
            command = user_input.split(" ")[0].toLowerCase();
        } catch (ArrayIndexOutOfBoundsException e){
            command = ""; // when user input " ", program will crash. This code will catch it.
        }
        Ui.line();
        switch (command){
            case "list":
                Ui.list(t, t.size());
                break;

            case "todo":
                if ( ErrType.isTask(user_input) ){
                    taskString = user_input.replace("todo", "").trim();
                    t.add(new Todo(taskString));
                    Ui.added(t, t.size());
                }
                break;

            case "deadline":
                if ( ErrType.isTask(user_input) && ErrType.isSchedule(user_input) ){
                    taskString = user_input.split("/")[0].replace("deadline", "").trim();
                    timeString = user_input.split("/")[1].replace("by", "").trim();
                    resultDateTime = dateParser(timeString);
                    if ( !resultDateTime.equals(notDate) ){
                        t.add(new Deadline(taskString, resultDateTime));
                        Ui.added(t, t.size());
                    }
                }
                break;

            case "event":
                if ( ErrType.isTask(user_input) && ErrType.isSchedule(user_input) ){
                    taskString = user_input.split("/")[0].replace("event","").trim();
                    timeString = user_input.split("/")[1].replace("at", "").trim();
                    resultDateTime = dateParser(timeString);
                    if ( !resultDateTime.equals(notDate) ) {
                        t.add(new Event(taskString, resultDateTime));
                        Ui.added(t, t.size());
                    }
                }
                break;

            case "done":
                if ( !(user_input.trim().length() > 4) ) {
                    Ui.invalid();
                    break;
                }
                idx = ErrType.toInteger(user_input.split("done")[1].trim(), t.size());
                if (idx == -1) {
                    System.out.println("\tOops!! Please key a valid task number.");
                    break;
                }
                t.get(idx - 1).taskDone();
                Ui.done(t, idx);
                break;

            case "delete":
                if ( !(user_input.trim().length() > 6) ) {
                    Ui.invalid();
                    break;
                }
                idx = ErrType.toInteger(user_input.split("delete")[1].trim(), t.size());
                if ( idx == -1 ){
                    System.out.println("\tOops!! Please key a valid task number.");
                    break;
                }
                Ui.delete(t, idx);
                t.remove(idx-1);
                break;

            case "find":
                if ( ErrType.isTask(user_input) ){
                    String wordToFind = user_input.replace("find", "").trim().toLowerCase();
                    findKeyword(t, wordToFind);
                }
                break;

            case "sort":
                sortTask(t);
                break;

            case "help":
                Ui.help();
                break;

            case "bye":
                Ui.bye();
                this.isExit = true; // loop will end after looping back to while()
                break;

            default:
                Ui.invalid(); // any other command will be considered as error
                break;
        }
        Ui.line();
    }

    /**
     * Returns a LocalDateTime object from a date string. It checks
     * the format of date and time component. It checks for validity of date
     * and time ie no alphabet and numbers within valid range.
     * It accepts both yyyy-mm-dd hhmm and dd-mm-yyyy hhmm format.
     * @param date  a string which suppose to represent a date with time
     * @return  LocalDateTime object of the format yyyy-mm-ddThh:mm
     */
    public static LocalDateTime dateParser(String date){
        // "1900-01-01T00:00" represent date error flag
        LocalDateTime resultDateTime = LocalDateTime.parse("1900-01-01T00:00");
        LocalDate resultDate = null;
        String dateTemp = "";
        String timeTemp = "";
        String dateReverse = "";
        String timeMin = "";
        String timeHrs = "";

        dateTemp = date.split(" ")[0];
        assert dateTemp.length()==10:dateTemp.length(); //expected date of length 10chars

        if ( ErrType.isTime(date) ){ //check if there is a possible time
            timeTemp = date.split(" ")[1];
            try {
                timeHrs = timeTemp.substring(0,2);
                timeMin = timeTemp.substring(2,4);
            } catch (ArrayIndexOutOfBoundsException e1){
                System.out.println("\tOops!! Not a valid time of format hhmm.");
                return resultDateTime;
            }
        }
        try {
            resultDate = LocalDate.parse(dateTemp); //try original date form
        } catch (DateTimeParseException e2){
            try {
                // assume date format 31-12-2019 then change to this format 2019-12-31
                dateReverse = dateTemp.split("-")[2] + "-" +
                              dateTemp.split("-")[1] + "-" +
                              dateTemp.split("-")[0];
            } catch (ArrayIndexOutOfBoundsException e1){
                System.out.println("\tOops!! Not a valid date of format yyyy-mm-dd.");
                return resultDateTime;
            }
            try {
                resultDate = LocalDate.parse(dateReverse);
            }
            catch (DateTimeParseException e3){
                System.out.println("\tOops!! Not a valid date format.");
                return resultDateTime;
            }
        }
        // date format passed testing. to add time component behind date.
        try {
            resultDateTime = resultDate.atTime( Integer.parseInt(timeHrs),
                                                Integer.parseInt(timeMin) );
        } catch (DateTimeException e4){
            System.out.println("\tOops!! Not a valid time.");
            //return resultDateTime;
        } catch (NumberFormatException e5){
            System.out.println("\tOops!! Not a valid time");
            //return resultDateTime;
        }
        return resultDateTime;
    }

    /**
     * This method find keyword/s within all the task list.
     * @param t : this is the data structure of user tasks
     * @param wordToFind : user's keyword/s to search task list.
     */
    public static void findKeyword(ArrayList<Task> t, String wordToFind){
        Boolean isFound = false;
        for ( int i=0 ; i<t.size() ; i++ ){
            int n = t.get(i).getDescription().toLowerCase().indexOf(wordToFind);
            if (n>=0) {
                Ui.singleList(t,i);
                isFound=true;
            }
       }
        if ( !isFound ){
            System.out.println("\tOops!! Cannot find what you looking for.");
        }
    }

    /**
     * This is an individual feature 1.
     * This method will bubble sort the dates of Deadline and Event.
     * The latest schedule will sort to the bottom of task list.
     * Todo task will consolidate to the front of task list.
     * Sorting is permanent.
     * @param t : this is the data structure of user tasks
     */
    public static void sortTask(ArrayList<Task> t){
        // sort [T} to the top first
        int number_of_Todo_list = 0;
        for ( int i=0 ; i<t.size() ; i++ ){
            String command = t.get(i).toString().substring(1,2);
            if ( command.equals("T") ){
                Collections.swap(t,number_of_Todo_list,i);
                number_of_Todo_list += 1;
            }
        }
        boolean isSwap = false;
        while (!isSwap){
            isSwap = true;
            for ( int i=number_of_Todo_list + 1 ; i<t.size() ; i++ ){
                if ( t.get(i-1).getDate().isAfter(t.get(i).getDate()) ){
                    Collections.swap(t,i-1,i);
                    isSwap = false;
                }
            }
        }
        Ui.list(t, t.size()); // display new list after sorting
    }
}

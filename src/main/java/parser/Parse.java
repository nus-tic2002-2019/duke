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
import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * Represent class of Parse.
 * Make sense of user input and update data into the data structure.
 */
public class Parse {

    private static boolean isExit ;
    private static Integer index;

    public Parse(){
        this.isExit = false;
        this.index = 0;
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

        String user_input = "";
        Integer idx = 0;

        String command = "";
        String taskString = "";
        String timeString = "";

        LocalDateTime resultDateTime = null;
        LocalDateTime dummy = LocalDateTime.parse("1900-01-01T00:00"); //error flag

        Scanner in = new Scanner(System.in);
        user_input = in.nextLine();
        command = user_input.split(" ")[0].toLowerCase();

        switch (command) {
            case "list":
                Ui.list(t, t.size());
                break;

            case "todo":
                if (ErrType.TaskCheck(user_input)) {
                    taskString = user_input.replace("todo", "").trim();
                    t.add(new Todo(taskString));
                    Ui.added(t, t.size());
                }
                break;

            case "deadline":
                if (ErrType.TaskCheck(user_input) && ErrType.ScheduleCheck(user_input)) {
                    taskString = user_input.split("/")[0].replace("deadline", "").trim();
                    timeString = user_input.split("/")[1].replace("by", "").trim();
                    resultDateTime = dateConverter(timeString);
                    if (resultDateTime.equals(dummy)){
                        System.out.println("Date is wrong");
                        break;
                    } else {
                        t.add(new Deadline(taskString, resultDateTime)); //timeString
                        Ui.added(t, t.size());
                    }
                }
                break;

            case "event":
                if (ErrType.TaskCheck(user_input) && ErrType.ScheduleCheck(user_input)) {
                    taskString = user_input.split("/")[0].replace("event","").trim();
                    timeString = user_input.split("/")[1].replace("at", "").trim();
                    resultDateTime = dateConverter(timeString);
                    if (resultDateTime.equals(dummy)){
                        System.out.println("Date is wrong");
                        break;
                    } else {
                        t.add(new Event(taskString, resultDateTime)); //timeString
                        Ui.added(t, t.size());
                    }
                }
                break;

            case "done":
                idx = ErrType.toInteger(user_input.split(" ")[1], t.size()); // with Exceptions handling
                if (idx == -1) {
                    System.out.println("\tPlease key a valid task number.");
                    break;
                }
                Ui.done(t, idx);
                t.get(idx - 1).taskDone();

                index = idx;
                break;

            case "delete":
                idx = ErrType.toInteger(user_input.split(" ")[1], t.size()); // with Exceptions handling
                if (idx == -1) {
                    System.out.println("\tPlease key a valid task number.");
                    break;
                }
                Ui.delete(t, idx);
                t.remove(idx-1);

                this.index = idx;
                break;

            case "find":
                if (ErrType.TaskCheck(user_input)) {
                    taskString = user_input.replace("find", "").trim();
                    findTask(t, taskString);
                }
                break;

            case "bye": // "bye" command will end loop after looping back to while()
                Ui.bye();
                this.isExit = true;
                break;

            default:   // any other command will be considered as error
                Ui.invalid();
                this.index = -1;
                break;
        }
    }

    /**
     * This method convert String date to a LocalDateTime object.
     * It will check the format of date and time component.
     * it checks for validity of date and time ie no alphabet and numbers within valid range.
     * It accepts both yyyy-mm-dd hhmm and dd-mm-yyyy hhmm format.
     * @param date  a string which suppose to represent a date with time
     * @return  LocalDateTime object of the format yyyy-mm-ddThh:mm
     */
    public static LocalDateTime dateConverter(String date) {
        LocalDateTime resultDateTime = LocalDateTime.parse("1900-01-01T00:00"); // flag to represent error
        LocalDate resultDate = null;
        String dateTemp = "";
        String timeTemp = "";
        String dateConfirm = "";
        String timeMin = "";
        String timeHrs = "";

        dateTemp = date.split(" ")[0].replace("/","-"); // convert to 2019-02-02 form

        if (ErrType.timeCheck(date)) { //check if there is a time component
            timeTemp = date.split(" ")[1]; // if true, possibly there is time given
            try {
                timeHrs = timeTemp.substring(0,2);
                timeMin = timeTemp.substring(2,4);
            } catch (ArrayIndexOutOfBoundsException e1){
                System.out.println("Not a valid time of format hhmm.");
                return resultDateTime;
            }
        }

        try {
            resultDate = LocalDate.parse(dateTemp); //try original date form
        } catch (DateTimeParseException e2) {
            try {
                // assume date format 31-12-2019 then change to this format 2019-12-31
                dateConfirm = dateTemp.split("-")[2] + "-" + dateTemp.split("-")[1] + "-" + dateTemp.split("-")[0];
            } catch ( ArrayIndexOutOfBoundsException e1){
                System.out.println("Not a valid date of format yyyy-mm-dd");
                return resultDateTime;
            }
            try {
                //date swap successful, now parse the swapped date
                resultDate = LocalDate.parse(dateConfirm);
            }
            catch (DateTimeParseException e3) {
                //dateConfirm is not a date
                System.out.println("Not a valid date of format yyyy-mm-dd.");
                return resultDateTime;
            }
        }
        // date format passed testing. to add time component behind date.
        try {
            resultDateTime = resultDate.atTime(Integer.parseInt(timeHrs), Integer.parseInt(timeMin) );
        } catch (DateTimeException e4){
            System.out.println("Not a valid time.");
            return resultDateTime;
        } catch (NumberFormatException e5){
            System.out.println("Not a valid time, contains non-numbers");
            return resultDateTime;
        }
        return resultDateTime;
    }

    public static void findTask(ArrayList<Task> t, String findString){
        for (int i=0 ; i<t.size() ; i++ ){
            String[] taskString = t.get(i).getDescription().split(" ");
            Integer n = 0;
            try {
                while (true){
                    if ( findString.equals(taskString[n]) ){
                        Ui.singleList(t, i);
                        //System.out.println("Found it" + t.get(i).getDescription());
                    }
                    n = n + 1;
                }
            } catch (ArrayIndexOutOfBoundsException e){
                //System.out.println("error");
            }
            //System.out.println(t.get(i).getDescription());
        }
    }
}

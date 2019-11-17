package Parser;

import Ui.Ui;
import Command.*;
import Exception.DukeException;

import java.text.*;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

/**
 * The class of Parser
 * Used to make sense of user input and prompt errors
 */
public class Parser {

    /**
     * Using switch to filter user command
     * @param commandline : its the user command input from Duke class
     * @throws DukeException : this throw an error if user type wrong command
     */

    public static Command parse (String commandline) throws DukeException
    {
        LocalDateTime dateTime = null;
        String[] command = commandline.split(" ",2);

        switch(command[0].toUpperCase())
        {
            case "LIST":
                return new ListCommand(commandline);
            case "BYE":
                return new ByeCommand();
            case "DONE":
                return new DoneCommand(commandline);
            case "DELETE":
                return new DeleteCommand(commandline);
            case "TODO":
                return new TodoCommand(commandline);
            case "DEADLINE":
                return new DeadlineCommand(commandline);
            case "EVENT":
                return new EventCommand(commandline);
            case "FIND":
                return new FindCommand(commandline);
            case "VIEW":
                return new ViewCommand(commandline);
            default:
                throw new DukeException("Please enter 'Todo' or 'Deadline /by' or 'Event /at' to create task. \n"
                +"Enter 'Delete' to remove task. 'List' to see tasks. 'View' to view schedules and 'Bye' to exit"); //+ System.lineSeparator()
        }
    }

    public static Command parseFile (String commandline) throws DukeException
    {
        /**
         * To check if the file is corrupted
         */
        String[] command = commandline.split(" ",2);
        assert  command != null; // Assume there are always T,D or E in the file
        switch(command[0].toUpperCase())
        {
            case "T":
                return new TodoCommand(commandline);
            case "D":
                return new DeadlineCommand(commandline);
            case "E":
                return new EventCommand(commandline);
            default:
                throw new DukeException("Corrupted file. Please replace the file Task.txt in Data folder");
        }
    }

    public static String parseTodo (String commandline) throws DukeException
    {
        /**
         * to check if user enter todo command correctly
         */
        String [] linearr;
        linearr = commandline.split(" ", 2);

        if (linearr.length != 2){
            throw new DukeException("Invalid Todo input. Enter todo ");
        }
        if (linearr[1].equals("")){
            throw new DukeException("Todo cannot be empty");
        }
        return linearr[1];
    }

    public static String[] parseDeadline(String commandline) throws DukeException
    {
        /**
         * to check if user enter deadline command correctly
         */
        String [] linearr;
        linearr = commandline.split(" ", 2);

        if (linearr.length !=2){
            throw new DukeException("Invalid Deadline input");
        }

        String DL_date[] = linearr[1].split("/by", 2);

        if (DL_date.length != 2){
            throw new DukeException("Invalid Deadline input. Enter deadline /by ");
        }
        if (DL_date[1].equals("")){
            throw new DukeException("Deadline date cannot be empty");
        }

        DL_date[1] = convertDateTime(DL_date[1]);
        assert DL_date !=null;  // assume the date can never = null
        return DL_date;
    }

    public static String[] parseEvent(String commandline) throws DukeException
    {
        /**
         * to check if user enter event command correctly
         */
        String [] linearr;
        linearr = commandline.split(" ", 2);

        if (linearr.length !=2){
            throw new DukeException("Invalid Event input");
        }
        String E_date[] = linearr[1].split("/at", 2);

        if (E_date.length != 2){
            throw new DukeException("Invalid Event input. Enter event /at ");
        }
        if (E_date[1].equals("")){
            throw new DukeException("Event date cannot be empty");
        }

        E_date[1] = convertDateTime(E_date[1]);
        assert E_date !=null;  // assume the date can never = null
        return E_date;
    }

    /**
     * Returns a formated time as string.
     * It checks the format of date and time from user input.
     * It accepts dd/mm/yyyy hhmm format.
     * @param userDate is a raw data input from user.
     * @return output as a desired date and time format dd MMM yyyy 'at' HHmm 'hrs'
     */

    public static String convertDateTime(String userDate) throws DukeException
    {
        String dateToConvert = userDate.trim();

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            DateFormat outputformat = new SimpleDateFormat("dd MMM yyyy hh:mm a");
            Date date =null;
            String output = null;
            try{
                date = df.parse(dateToConvert);
                output = outputformat.format(date);
                return output;
            }
            catch (ParseException e) {
                throw new DukeException("Invalid date and time format. Please use this format - dd/MM/yyyy HH:mm");
            }
    }

//    public static String parseSchedule(String userDate) throws DukeException
//    {
//        String trimedSchedule = userDate.trim();
//
//        try{
//            if(trimedSchedule)
//            return output;
//        }
//        catch (ParseException e) {
//            throw new DukeException("Invalid date and time format. Please use this format - dd/MM/yyyy HH:mm");
//        }
//    }

    public static String[] parseTodoFile (String commandline) throws DukeException
    {
        String [] linearr;
        linearr = commandline.split(" \\| ");
        return linearr;
    }

    public static String[] parseEventFile(String commandline) throws DukeException
    {
        String [] lineEvent;
        lineEvent = commandline.split(" \\| ");
        return lineEvent;
    }

    public static String[] parseDeadlineFile(String commandline) throws DukeException
    {
        String [] lineDeadline;
        lineDeadline = commandline.split(" \\| ");
        return lineDeadline;
    }

    public static int parseIntegerParameter(String commandline) throws DukeException
    {
        String [] linearr;
        linearr = commandline.split(" ", 2);

        if (linearr.length !=2){
            throw new DukeException("Invalid command");
        }
        if (linearr[1].equals("")) {
            throw new DukeException("Invalid input");
        }

        int intNum=0;
        try{
            intNum = Integer.parseInt(linearr[1]);
        }
        catch (NumberFormatException  e)
        {
            Ui.showError("Incorrect command" + e.getMessage());
            throw new DukeException("Invalid input");
        }
        return intNum;
    }
}
package parser;

import command.*;
import exception.DukeException;
import ui.Ui;
import util.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Parser {
    /**
     * To parse available commands supported by this application
      * @param commandline input parameters captured from the user
     * @return Command object to be processed by relevant command, based on correctly parsed command
     * @throws DukeException To check if invalid command, otherwise will provide more information
     */
    public static Command parse (String commandline) throws DukeException
    {
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
            case "FINDDATE":
                return new FindDateCommand(commandline);
            case "SORT":
                return new SortCommand(commandline);
            case "FIND":
                return new FindCommand(commandline);
            case "HELP":
                return new HelpCommand(commandline);
            default:
                throw new DukeException("Invalid Command. Please try again, or type 'Help' for help" + System.lineSeparator());
        }
    }

    /**
     * Parse strings that are loaded from the file.
     * @param commandline a string that is loaded from the file
     * @return Command object to be processed by relevant command, based on correctly parsed command
     * @throws DukeException To check if invalid command, otherwise will provide more information
     */
    public static Command parseFile (String commandline) throws DukeException
    {

        String[] command = commandline.split(" ",2);

        switch(command[0].toUpperCase())
        {
            case "T":
                return new TodoCommand(commandline);
            case "D":
                return new DeadlineCommand(commandline);
            case "E":
                return new EventCommand(commandline);
            default:
                throw new DukeException("Invalid File format. Please try again" + System.lineSeparator());
        }
    }

    /**
     * To parse todo type of as task
     * @param commandline commandline paramters supplied by the user based on user input
     * @return relevant task description to be captured by the application
     * @throws DukeException To check if any invalid parameters, such as missing task description.
     * Otherwise will provide more information
     */
    public static String parseTodo (String commandline) throws DukeException
    {
        String [] linearr;
        linearr = commandline.split(" ", 2);
        //to check size of linearr
        if (linearr.length != 2){
            throw new DukeException("Invalid Todo parameters");
        }
        if (linearr[1].equals("")){
            throw new DukeException("Invalid empty Todo parameters");
        }

        return linearr[1];
    }

    /**
     * To parse event related string input captured by the user
     * @param commandline commandline paramters supplied by the user based on user input
     * @return array of strings to be processed by event command.
     * @throws DukeException To check if invalid command, otherwise will provide more information
     */
    public static String[] parseEvent(String commandline) throws DukeException
    {
        String [] linearr;
        linearr = commandline.split(" ", 2);
        if (linearr.length !=2){
            throw new DukeException("Invalid event Parameters");
        }
        String lineEvent[] = linearr[1].split("/at", 2);

        if (lineEvent.length != 2){
            throw new DukeException("Invalid Event parameters");
        }
        // to check second parameter
        if (lineEvent[1].equals("")){
            throw new DukeException("Invalid empty Deadline parameters");
        }
        lineEvent[1] = lineEvent[1].toString().trim();
        return lineEvent;
    }

    /**
     * To parse Deadline related string from file
     *
     * @param commandline commandline paramters supplied by loaded file
     * @return array of strings to be processed by Deadline Command
     * @throws DukeException To check if invalid command, otherwise will provide more information
     */
    public static String[] parseDeadline(String commandline) throws DukeException
    {
        String [] linearr;
        linearr = commandline.split(" ", 2);
        if (linearr.length !=2){
            throw new DukeException("Invalid deadline Parameters");
        }

        String lineDeadline[] = linearr[1].split("/by", 2);
        if (lineDeadline.length != 2){
            throw new DukeException("Invalid Deadline parameters");
        }
        // to check second parameter
        if (lineDeadline[1].equals("")){
            throw new DukeException("Invalid empty Deadline parameters");
        }
        lineDeadline[1] = lineDeadline[1].toString().trim();
        return lineDeadline;
    }

    /**
     * To parse Todo related string from file
     *
     * @param commandline commandline string supplied by loaded file
     * @return array of strings to be processed by Todo command
     * @throws DukeException To check if invalid format, otherwise will provide more information
     */
    public static String[] parseTodoFile (String commandline) throws DukeException
    {
        String [] linearr;
        linearr = commandline.split(" \\| ");
        if (linearr.length!=3)
        {
            throw new DukeException("Invalid todo file format. task will not be loaded");
        }
        return linearr;
    }

    /**
     * To parse Event related string from file
     * @param commandline commandline string supplied by loaded file
     * @return array of strings to be processed by Event command
     * @throws DukeException To check if invalid format, otherwise will provide more information
     */
    public static String[] parseEventFile(String commandline) throws DukeException
    {
        String [] lineEvent;
        lineEvent = commandline.split(" \\| ");
        if (lineEvent.length!=4)
        {
            throw new DukeException("Invalid event file format. Event task will not be loaded");
        }
        return lineEvent;
    }

    /**
     * To parse Deadline related string from file
     * @param commandline commandline string supplied by loaded file
     * @return array of strings to be processed by Event command
     * @throws DukeException To check if invalid format, otherwise will provide more information
     */
    public static String[] parseDeadlineFile(String commandline) throws DukeException
    {
        String [] lineDeadline;
        lineDeadline = commandline.split(" \\| ");
        if (lineDeadline.length!=4)
        {
            throw new DukeException("Invalid event file format. Event task will not be loaded");
        }

        return lineDeadline;
    }

    /**
     * Parse integer parameter used by Done or Delete command.
     * @param commandline commandline string provided by user input
     * @return integer value of the input parameters
     * @throws DukeException will throw exception if it is invalid parameter or empty parameter
     */
    public static int parseIntegerParameter(String commandline) throws DukeException
    {
        String [] linearr;
        linearr = commandline.split(" ", 2);
        if (linearr.length !=2){
            throw new DukeException("Invalid command Parameters");
        }
        if (linearr[1].equals("")) {
            throw new DukeException("Invalid empty parameters");
        }
        if (linearr[1].equals("")) {
            throw new DukeException("Invalid empty parameters");
        }
        int intNumber=0;
        try{
            intNumber = Integer.parseInt(linearr[1]);

        }
        catch (NumberFormatException  e)
        {
            Ui.showError("Incorrect command parameter " + e.getMessage());
            throw new DukeException("Invalid parameter - expecting Integer");
        }
        return intNumber;
    }

    /**
     *  To parse command line to finddate command
     * @param commandline input parameters provided by the user
     * @return LocalDate objects to be parsed and processed by finddate command.
     * @throws DukeException to check if the date format supplied is in dd/MM/yyyy format
     */
    public static LocalDate parseFindDate(String commandline) throws DukeException
    {
        String [] findDate;
        findDate = commandline.split(" ");

        if (findDate.length != 2){
            throw new DukeException("Invalid Find Date parameters. Usage: Find dd/MM/yyyy");
        }

        try{
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            assert findDate[1].length()==10:findDate[1].length(); //expected length of Date string is 10 chars
            LocalDate d = LocalDate.parse(findDate[1], df);
            return d;
        }catch (DateTimeParseException e){
            throw new DukeException("invalid date time format. Please use the following format - dd/MM/yyyy");
        }
    }

    /**
     * Ti parse user input to be processed by Find Command
     * @param commandline user input provided by the user
     * @return return a single search keyword
     * @throws DukeException Will throw exception if no parameter is supplied
     */
    public static String parseFindText(String commandline) throws DukeException
    {
        String [] findText;
        findText = commandline.split(" ");

        if (findText.length != 2){
            throw new DukeException("Invalid Find parameters. Usage: Find <search string>");
        }
        return findText[1].trim();
    }

    /**
     * To parse help parameters based on user provided string
     * @param commandline user input provided by the user
     * @return help string for a specific command
     * @throws DukeException
     */
    public static String parseHelp (String commandline) throws DukeException
    {
        String [] linearr;
        linearr = commandline.split(" ", 2);
        //to check size of linearr
        if (linearr.length != 2){
            return "";
        }
        if (linearr[1].equals("")){
            return "";
        }

        return linearr[1];
    }
}

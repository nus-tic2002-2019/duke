package Parser;

//import Command.Command;
//import Command.ByeCommand;
//import Command.TodoCommand;
import Command.*;
import Duke.Duke;
import Exception.DukeException;
import Ui.Ui;

//import Command;
public class Parser {
//    public Parser(){
//
//    }
    public static Command parse (String commandline) throws DukeException
    {
        String[] command = commandline.split(" ",2);
//        return new ByeCommand();
        switch(command[0].toUpperCase())
        {
            case "LIST":
                //System.out.println("");
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
            default:
                throw new DukeException("Invalid Command. Please try again" + System.lineSeparator());
        }
    }

    public static Command parseFile (String commandline) throws DukeException
    {
        String[] command = commandline.split(" ",2);
//        return new ByeCommand();
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
        return lineEvent;
    }

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
        return lineDeadline;
    }

    public static String[] parseTodoFile (String commandline) throws DukeException
    {
        String [] linearr;
        linearr = commandline.split(" \\| ");
//        System.out.println("Parse todofile");
//        System.out.println(linearr);

//        if (linearr.length != 2){
//            throw new DukeException("Invalid Todo parameters");
//        }
//        if (linearr[1].equals("")){
//            throw new DukeException("Invalid empty Todo parameters");
//        }

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
            //throw new NumberFormatException();
            throw new DukeException("Invalid parameter - expecting Integer");
        }
        return intNumber;
    }

}

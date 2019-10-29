package parser;

import command.*;
import error.*;
import task.*;
import java.time.LocalDate;

public class Parser {

    /**
     * Parsing text file into Task
     * @param command
     * @return Task
     */
    public static Task createTodo(String command){
        String[] components = command.split(" \\| ");
        Task result = null;
        switch(components[0]){
            case "T" :
                result = new Todo(components[2], Integer.parseInt(components[1]));
                break;
            case "D" :
                result = new Deadline(components[2], Integer.parseInt(components[1]), LocalDate.parse(components[3]));
                break;
            case "E" :
                result =  new Event(components[2], Integer.parseInt(components[1]), LocalDate.parse(components[3]));
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * Parsing user-input commands into Command
     * @param args
     * @return Command
     * @throws StringIndexOutOfBoundsException
     * @throws IllegalStringException
     */
    public static Command parse(String args) throws StringIndexOutOfBoundsException, IllegalStringException{
        String[] inputs = args.split(" ");
        Command output = null;
        LocalDate date = null;
        switch(inputs[0]){
            case "todo":
                output = new AddCommand(inputs[0], inputs[1]);
                break;
            case "event":
            case "deadline":
                date = LocalDate.parse(args.substring(args.lastIndexOf("/by ") + 4));
                output = new AddCommand(inputs[0], inputs[1], date);
                break;
            case "delete":
                // try if delete never include the number
                output = new DeleteCommand(inputs[0], Integer.parseInt(inputs[1]) - 1);
                break;
            case "bye":
                output = new ExitCommand(true);
                break;
            case "done":
                output = new DoneCommand(inputs[0], Integer.parseInt(inputs[1]) - 1);
                break;
            case "list":
                output = new ListCommand();
                break;
            default:
                throw new IllegalStringException();
        }
        return output;
    }

}

package parser;

import command.*;
import error.*;
import Task.*;


public class Parser {

    public static Task createTodo(String command){
        String[] components = command.split(" \\| ");
        Task result = null;
        switch(components[0]){
            case "T" :
                result = new Todo(components[2], Integer.parseInt(components[1]));
                break;
            case "D" :
                result = new Deadline(components[2], Integer.parseInt(components[1]), components[3]);
                break;
            case "E" :
                result =  new Event(components[2], Integer.parseInt(components[1]), components[3]);
                break;
            default:
                break;
        }
        return result;
    }

    public static Command parse(String args) throws StringIndexOutOfBoundsException, IllegalStringException{
        String[] inputs = args.split(" ");
        Command output = null;
        switch(inputs[0]){
            case "todo":
                output = new AddCommand(inputs[0], inputs[1]);
                break;
            case "event":
            case "deadline":
                output = new AddCommand(inputs[0], inputs[1], inputs[2]);
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

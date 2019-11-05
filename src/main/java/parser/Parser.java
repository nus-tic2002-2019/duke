package parser;

import command.*;
import error.*;
import task.*;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;


public class Parser {

    /**
     * Parses user input into command for execution
     * @param args
     * @return the command based on the user input
     * @throws IllegalStringException
     * @throws ArrayIndexOutOfBoundsException
     * @throws InvalidPriorityException
     * @throws MissingIndexException
     * @throws MissingDateException
     */
    //runtime error such as ArrayIndexOutOfBounds should not be shown on method signature
    public static Command parse(String args) throws IllegalStringException,
            ArrayIndexOutOfBoundsException,
            InvalidPriorityException,
            MissingIndexException,
            MissingDateException{
        String[] inputs = args.split(" ");
        switch(inputs[0]){
            case AddToDoCommand.COMMAND_WORD:
                return prepareTodo(inputs);
            case AddEventCommand.COMMAND_WORD:
                return prepareEvent(args, inputs);
            case AddDeadlineCommand.COMMAND_WORD:
                return prepareDeadline(args, inputs);
            case DeleteCommand.COMMAND_WORD:
                // try if delete never include the number
                return prepareDel(inputs);
            case ExitCommand.COMMAND_WORD:
                return prepareExit();
            case DoneCommand.COMMAND_WORD:
                return prepareDone(inputs);
            case ListCommand.COMMAND_WORD:
                return prepareList();
            case FindCommand.COMMAND_WORD:
                return prepareFind(inputs);
            default:
                throw new IllegalStringException("Invalid Command: Please refer to the API to find the list of legal commands.");
        }
    }

    //for high cohesion creating new instance should be a new method by itself

    /**
     * Parses arguments in the context of todo command
     * @param inputs
     * @return the prepared command
     */
    private static Command prepareTodo(String[] inputs) throws InvalidPriorityException{
        try{
            final String description =  inputs[1];
            final int priorityValue = Integer.parseInt(inputs[inputs.length - 1]);
            return new AddToDoCommand(description, priorityValue);
        } catch (NumberFormatException e){
            throw new InvalidPriorityException("Please remember to key in your priority value for this todo task");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidPriorityException("Please remember to key in your priority value for this todo task");
        }
    }

    /**
     * Parses arguments in the context of event command
     * @param inputs
     * @param args
     * @return the prepared command
     */
    private static Command prepareEvent(String args, String[] inputs) throws MissingDateException{
        try{
            final LocalDate date = LocalDate.parse(args.substring(args.lastIndexOf("/by ") + 4, args.length()-2));
            final String description = inputs[1];
            final int priorityValue = Integer.parseInt(inputs[inputs.length - 1]);
            return new AddEventCommand(description, date, priorityValue);
        } catch (DateTimeParseException e) {
            throw new MissingDateException("Invalid Input: Please follow the deadline syntax [DESCRIPTION] /by [DATE(YYYY-MM-DD)] [PRIORITY VALUE]");
        } catch (StringIndexOutOfBoundsException e){
            throw new MissingDateException("Invalid Input: Please follow the deadline syntax [DESCRIPTION] /by [DATE(YYYY-MM-DD)] [PRIORITY VALUE]");
        }
    }

    /**
     * Parses arguments in the context of deadline command
     * @param inputs
     * @param args
     * @return the prepared command
     */
    private static Command prepareDeadline(String args, String[] inputs) throws MissingDateException{
        try{
            final LocalDate date = LocalDate.parse(args.substring(args.lastIndexOf("/by ") + 4, args.length()-2));
            final String description = inputs[1];
            final int priorityValue = Integer.parseInt(inputs[inputs.length - 1]);
            return new AddDeadlineCommand(description, date, priorityValue);
        } catch (DateTimeParseException e) {
            throw new MissingDateException("Invalid Input: Please follow the deadline syntax [DESCRIPTION] /by [DATE(YYYY-MM-DD)] [PRIORITY VALUE]");
        } catch (StringIndexOutOfBoundsException e){
            throw new MissingDateException("Invalid Input: Please follow the deadline syntax [DESCRIPTION] /by [DATE(YYYY-MM-DD)] [PRIORITY VALUE]");
        }
    }

    /**
     * Parses arguments in the context of delete command
     * @param inputs
     * @return the prepared command
     */
    private static Command prepareDel(String[] inputs) throws MissingIndexException{
        try{
            final int index = Integer.parseInt(inputs[1]) - 1;
            return new DeleteCommand(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingIndexException("Invalid Index: Please remember to key in the index you want to mark as done");
        }

    }

    /**
     * Parses arguments in the context of exit command
     * @return the prepared command
     */
    private static Command prepareExit(){
        return new ExitCommand();
    }

    /**
     * Parses arguments in the context of done command
     * @param inputs
     * @return the prepared command
     */
    private static Command prepareDone(String[] inputs) throws MissingIndexException {
        try{
            final int index = Integer.parseInt(inputs[1]) - 1;
            return new DoneCommand(index);
        } catch (ArrayIndexOutOfBoundsException e){
            throw new MissingIndexException("Invalid Index: Please remember to key in the index you want to mark as done");
        }

    }

    /**
     * Parses arguments in the context of list command
     * @return the prepared command
     */
    private static Command prepareList(){
        return new ListCommand();
    }

    /**
     * Parses arguments in the context of find command
     * @param inputs
     * @return
     */
    private static Command prepareFind(String[] inputs) throws MissingIndexException{
        try{
            return new FindCommand(inputs[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingIndexException("Invalid Input: Please remember to key in the search keyword");
        }
    }

    /**
     * Parsing text file into Task
     *
     * @param command stored individual command from the text file
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

}

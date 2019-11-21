package Parser;
import Commands.*;
import Exceptions.DukeEmptyException;
import Exceptions.DukeException;
import Exceptions.DukeOutOfBoundsException;
import Exceptions.InvalidDateException;
import Storage.Storage;
import TaskList.TaskList;
import Tasks.Events;
import Ui.Ui;

import javax.sound.midi.SysexMessage;

public class Parser {

    private static boolean exit = true;

    private static Ui ui = new Ui();

    /**
     * Parses the user input to obtain commands based on User Input.
     * Each Command is then returned for Execution to take place based on the Commands returned.
     * @param userInput a String of User Input
     * @param tasklist a list of tasks
     * @return parse Parsed command for execution
     * @throws DukeEmptyException
     * @throws DukeOutOfBoundsException
     * @throws InvalidDateException
     * @throws DukeException
     */
    public static Command parse(String userInput, TaskList tasklist) throws DukeEmptyException,
            DukeOutOfBoundsException,
            InvalidDateException,NumberFormatException, DukeException{
            String parsed = parsed(userInput)[0].toLowerCase();



            switch(parsed) {
                case addByeCommand.COMMAND:
                    exit = false;
                    return new addByeCommand(false);

                case addListCommand.COMMAND:
                    return list(userInput, tasklist);

                case addTodoCommand.COMMAND:
                    return todo(userInput);

                case addDoneCommand.COMMAND:
                    return done(userInput, tasklist);

                case addDeadlineCommand.COMMAND:
                    return deadline(userInput);

                case addEventCommand.COMMAND:
                    return event(userInput);

                case addDeleteCommand.COMMAND:
                    return delete(userInput, tasklist);

                case addFindCommand.COMMAND:
                    return find(userInput);
                default:
                    throw new DukeException("     Please Key in a correct Command.");
            }
    }

    /**
     * Return exit Status to end program
     * @return exit which is the boolean to end the program
     */
    public boolean getExitStatus(){
        return exit;
    }

    /**
     * Returns a new find command for Execution
     * @param inputs a String of User Input
     * @return command Find
     * @throws DukeException
     */
    private static Command find(String inputs) throws DukeException {

        try{
            String[] tmp = inputs.split(" ");
            assert tmp != null : "input cannot be nothing";
            System.out.println(tmp[0]);

            return new addFindCommand(tmp[1]);
        }
        catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("     Search command cannot be empty");
        }
    }

    /**
     * Returns a new list command for Execution
     * @param inputs a String of User Input
     * @param tasklist a list of Tasks
     * @return command list
     * @throws DukeException
     */
    private static Command list(String inputs, TaskList tasklist) throws DukeException{
        try {
            if(tasklist.getSize() == 0){
                throw new DukeException("     ☹ OOPS!!! The list is empty.");
            }

            return new addListCommand(inputs);
        }
        catch (DukeException e){
            throw new DukeException("     ☹ OOPS!!! The list is empty.");
        }
    }

    /**
     * Returns a new delete command for Execution
     * @param inputs a String of User Input
     * @param tasklist a list of tasks
     * @return command delete
     * @throws DukeEmptyException
     * @throws NumberFormatException
     * @throws DukeOutOfBoundsException
     */
    private static Command delete(String inputs, TaskList tasklist) throws DukeEmptyException, NumberFormatException, DukeOutOfBoundsException{
        String tmp = "";
        int storeTaskNo = 0;
        if(inputs.length() == 6){
            tmp = "";
            storeTaskNo = 0;
        }
        else {
            tmp = inputs.substring(inputs.indexOf("delete") + 5, inputs.length()).trim();
            if (!tmp.equals("")) {
                storeTaskNo = Integer.parseInt(parsed(inputs)[1]);
            }
        }
        try {
            if(inputs.substring(inputs.indexOf("delete")).length() == 6){
                throw new DukeEmptyException("delete");
            }
            if((inputs.substring(inputs.indexOf("delete")+7, inputs.length())).trim().equals("")){
                System.out.println("GOT HERE");
                throw new DukeEmptyException("delete");
            }
            int t = Integer.parseInt((inputs.substring(inputs.indexOf("delete")+7).trim()));

            if(storeTaskNo > tasklist.getSize() || storeTaskNo > t || storeTaskNo == 0) {
                throw new DukeOutOfBoundsException("done");
            }
            if(parsed(inputs)[1].toString()==null){
                throw new NumberFormatException("     ☹ OOPS!!! The task number must be a numerical value.");
            }
            String taskB4Del = tasklist.getTask(Integer.parseInt(parsed(inputs)[1])-1).toString();

            return new addDeleteCommand(storeTaskNo-1, taskB4Del);
        } catch (DukeEmptyException e){
            throw new DukeEmptyException("delete");
        } catch (NumberFormatException e){
            throw new NumberFormatException("     ☹ OOPS!!! The task number must be a numerical value.");

        } catch (DukeOutOfBoundsException e){
            throw new DukeOutOfBoundsException("     ☹ OOPS!!! The task number must be within range.");
        }
    }

    /**
     * return a new todo Command for execution
     * @param inputs a String of User Input
     * @return command todo
     * @throws DukeEmptyException
     * @throws NumberFormatException
     * @throws DukeException
     */
    private static Command todo(String inputs) throws DukeEmptyException,NumberFormatException, DukeException {

        try {
            if(inputs.substring(inputs.indexOf("todo")).length() == 4){
                throw new DukeEmptyException("todo");
            }
            if((inputs.substring(inputs.indexOf("todo")+5, inputs.length())).trim().equals("")){
                throw new DukeEmptyException("todo");
            }
            return new addTodoCommand(parsed(inputs)[1]);
          //  return new addTodoCommand(inputs);

        } catch (DukeEmptyException e){
            throw new DukeEmptyException("todo");
        } catch (NumberFormatException e){
            throw new NumberFormatException("     ☹ OOPS!!! The task number must be a numerical value.");

        } catch (Exception e){
            throw new DukeException("     Caught an Exception ");
        }
    }


    /**
     * return a new Done Command for Execution
     * @param inputs a String of User Input
     * @param tasklist a list of tasks
     * @return command done
     * @throws DukeEmptyException
     * @throws NumberFormatException
     * @throws DukeOutOfBoundsException
     */
    private static Command done(String inputs, TaskList tasklist) throws DukeEmptyException, NumberFormatException, DukeOutOfBoundsException{
        String tmp = "";
        int storeTaskNo = 0;

        if(inputs.length() == 4){
            tmp = "";
            storeTaskNo = 0;
        }
        else {
            tmp = inputs.substring(inputs.indexOf("done") + 5, inputs.length()).trim();
            if (!tmp.equals("")) {
                storeTaskNo = Integer.parseInt(parsed(inputs)[1]);
            }
        }
        try {
            if(inputs.substring(inputs.indexOf("done")).length() == 4
                    || inputs.substring(inputs.indexOf("done")+5, inputs.length()).trim().equals("")){
                throw new DukeEmptyException("done");
            }
            if(tmp.equals("")){
                throw new DukeEmptyException("done");
            }
            int t = Integer.parseInt((inputs.substring(inputs.indexOf("done")+5).trim()));

            if(storeTaskNo > tasklist.getSize() || storeTaskNo > t || storeTaskNo == 0 || t > tasklist.getSize()) {
                throw new DukeOutOfBoundsException("     ☹ OOPS!!!  The task number must be within range.");
            }
            if(parsed(inputs)[1].toString()==null){
                throw new NumberFormatException("     ☹ OOPS!!! The task number must be a numerical value.");
            }
            return new addDoneCommand(storeTaskNo-1);
        }
        catch (NumberFormatException e){
            ui.Line();
            throw new NumberFormatException("     ☹ OOPS!!! The task number must be a numerical value.");
        }
        catch (DukeOutOfBoundsException e){
            throw new DukeOutOfBoundsException("     ☹ OOPS!!! The task number must be within range.");

        }catch (DukeEmptyException e){
            throw new DukeEmptyException("done");
        }

    }


    /**
     * return a new Deadline command for Execution
     * @param inputs a String of User Input
     * @return command deadline
     * @throws DukeEmptyException
     * @throws InvalidDateException
     */
    private static Command deadline(String inputs) throws DukeEmptyException, DukeOutOfBoundsException, InvalidDateException {
        try {
            if(inputs.substring(inputs.indexOf("deadline")).length() == 8
                    || inputs.substring(inputs.indexOf("deadline")+9, inputs.length()).trim().equals("")){
                throw new DukeEmptyException("deadline");
            }
            String des = inputs.substring(inputs.indexOf("deadline")+9, inputs.indexOf("by")-1);
            System.out.println(des);
            return new addDeadlineCommand(des, new myMethods().dteToString(inputs));
        }
        catch (DukeEmptyException e){
            throw new DukeEmptyException("deadline");
            //ui.Line();
        }
        catch (StringIndexOutOfBoundsException e){
            throw new DukeOutOfBoundsException("     Please key in Correct Syntax: [deadline] [description] [/by] [dd-mmm-yyyy HHmm]");
        }
        catch (InvalidDateException e){
            throw new InvalidDateException("     ☹ OOPS!!! Date, Syntax Wrong, Please use : DD-MMMM-YYYY(13-Oct-2019) HHmm (1000)");
        }
    }


    /**
     * return a new Command event for Execution
     * @param inputs a String of User Input
     * @return command event
     * @throws DukeEmptyException
     * @throws InvalidDateException
     */
    private static Command event(String inputs) throws DukeEmptyException, InvalidDateException{
        try {
            if(inputs.substring(inputs.indexOf("event")).length() == 5
             || (inputs.substring(inputs.indexOf("event")+5, inputs.length())).trim().equals("")){
                throw new DukeEmptyException("event");
            }

            String des = inputs.substring(inputs.indexOf("event")+6, inputs.indexOf("at")-1);
            String at = inputs.substring(inputs.indexOf("at")+3, inputs.length());


            return new addEventCommand(des, at);
        }
        catch (DukeEmptyException e){
            throw new DukeEmptyException("event");
        }
        catch (NumberFormatException e){
            throw new NumberFormatException("     ☹ OOPS!!! The task number must be a numerical value.");
        }
    }

    public static String[] parsed(String input){
        String[] act = input.split(" ",2);
        return act;
    }

    public String[] parsedInput(String input){
        String[] act = input.split(" ",2);
        return act;
    }
}





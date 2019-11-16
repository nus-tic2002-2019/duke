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
    public static Command parse(String userInput, TaskList tasklist) throws DukeEmptyException,
            DukeOutOfBoundsException,
            InvalidDateException, DukeException{
        String parsed = parsed(userInput)[0].toLowerCase();

        Storage storage = new Storage();


        try {
            switch(parsed) {
                case addByeCommand.COMMAND:
                    exit = false;
                    return new addByeCommand(false);

                case  addListCommand.COMMAND:
                    return list(userInput, tasklist);

                case  addTodoCommand.COMMAND:
                    return todo(userInput);

                case  addDoneCommand.COMMAND:
                    return done(userInput, tasklist);

                case addDeadlineCommand.COMMAND:
                    return deadline(userInput);

                case addEventCommand.COMMAND:
                    return event(userInput);

                default:
                    throw new DukeException();
            }
        }
        catch (DukeEmptyException e){
            throw new DukeEmptyException(e.getMessage());
        }
        catch (DukeOutOfBoundsException e){
            throw new DukeOutOfBoundsException("     ☹ OOPS!!! The task number must be within range.");
        }
        catch (InvalidDateException e){
            throw new InvalidDateException(e.getMessage());
        }
        catch (DukeException e){
            throw new DukeException();
        }


        //return new addByeCommand(true);

    }

    public boolean getExitStatus(){
        return exit;
    }


    private static Command list(String inputs, TaskList tasklist) throws DukeException{
        try {
            if(tasklist.getSize() == 0){
                throw new DukeException();
            }

            return new addListCommand(inputs);
        }
        catch (DukeException e){
            ui.Line();
            System.out.println("     ☹ OOPS!!! The list is empty.");
            ui.Line();
            throw new DukeException();
        }
    }

    /**
     * return a new todo Command for execution
     * @param inputs
     * @return
     * @throws DukeEmptyException
     * @throws NumberFormatException
     * @throws DukeException
     */
    private static Command todo(String inputs) throws DukeEmptyException,NumberFormatException, DukeException {

//            String description = inputs[1];
//            for(int i = 2; i < inputs.length - 1; i++){
//                System.out.println(inputs[i]);
//                description += " " + inputs[i];
//            }
        try {
            if(inputs.substring(inputs.indexOf("todo")).length() == 4){
                throw new DukeEmptyException("todo");
            }
            if((inputs.substring(inputs.indexOf("todo")+5, inputs.length())).trim().equals("")){
                throw new DukeEmptyException("todo");
            }
            return new addTodoCommand(new myMethods().parsed(inputs)[1]);
        } catch (DukeEmptyException e){
//            System.out.println(e.getMessage());
            throw new DukeEmptyException(e.getMessage());
        } catch (NumberFormatException e){
//            System.out.println("     ☹ OOPS!!! The task number must be a numerical value.");
            throw new NumberFormatException("     ☹ OOPS!!! The task number must be a numerical value.");

        } catch (Exception e){
            throw new DukeException();
        }
        //return new addTodoCommand(new myMethods().parsed(inputs)[1]);
    }


    /**
     * return a new Done Command for Execution
     * @param inputs
     * @param tasklist
     * @return
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
            if(storeTaskNo > tasklist.getSize() || storeTaskNo > t || storeTaskNo == 0) {
                throw new DukeOutOfBoundsException("done");
            }
            if(parsed(inputs)[1].toString()==null){
                throw new NumberFormatException();
            }
            return new addDoneCommand(storeTaskNo-1);
        }
        catch (NumberFormatException e){
            throw new NumberFormatException("     ☹ OOPS!!! The task number must be a numerical value.");
            //ui.Line();
        }
        catch (DukeOutOfBoundsException e){
            throw new DukeOutOfBoundsException("     ☹ OOPS!!! The task number must be within range.");

        }catch (DukeEmptyException e){
            throw new DukeEmptyException(e.getMessage());
            //ui.Line();
        }

    }


    /**
     * return a new Deadline command for Execution
     * @param inputs
     * @return
     * @throws DukeEmptyException
     * @throws InvalidDateException
     */
    private static Command deadline(String inputs) throws DukeEmptyException, InvalidDateException {
        try {
            if(inputs.substring(inputs.indexOf("deadline")).length() == 8
                    || inputs.substring(inputs.indexOf("deadline")+9, inputs.length()).trim().equals("")){
                throw new DukeEmptyException("deadline");
            }
            String des = inputs.substring(inputs.indexOf("deadline")+9, inputs.indexOf("by")-1);
            return new addDeadlineCommand(des, new myMethods().dteToString(inputs));
        }
        catch (DukeEmptyException e){
            throw new DukeEmptyException(e.getMessage());
            //ui.Line();
        }
        catch (InvalidDateException e){
            throw new InvalidDateException(e.getMessage());
        }
    }


    private static Command event(String inputs) throws DukeEmptyException, InvalidDateException{
        try {
            if(inputs.substring(inputs.indexOf("event")).length() == 5
             || (inputs.substring(inputs.indexOf("event")+5, inputs.length())).trim().equals("")){
                throw new DukeEmptyException("event");
            }

            String des = inputs.substring(inputs.indexOf("event")+6, inputs.indexOf("at")-1);


            return new addEventCommand(des, new myMethods().dteToString(inputs));
        }
        catch (DukeEmptyException e){
            ui.Line();
            throw new DukeEmptyException(e.getMessage() + ui.getLine());
        }
        catch (NumberFormatException e){
            throw new NumberFormatException("     ☹ OOPS!!! The task number must be a numerical value." + ui.getLine());
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





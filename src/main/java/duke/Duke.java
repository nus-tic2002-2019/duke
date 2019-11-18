package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.DukeException;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;


public class Duke {

    private static TaskList tasks;
    private static Ui userInterface;
    private static Storage storage;

    /**
     * The Duke constructor creates the main program known as Duke, and preload the tasks saved in a file.
     * @param filePath is the full path where the file is located.
     * @throws DukeException if file cannot be created.
     *
     */

    public Duke (String filePath) throws DukeException {
        userInterface = new Ui();
        userInterface.showWelcome();

        try{
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadList());
           }
        catch (DukeException e){
            tasks = new TaskList();
        }
        catch (FileNotFoundException e){
           userInterface.showLoadingError();
        }
        catch (IOException e){
            throw new DukeException("unable to create tasks.txt");
        }
    }

    /**
     * The method run starts up the user interface and receive input from the users
     * @exception DukeException if an unknown command is entered, it will show unknown Command
     */

    public void run(){
        boolean isExit = false;
        String textInput;
        while(!isExit) {
            try{

                textInput = userInterface.getTextInput().nextLine();
                userInterface.showLine();
                userInterface.dukeInput(tasks, textInput);
                if (textInput.equalsIgnoreCase("bye")) isExit = true;
            }catch (DukeException e){
                userInterface.showError(e.getMessage());
            }
            finally {
                userInterface.showLine();
            }
        }
    }

    public static void main(String[] args) throws DukeException{

       new Duke("tasks.txt").run();

    }


}

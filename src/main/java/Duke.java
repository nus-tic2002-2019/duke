import java.io.FileNotFoundException;
import java.util.Scanner;

import exception.DukeException;
import tasklist.*;
import ui.Ui;
import storage.Storage;


public class Duke {

    private static taskList tasks;
    private static Ui userInterface;
    private static Storage storage;
    private static Scanner in = new Scanner(System.in);

    /**
     * The Duke constructor creates the main program known as Duke, and preload the tasks saved in a file.
     * @param filePath is the full path where the file is located.
     * @exception DukeException if file not found
     *
     */

    public Duke (String filePath) throws DukeException {
        userInterface = new Ui();
        userInterface.showWelcome();
        storage = new Storage(filePath);
        try{
            tasks = new taskList(storage.loadList());
           }
        catch (DukeException e){
            tasks = new taskList();
        }
        catch (FileNotFoundException e){
           throw new DukeException("File not found");
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

                textInput = in.nextLine();
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

    public static void main(String[] args) throws DukeException {

       new Duke("data/tasks.txt").run();

    }


}

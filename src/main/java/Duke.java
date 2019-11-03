import java.io.FileNotFoundException;
import java.util.Scanner;

import exception.DukeException;
import tasklist.*;
import ui.Ui;
import storage.Storage;

public class Duke {
    
    //private static taskList tasks = new taskList();
    private static taskList tasks;
    private static Ui userInterface;
    private static Storage storage;
    private static Scanner in = new Scanner(System.in);

    public Duke (String filePath){
        userInterface = new Ui();
        userInterface.showWelcome();
        storage = new Storage(filePath);
        try{
            tasks = new taskList(storage.load());
           }
        catch (DukeException e){
            tasks = new taskList();
        }
        catch (FileNotFoundException e){
            System.out.println("File not Found.");
        }
    }

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
                //userInterface.showUnknownCommand();
            }
            finally {
                userInterface.showLine();
            }
        }
    }

    public static void main(String[] args) {

       new Duke("data/tasks.txt").run();

    }


}

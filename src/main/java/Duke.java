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
            tasks = new taskList();
            storage.load();
        }
        catch (DukeException e){
            tasks = new taskList();
        }
        catch (FileNotFoundException e){
            System.out.println("File not Found.");
        }
    }

    public Duke (){

        String textInput;
        userInterface = new Ui();
        userInterface.showWelcome();

        do {
         textInput = in.nextLine();
          try {
              userInterface.dukeInput(tasks, textInput);
          } catch (DukeException e){
              //do we need anything here?
          }
        }  while (!textInput.equalsIgnoreCase("bye"));
    }

    public void run(){
        boolean isExit = false;
        String textInput;
        while(!isExit) {
            try{
                userInterface.showLine();
                textInput = in.nextLine();
                userInterface.dukeInput(tasks, textInput);
                if (textInput.equalsIgnoreCase("bye")) isExit = true;
            }catch (DukeException e){}
        }
    }

    public static void main(String[] args) {

       //userInterface = new Ui();
       //userInterface.showWelcome();
        System.out.println("Choose 1 for Command Input, 2 for File input:");
        String choiceInput = in.nextLine();
        if (choiceInput.equals("1")) new Duke();
        else if (choiceInput.equals("2")) new Duke("data/tasks.txt").run();
        else System.out.println("Please choose 1 or 2");



    }


}

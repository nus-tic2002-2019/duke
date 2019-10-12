import java.util.Scanner;

import exception.DukeException;
import tasklist.*;
import ui.Ui;
import storage.Storage;

public class Duke {
    
    private static taskList tasks = new taskList();
    private static Ui userInterface;
    private static Storage storage;

    public Duke (String filePath){
        userInterface = new Ui();
        userInterface.showWelcome();
        storage = new Storage(filePath);
    }

    public static void main(String[] args) {

        userInterface = new Ui();
        userInterface.showWelcome();


        Scanner in = new Scanner(System.in);
        String textInput;

        do {
         textInput = in.nextLine();
          try {
              userInterface.dukeInput(tasks, textInput);
          } catch (DukeException e){
              //do we need anything here?
          }
        }  while (!textInput.equalsIgnoreCase("bye"));
    }


}

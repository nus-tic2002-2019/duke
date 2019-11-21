package duke;

import duke.commands.Parser;
import duke.ui.Ui;
import duke.task.*;
import duke.storage.Storage;
import duke.commands.Command;
import java.io.*;
import java.nio.file.Paths;

/**
 * Main class for duke project
 * @author Guan Hao
 */
public class Duke{
    private final String filePath = Paths.get("").toAbsolutePath().toString() + "\\src\\duke\\data\\duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();
    
    /**
     * Main method for duke, assign and initialize classes to be run
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }
/**
 * Method to run duke project
 */
    public void run() {
        Ui.welcome();
        boolean isExit = false;
        boolean isError = false;
        
        while ( !isExit ){
            String line = ui.readCommand();
            Command c = Parser.parse(line);
            if ( c.isError() ) {
                continue;
            }
            c.execute(tasks,ui,storage);
            isExit = c.isExit();
        }
    }
    
    /**
     * Main method in duke, initialize and run the project.
     * @param args 
     */
    public static void main(String[] args)  {
        new Duke().run();
    } 
    
 }
package duke;

import duke.ui.Ui;
import duke.task.*;
import duke.storage.Storage;
import duke.commands.Command;
import java.io.*;

public class Duke{
    
    static final String filePath = "C:\\Users\\lug3g\\Documents\\NetBeansProjects\\Duke\\src\\duke\\data\\duke.txt";
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui = new Ui();
    
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        boolean isExit = true;
        boolean isError = false;
        String line = "";
        
        while ( isExit ){
            line = ui.readCommand();
            Command c = Parser.parse(line);
            if ( c.isError() ) {
                continue;
            }
            c.execute(tasks,ui,storage);
            isExit = c.isExit();
        }
    }
    
    public static void main(String[] args)  {
        new Duke().run();
    } 
    
 }
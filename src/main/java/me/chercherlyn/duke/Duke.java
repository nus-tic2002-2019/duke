package me.chercherlyn.duke;

import me.chercherlyn.duke.util.Parser;
import me.chercherlyn.duke.util.Storage;
import me.chercherlyn.duke.util.TaskList;
import me.chercherlyn.duke.util.Ui;

/**
 * Launcher
 */
public class Duke {
    
    private Ui ui;
    private Parser parser;
    
    public Duke(String path) {
        Storage storage = new Storage(path);
        TaskList tasks = storage.loadTasks();
        
        ui = new Ui();
        parser = new Parser(ui, tasks, storage);
    }
    
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
    
    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        ui.println(logo);
        //ui.scenarioStart();
        System.out.println("\n");
        ui.printFancy("" +
                "Hello! I'm Duke\n" +
                "What can I do for you?");
    
        while (true) {
            ui.print("\n");
            parser.processCommand(ui.readLine());
        }
    }
}

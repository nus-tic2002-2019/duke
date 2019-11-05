import java.io.FileNotFoundException;

import command.Command;
import error.IllegalStringException;
import error.MissingDateException;
import error.InvalidPriorityException;
import error.MissingIndexException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Entry point of Duke To-do application
 * Initializes the application and starts the interaction with the user
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
     *
     * @param filename arguments supplied by the user before the program launch
     *
     */
    public Duke(String filename){
        this.ui = new Ui();
        this.storage = new Storage(filename);
        try{
            tasks = new TaskList(storage.load());
            ui.showToUser(tasks.getDescription());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /** Runs the program until termination */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                storage.save(tasks);
                isExit = c.isExit();
            } catch (IllegalStringException e) {
                ui.showError(e.getMessage());
            } catch (InvalidPriorityException e) {
                ui.showError(e.getMessage());
            } catch (MissingIndexException e) {
                ui.showError(e.getMessage());
            } catch (MissingDateException e){
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("/Users/tankaiwei/Desktop/duke/src/main/java/data/duke.txt").run();

    }
}

package Duke;

import Commands.Command;
import Storage.Storage;
import Tasks.TaskList;
import UI.Ui;
import Exception.DukeException;
import Parser.Parser;

/**
 * Create the Duke class to start of the program
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs the Duke class
     * @param filePath the filePath of the stored txt file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * this function will run the Duke program
     */
    public void run() {
        ui.showDukeWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main function to start
     * @param args
     */
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

}


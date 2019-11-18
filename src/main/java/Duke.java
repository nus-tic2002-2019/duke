import tic2002.exception.DukeException;
import tic2002.parser.Parser;
import tic2002.storage.Storage;
import tic2002.task.Task;
import tic2002.ui.Ui;

import java.util.ArrayList;

/**
 * Represents class Duke of main application.
 */
public class Duke {
    //Declare constant variables
    final String STORAGE_PATH_DUKE = "src/main/data/duke.txt";

    //Create class
    private static ArrayList<Task> tasks;
    private static Ui ui;
    private static Storage storage;
    private static Parser parser;

    //Constructor
    public Duke() {
        tasks = new ArrayList<Task>();
        ui = new Ui(tasks);
        storage = new Storage(STORAGE_PATH_DUKE, ui, tasks);
    }

    /**
     * Runs application of class Duke.
     */
    public void runApp() {
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.getUserInput();

                parser = new Parser(ui, input, tasks, storage);

                parser.runCommand();

                isExit = parser.getIsExit();
            } catch (DukeException e) {
                ui.displayErrorDuke();
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.runApp();
    }
}

import tic2002.exception.DukeException;
import tic2002.parser.Parser;
import tic2002.storage.Storage;
import tic2002.task.TaskList;
import tic2002.ui.Ui;

/**
 * Represents class Duke of main application.
 */
public class Duke {
    //Declare constant variables
    private static final String STORAGE_PATH_DUKE = "src/main/data/duke.txt"; //Debug mode
    //private static final String STORAGE_PATH_DUKE = "./duke.txt"; //Production mode

    //Create class
    private static TaskList tasks;
    private static Ui ui;
    private static Storage storage;
    private static Parser parser;

    //Constructor
    public Duke() {
        tasks = new TaskList();
        ui = new Ui(tasks);
        storage = new Storage(STORAGE_PATH_DUKE, ui, tasks);
    }

    /**
     * Runs application of class Duke.
     * Until user exits.
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
            } catch (StringIndexOutOfBoundsException e) {
                ui.displayErrorIncompleteInput();
            } catch (AssertionError e) {
                ui.displayAssertErrorMessage(e);
            } catch (IndexOutOfBoundsException e) {
                ui.displayErrorIndexInput();
            } catch (IllegalArgumentException e) {
                ui.displayErrorPriority();
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.runApp();
    }
}

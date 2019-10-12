package duke;
import command.Command;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import exception.DukeException;
import java.io.IOException;

/**
 *Represents the creation of the chat-bot.
 * */
public class Duke{
    /**
     * Creation of variables such as storage,
     * task-list
     * and ui
     * which are necessary to a task manager.
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
  /**
   * Pre-loading of any existing saving,
   * otherwise start new file.
   * */
    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e){
            ui.showToUser("☹ OOPS!!! Problem reading file. Starting with an empty task list");
            tasks = new TaskList();
        }
    }
    /**
     * Collects the input of the user to parse to respective command list,
     * to perform the respective tasks.
     * */
    public void run() throws IOException{
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser.parse(fullCommand);
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit;

            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.saveNow(storage,tasks.getTaskList());
        ui.printBye();
    }
/**
 * Runs the text according to the directory stated.
 *  */
    public static void main(String[] args) throws IOException, DukeException {
        new Duke("C:\\NUS\\test.txt").run();
    }
}
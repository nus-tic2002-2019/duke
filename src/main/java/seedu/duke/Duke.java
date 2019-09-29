package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.data.exception.DukeException;
import seedu.duke.data.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.UI;
import seedu.duke.parser.Parser;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private UI ui;

    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            // ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readUserInput();
                Command c = Parser.parseInput(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit;
            }
            catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/taskList.txt").run();
    }

    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }
}
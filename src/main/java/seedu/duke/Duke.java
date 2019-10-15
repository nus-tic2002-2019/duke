package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.data.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.UI;
import seedu.duke.parser.Parser;

public class Duke{

    private Storage storage;
    private TaskList taskList;
    private UI ui;


    public Duke() {
        ui = new UI();
        storage = new Storage("data/taskList.txt");
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
                String input = ui.readUserInput();
                Command command = Parser.parseInput(input);
                command.execute(taskList, ui, storage);
                isExit = command.isExit;
            }
            catch (Exception e) {
                ui.showError(e);
            }
        }
    }

    String getResponse(String input) {
        try {
            Command command = Parser.parseInput(input);
            command.execute(taskList, ui, storage);
            return ui.showOutputToUser();
        } catch (Exception e) {
            return ui.showError(e);
        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}
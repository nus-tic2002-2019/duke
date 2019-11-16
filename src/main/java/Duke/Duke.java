package Duke;

import Commands.Command;
import Storage.Storage;
import Tasks.TaskList;
import UI.Ui;
import Exception.DukeException;
import Parser.Parser;

public class Duke {


    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
       // tasks = new Tasks.TaskList();

        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            ui.showLoadingError();
            tasks = new TaskList();

        }

    }

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

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }

}


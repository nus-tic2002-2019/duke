package Duke;

import java.io.IOException;

import Command.Command;
import Exception.DukeException;
import Parser.Parser;
import Storage.Storage;
import Tasklist.*;
import Ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
//    private Parser par
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        //...
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        storage.save(tasks);
        ui.showBye();
    }

    public static void main(String[] args) {
//        new Duke("data/tasks.txt").run();
        new Duke("data/Duke.txt").run();
    }
}
// list
//  todo borrow book
//  deadline return book /by Sunday
//  event project meeting /at Mon 2-4pm
// todo read this

// Todo :
// 1. Save into file
// 2. Load from file into tasklists
// 3. strengthen exceptions catching
// 4.
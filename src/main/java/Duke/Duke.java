<<<<<<< HEAD
package Duke;

import Ui.Ui;
import Tasklist.*;
import Storage.*;
import Parser.Parser;
import Command.Command;

import Exception.DukeException;
import java.io.IOException;

/**
 * Duke is a task scheduler using console for input and output of task and schedules
 */

public class Duke {

    public static void main(String[] args) {
        String filePath = "data/Task.txt";
        new Duke(filePath).run();
    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        }
        catch (IOException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
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
        ui.sayBye();
    }
}
=======
package Duke;

import Ui.Ui;
import Tasklist.*;
import Storage.*;
import Parser.Parser;
import Command.Command;

import Exception.DukeException;
import java.io.IOException;

/**
 * Duke is a task scheduler using console for input and output of task and schedules
 */

public class Duke {

    public static void main(String[] args) {
        String filePath = "data/Task.txt";
        new Duke(filePath).run();
    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        }
        catch (IOException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
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
        ui.sayBye();
    }
}
>>>>>>> 052b4d35a43d775d5c0194545db262f21223b209

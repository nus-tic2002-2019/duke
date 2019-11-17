package duke;

import duke.command.Command;
import duke.others.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.others.DateFormat;

import java.io.*;
import java.time.format.DateTimeParseException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.print("No existing To Do List. Generating a new one :)");
            tasks = new TaskList();
        } catch (IOException e) {
            ui.print("Unable to read specific lines in data.txt file");
        } catch(DateTimeParseException e) {
            ui.print("Dates in the data.txt file is incompatible. Duke reads date in " + DateFormat.STANDARD + " only");
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
            } catch(DukeException e) {
                ui.showError(e.getMessage());
            } catch(IOException e) {
                ui.print("Line not found");
            } catch(DateTimeParseException e) {
                ui.print("Please enter the date in this format: " + DateFormat.STANDARD);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("/Users/spencernah/code/duke/data/data.txt").run();
    }
}

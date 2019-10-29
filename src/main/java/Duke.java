import command.Command;
import error.*;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.FileNotFoundException;
//make sure the code is readable and well composed

public class Duke {

    public Ui ui;
    public Storage storage;
    public TaskList tasks;

    public Duke(String filename){
        ui = new Ui();
        storage = new Storage(filename);
        try{
            tasks = new TaskList(storage.load());
            ui.showToUser(tasks.getDescription());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
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
                storage.save(tasks);
                isExit = c.isExit();
            } catch (IllegalStringException e) {
                ui.showError(e.getMessage());
            } catch (IndexOutOfBoundsException e){
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("/Users/tankaiwei/Desktop/duke/src/main/java/data/duke.txt").run();

    }
}

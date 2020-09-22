package basic;

import command.Command;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;


/**
 * Runs basic.Duke program as long as isExit is false
 * Passes tasks entered by user to Storage class
 */

public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    Duke() throws FileNotFoundException, UnsupportedEncodingException {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.load());
    }

    private void run() throws FileNotFoundException, UnsupportedEncodingException {
        storage.load();
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                storage.save(tasks.returnList());
                isExit = c.isExit();
            } catch (Exception e) {
                ui.printException(e);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        new Duke().run();
    }

}

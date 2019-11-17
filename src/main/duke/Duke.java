package main.duke;

import main.duke.command.Command;
import main.duke.exception.DukeException;
import main.duke.storage.Storage;
import main.duke.task.*;
import main.duke.ui.Ui;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Parser parser;


    public Duke() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            storage.readSaveFile();
        } catch (java.io.IOException ioException){
            System.out.println(ioException.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run(){
        boolean running = true;
        ui.printHelloMsg();
        while (running) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parseInput(fullCommand);
                c.execute(tasks, ui, storage);
                running = !c.isExit();
            } catch (DukeException e){
                ui.printErrorMsg(e);
            }
        }
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}

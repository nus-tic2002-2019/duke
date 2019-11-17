package duke;

import java.io.IOException;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;
//import java.util.Date;
//======================
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
//======================
import command.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import tasklist.*;
import ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Main application constructor to initialize duke application
     * @param filePath path of which the tasks file is located.
     */
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



    /**
     * To run Duke application and process commands based user input
     */
    public void run() {
        //...
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
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

    /**
     * Main Duke application entry point
     * @param args any arguments to be passed to Duke main application. arguments are not processed
     */
    public static void main(String[] args) {
        new Duke("data/Duke.txt").run();
        return;
    }
}

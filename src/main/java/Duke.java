/**
 * Duke class, main() is located here
 * Ensures execution of Duke program as long as isExit is false
 * @param isExit tells program to exit, set to false by default
 * Writes tasks entered by user at the end of program right before exiting
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import subclass.*;

public class Duke {
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        try {
            Storage.loadFile(filePath);
        } catch (ParseException | FileNotFoundException e) {
            Ui.showLine();
            Ui.displayError();
            Ui.showLine();
            tasks = new TaskList();
        } catch (todoException e) {
            e.printStackTrace();
        }

    }

    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
                isExit = c.isExit;
            } catch (IOException | DukeException | ParseException | todoException e) {
                Ui.showLine();
                Ui.displayError();
                Ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws DukeException, todoException, IOException, ParseException {
        //load txt file
        String file_path = "/Users/marcus/Desktop/marcus/m/NUS/TIC2002 Introduction to Software Engineering/duke_new/src/main/java/taskList.txt";
        new Duke(file_path).run();

        //save Task.txt
        Storage.writeToFile(file_path, Storage.toTxt(Task.getOutput()));

    }
}

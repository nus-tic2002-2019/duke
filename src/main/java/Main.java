import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private Storage storage;
    private Tasklist tasks;
    private UI ui;

    public Main(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new Tasklist(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new Tasklist();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch(DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        /*
         TODO Auto-generated method stub
        */
        //new Duke("data/tasks.txt").run();
        new Main("data/tasks.txt").run();
    }

}

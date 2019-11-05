import java.io.File;
import java.io.IOException;
import tasks.*;
import java.io.FileWriter;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
//import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath, tasks.getList());
    }

    public boolean isBye(String actionType) {
        return (actionType.equals("bye"));
    }

    public void run() {
        ui.showWelcome();
        String[] output = ui.takeUi();
        int i = 0;
        ArrayList<Task> userArr = tasks.getList();

        try {
            while (!isBye(output[0])) {
                switch (output[0]) {
                    case "todo":
                        tasks.addTasks(output[1]);
                        storage.appendToFile(output[0], i, " | " + output[1]);
                        break;
                    case "event": case "deadline":
                        tasks.addTasks(output[1], output[0], output[2]);
                        storage.appendToFile(output[0], i, " | " + output[1] + " | " + output[2]);
                        break;
                    case "list":
                        tasks.listTasks();
                        break;
                    case "done":
                        tasks.setDone(output[1]);
                        storage.replaceFile("done", output[1]);
                        break;
                    case "delete":
                        tasks.deleteTasks(output[1]);
                        break;
                    default:
                        break;
                }
                //tasks.getList().get(i).print();
                output = ui.takeUi();
                i++;
            }
        } catch (IOException e) {
            System.out.println("");
        }

    }

    public static void main(String[] args) {
        new Duke("data/TaskList.txt").run();
    }
}

import java.io.IOException;
import exceptions.DukeException;
import tasks.*;
import java.util.ArrayList;

public class Duke {
    private TaskList tasksList;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasksList = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println("Loading Error");
            tasksList = new TaskList();
        }
    }

    public boolean isBye(String actionType) {
        return (actionType.equals("bye"));
    }

    public void run() {
        ui.showWelcome();
        String[] output = ui.takeUi();

        ArrayList<Task> userArr = tasksList.getList();
        int i = userArr.size() ;

        try {
            while (!isBye(output[0])) {
                switch (output[0]) {
                    case "todo":
                        tasksList.addTasks(output[1]);
                        storage.appendToFile(output[0], i, " | " + output[1]);
                        i++;
                        break;
                    case "deadline":
                        tasksList.addTasks(output[1], output[0], output[2]);
                        storage.appendToFile(output[0], i, " | " + output[1] + " | " + (userArr.get(i)).getDate());
                        i++;
                        break;
                    case "event":
                        tasksList.addTasks(output[1], output[0], output[2]);
                        storage.appendToFile(output[0], i, " | " + output[1] + " | " + (userArr.get(i)).getDate() + " | " + (userArr.get(i)).getTime());
                        i++;
                        break;
                    case "list":
                        tasksList.listTasks();
                        break;
                    case "done":
                        tasksList.setDone(output[1]);
                        storage.replaceFile("done", output[1]);
                        break;
                    case "delete":
                        tasksList.deleteTasks(output[1]);
                        storage.replaceFile("delete", output[1]);
                        i--;
                        break;
                    case "find":
                        tasksList.findTasks(output[1]);
                        break;
                    default:
                        break;
                }
                output = ui.takeUi();
            }
        } catch (IOException e) {
            System.out.println("Commands not recognized");
        }

    }

    public static void main(String[] args) {
        new Duke("data/TaskList.txt").run();
    }
}

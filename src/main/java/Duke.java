import duke.task.Task;

import java.util.ArrayList;

public class Duke {
    private TaskList taskList;
    private String filePath;
    /**
     * Create new Duke with file address for loading and saving.
     * @param filePath file address.
     * */
    private Duke(String filePath) {
        ArrayList<Task> tasks = new ArrayList<>();
        this.taskList = new TaskList(tasks);
        this.filePath = filePath;
    }

    private void run() {
        Storage storage = new Storage(filePath);
        storage.loadFile(taskList.tasks);
        assert storage.isLoad : "File not found";
        UI.askCommand();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = UI.readCommand();
                Command c = new Command(fullCommand);
                UI.splitLine();
                c.execute(taskList, storage);
                isExit = c.isExit;
            } catch (Exception e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } finally {
                UI.splitLine();
            }
        }
    }

    public static void main(String[] args) {
        UI.welcome();
        new Duke(UI.readCommand()).run();
    }
}

import duke.task.Task;

import java.util.*;

public class Duke {
    private ArrayList<Task> tasks;
    private String filePath;
    /**
     * Create new Duke with file address for loading and saving.
     * @param filePath file address.
     * */
    private Duke(String filePath){
        this.tasks = new ArrayList<>();;
        this.filePath = filePath;
    }

    private void run(){
        UI.welcome();
        Storage.loadFile(filePath, tasks);
        boolean isExit = false;
        while (!isExit) {
            try {
                String line = UI.readCommand();
                Command c = new Command(line);
                UI.split_line();
                c.execute(tasks, filePath);
                isExit = c.isExit;
            } catch (Exception e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } finally {
                UI.split_line();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("C:/Users/alienware/Desktop/TIC2002/duke/data/duke.txt").run();
    }
}

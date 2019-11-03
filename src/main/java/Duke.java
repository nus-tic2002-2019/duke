import duke.task.Task;

import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> tasks;
    private String filePath;
    /**
     * Create new Duke with file address for loading and saving.
     * @param filePath file address.
     * */
    private Duke(String filePath){
        this.tasks = new ArrayList<>();
        this.filePath = filePath;
    }

    private void run(){
        Storage storage = new Storage(filePath);
        storage.loadFile(tasks);
        if (storage.isLoad) {
            UI.askCommand();
            boolean isExit = false;
            while (!isExit) {
                try {
                    String line = UI.readCommand();
                    Command c = new Command(line);
                    UI.splitLine();
                    c.execute(tasks, storage);
                    isExit = c.isExit;
                } catch (Exception e) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } finally {
                    UI.splitLine();
                }
            }
        } else {
            new Duke(UI.readCommand()).run();
        }
    }

    public static void main(String[] args) {
        UI.welcome();
        new Duke(UI.readCommand()).run();
    }
}

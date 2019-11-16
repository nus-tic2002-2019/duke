import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private UI ui;

    public Duke(String filePath) throws IOException {
        ui = new UI();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.readFile());
    }

    public void run() {
        ui.welcomeMessage();
        while (true) {
            try {
                String fullCommand = ui.readUserInput();
                Command c = Parser.parseInput(fullCommand);
                c.execute(taskList, ui, storage);
            }
            catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) throws IOException {
        new Duke("/Users/joseph/Desktop/tic2002Duke/src/main/java/data/taskList.txt").run();
    }

} 
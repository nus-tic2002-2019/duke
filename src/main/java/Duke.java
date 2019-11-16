import java.io.IOException;

public class Duke{
    private Storage storage;
    private TaskList taskList;
    private UI ui;

    public Duke(String filepath){
        ui = new UI();
        storage = new Storage(filepath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            //ui.showToUser("OOPS!!! Problem reading file. Starting with an empty task list");
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readUserInput();
                Command c = Parser.parseInput(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit;
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/taskList.txt").run();
        //new Duke("C:\\Users\\AdminCOOP\Documents\\2019 Year 3 Sem 1\\TIC2002\\sample.txt").run();
    }
    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }
}

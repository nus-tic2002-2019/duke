import java.io.IOException;

public class Duke{
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e){
            ui.showToUser("☹ OOPS!!! Problem reading file. Starting with an empty task list");
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser.parse(fullCommand);
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit;

            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.saveNow(storage,tasks.getTaskList());
        ui.printBye();
    }

    public static void main(String[] args) throws IOException, DukeException {
        new Duke("C:\\NUS\\test.txt").run();
    }
}
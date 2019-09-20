import java.io.IOException;

public class DukeRefactor {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public DukeRefactor(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e){
            ui.showToUser("☹ OOPS!!! Problem reading file. Starting with an empty task list");
        }
    }

    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                String commandWord = Parser.parse(fullCommand);
                switch (commandWord) {
                    case "bye":
                    case "": // exit if user input is empty
                        isExit = true;
                        break;
                    case "todo":
                        tasks.addTask(Parser.createTodo(fullCommand));
                        ui.changed();
                        break;
                    case "deadline":
                        tasks.addTask(Parser.createDeadline(fullCommand));
                        ui.changed();
                        break;
                    case "event":
                        tasks.addTask(Parser.createEvent(fullCommand));
                        ui.changed();
                        break;
                    case "delete":
                        tasks.removeTask(Parser.getIndex(fullCommand));
                        ui.changed();
                        break;
                    case "list":
                        ui.showToUser(tasks.getDescription());
                        break;
                    case "done":
                        tasks.markAsDone(Parser.getIndex(fullCommand));
                        ui.changed();
                        break;
                    case "save":
                        ui.saveNow(storage,tasks.getTaskList());
                        break;
                    case "saveTo":
                        ui.setPath(storage,Parser.getPath(fullCommand));
                        ui.saveCopy(storage,tasks.getTaskList());
                        break;
                    case "loadFrom":
                        ui.setPath(storage,Parser.getPath(fullCommand));
                        try {
                            tasks = new TaskList(storage.load());
                        } catch (DukeException e) {
                            ui.showToUser("Problem reading file. Starting with an empty task list");
                        }
                    default:
                        ui.showToUser("☹ OOPS!!! Unknown command! please try again"+System.lineSeparator());
                        break;
                }
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.saveNow(storage,tasks.getTaskList());
        ui.printBye();
    }

    public static void main(String[] args) throws IOException {
        new DukeRefactor("C:\\NUS\\test.txt").run();
    }
}
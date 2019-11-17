/*
 *  Duke.java
 *  This is a simple TO-DO program named 'Duke'.
 *  Fork source: https://github.com/nus-tic2002-2019/duke
 *  Runs the 'Duke' program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /*
     *  Constructs Duke object.
     *  @param filePath Define file location.
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /*
     *  This method shows the flow of 'Duke'.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
                storage.save(tasks.getTaskList());
            } catch (DukeException e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    /*
     *  This is the main method
     *  @param args Arguments.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
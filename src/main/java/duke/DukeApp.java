package duke;


public class DukeApp {
    UI ui;
    Storage storage = new Storage();
    public DukeApp() {
        ui = new UI();
    }

    /***
     * to run the application by calling the parser, UI and storage classes
     */
    public void run() {
        ui.printWelcomeMessage();
        Parser parser = new Parser();
        String input = "";
        TaskList line = new TaskList();
        storage.load("listData.txt", line);

        //if string doesn't equal to bye
        while (!input.equals("bye")) {
            input = ui.getUserInput();
            parser.parseCommand(input, ui, line, storage);
        }
        ui.printBye();
    }

}

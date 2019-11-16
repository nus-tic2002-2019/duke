package duke;

public class Duke {
    private static Task todoList = new Task();
    private static Parser parse = new Parser();
    private static UserInterface ui = new UserInterface();
    private static FileUtils file = new FileUtils("/Users/Dax/Desktop/school/java/2019/sem1/duke/src/main/save.txt");

    private static void run() {
        String line = ui.readCommand();
        String choice = parse.getChoice(line);
        try {
            while (!choice.equals("bye")) {
                ui.showLine();
                ui.handleInput(choice, line);
                ui.showLine();
                line = ui.readCommand();
                choice = parse.getChoice(line);
            }
            ui.showLine();
            ui.goodbye();
            ui.showLine();
        } catch (DukeException error) {
            ui.showLine();
            run();
        }
    }

    public static void main(String[] args) {
        ui.showWelcome();
        ui.showLine();
        run();
    }
}

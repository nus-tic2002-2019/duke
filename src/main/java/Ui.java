import exceptions.*;
import uiParser.*;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Interaction with User Input
 */

public class Ui {
    private String userInput;
    private static ArrayList<String> taskCommands;

    public Ui(){
        userInput = "";
        taskCommands = new ArrayList<>();
        taskCommands.add("todo");
        taskCommands.add("event");
        taskCommands.add("deadline");
        taskCommands.add("done");
        taskCommands.add("delete");
        taskCommands.add("list");
        taskCommands.add("bye");
        taskCommands.add("find");
    }

    public String[] takeUi() {
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        String[] text = new Parser().parseUi(userInput);

        try {
            validateCommand(text[0]);
        } catch (InvalidAction e) {
            System.out.println(e);
        }
        return text;
    }

    public void showWelcome() {
        System.out.println("Hello, I'm Duke.\nWhat can I do for you?");

    }

    public void validateCommand(String actionType) throws InvalidAction {
        if (!taskCommands.contains(actionType)) {
            throw new InvalidAction("Unrecognized command, please enter a command listed in the instructions.");
        } else {
            return;
        }
    }

    public void validateTask(String task) throws NotNull {
        if (task.isEmpty()) {
            throw new NotNull("The description field for a task cannot be empty.");
        } else {
            return;
        }
    }
}

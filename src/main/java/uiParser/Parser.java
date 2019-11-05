package uiParser;
import java.util.ArrayList;
import exceptions.*;

public class Parser {
    private String actionType;
    private int taskNo;
    static ArrayList<String> taskCommands;

    public Parser() {
        taskCommands = new ArrayList<>();
        taskCommands.add("todo");
        taskCommands.add("event");
        taskCommands.add("deadline");
        taskCommands.add("done");
        taskCommands.add("delete");
        taskCommands.add("list");
        taskCommands.add("bye");
    }

    public String[] parseUi(String userInput) {
        String[] d = userInput.split(" ");
        String[] d1 = {};
        //action type in d[0]
        actionType = d[0].toString().toLowerCase();

        try {
            validateCommand(d[0]);
        } catch (InvalidAction e) {
            System.out.println(e);
        }

        /*try {
            validateTask(d[1]);
        } catch (NotNull e) {
            System.out.println("Not Null Exception: " + e);
        }*/

        if (actionType.equals("bye") || actionType.equals("list")) {
            return d;
        }

        //task in d[1]
        d[1] = userInput.substring(actionType.length()+1);
        for (int i=2; i < d.length; i++) {
            d[i] = null;
        }

        if (actionType.equals("event")) {
            d1 = d[1].split("/at ");
        } else if (actionType.equals("deadline")) {
            d1 = d[1].split("/by ");
        }

        //tasktime in d[2]
        if (actionType.equals("event") || actionType.equals("deadline")) {
            d[1] = d1[0];
            d[2] = d1[1];
        }

        return d;
    }

    public void validateCommand(String actionType) throws InvalidAction {
        if (!taskCommands.contains(actionType)) {
            throw new InvalidAction("Unrecognized command, I don't know what that means. =(");
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

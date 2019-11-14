package main;

import DukeCommands.*;

public class Parser {

    public Parser() {

    }
	public Command parse(String commandLine) {

        String[] nextCommand = commandLine.split(" ");

        switch (nextCommand[0]) {
		case "hello":
            return new StartCommand();
			
		case "bye":
            return new ByeCommand();
			
		case "list":
            return new ListCommand();
			
		case "todo":
            // taskHasName(nextCommand);
            String todoName = "";
            for (int i = 1; i < nextCommand.length; i++) {
                todoName += nextCommand[i] + " ";
            }
            return new TodoCommand(todoName);
			
        case "done":
            //commandHasTask(nextCommand);
            return new DoneCommand(Integer.valueOf(nextCommand[1]) - 1);
			
        case "event":
            String[] eventDetails = commandLine.split(" /at ");
            return new EventCommand(eventDetails[0], eventDetails[1]);

        case "deadline":
            String[] deadlineDetails = commandLine.split(" /by ");
            return new DeadlineCommand(deadlineDetails[0], deadlineDetails[1]);
			
		case "delete":
            // commandHasTask(nextCommand);
            int index = Integer.parseInt(nextCommand[1]);
            return new DeleteCommand(index);

		default:
            break;
        }
        return new UnknownCommand();
    }
}

package newDuke.main;

import newDuke.DukeCommands.*;
import newDuke.DukeExceptions.Exception;

public class Parser {
	
	public Parser() {
    	}

    	private void commandHasInput(String[] nextCommand) {
		assert nextCommand.length > 1 : "Command does not have an Input.";
    	}
	private void commandHasTaskNumber(String nextCommand) {
		Object obj = Integer.parseInt(nextCommand);
		assert obj instanceof Integer: "Command does not have a Task.";
    	}
	
	/**
	* Parses the input and returns a corresponding Command
	*
	* <p></p>When a String (commandLine) is supplied, this method will split commandLine into multiple words (via
	* String.split(" "). This will result in a String array containing individual words. The first word is then used
	* to determine which Command is to be returned.
	*
	* @param commandLine Entire user input in String.
	* @return Returns a corresponding Command object.
	*/
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
		String todoName = "";
		for (int i = 1; i < nextCommand.length; i++) {
			todoName += nextCommand[i] + " ";
		}
		return new TodoCommand(todoName);

	case "done":
		return new DoneCommand(nextCommand);

	case "event":
		return new EventCommand(commandLine);

	case "deadline":
		return new DeadlineCommand(commandLine);
			
	case "delete":
		commandHasInput(nextCommand);
		commandHasTaskNumber(nextCommand[1]);
		int index = Integer.parseInt(nextCommand[1]);
		return new DeleteCommand(index);

	case "find":
		commandHasInput(nextCommand);
		commandHasTaskNumber(nextCommand[1]);
		return new FindCommand(nextCommand[1]);

	case "snooze":
		commandHasInput(nextCommand);
		commandHasTaskNumber(nextCommand[1]);
		index = Integer.parseInt(nextCommand[1]);
		return new SnoozeCommand(index);

	default:
		break;
	}
		return new UnknownCommand();
	}

}

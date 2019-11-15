package main;

import DukeCommands.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
            String todoName = "";
            for (int i = 1; i < nextCommand.length; i++) {
                todoName += nextCommand[i] + " ";
            }
            return new TodoCommand(todoName);
			
        case "done":
            return new DoneCommand(Integer.valueOf(nextCommand[1]) - 1);
			
        case "event":
            String[] eventDetails = commandLine.split(" /at ");
            return new EventCommand(eventDetails[0].substring(6), eventDetails[1]);

        case "deadline":
            String[] deadlineDetails = commandLine.split(" /by ");
	    LocalDate d1 = LocalDate.parse(deadlineDetails[1]);
            return new DeadlineCommand(deadlineDetails[0].substring(9), d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
			
	case "delete":
            int index = Integer.parseInt(nextCommand[1]);
            return new DeleteCommand(index);

	default:
            break;
        }
        return new UnknownCommand();
    }
}

package parser;
import command.*;
import exception.DukeException;
import task.Originator;
import task.TaskList;

import java.util.List;

/**
 *Represents the parser class to parse correct information from users to respective commands.
 * */
public class Parser{
    /**
     *Takes input from user and splits out the important keywords to return to the correct command.
     * */
    public static Command parse(String fullCommand, List<Originator.Memento> savedStates, Originator originator, TaskList taskList, int state) throws DukeException {
        String command = fullCommand.split(" ")[0];
        switch (command) {
            case "bye":
            case "": // exit if user input is empty
                return new ByeCommand();
            case "todo":
                return new TodoCommand(fullCommand);
            case "deadline":
                return new DeadlineCommand(fullCommand);
            case "event":
                return new EventCommand(fullCommand);
            case "delete":
                return new DeleteCommand(fullCommand);
            case "list":
                return new ListCommand();
            case "done":
                return new DoneCommand(fullCommand);
            case "save":
                return new SaveCommand();
            case "saveTo":
                return new SaveToCommand(fullCommand);
            case "loadFrom":
                return new LoadFromCommand(fullCommand);
            case "find":
                return new FindCommand(fullCommand);
            case "help":
                return new HelpCommand(fullCommand);
            case "undo":
                return new UndoCommand(fullCommand,savedStates, originator,taskList, state);
            default:
                throw new DukeException("☹ OOPS!!! Unknown command! please try again or you may try typing 'help' to see list of commands." + System.lineSeparator());
        }
    }
}
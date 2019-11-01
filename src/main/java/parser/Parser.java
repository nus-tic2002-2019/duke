package parser;
import command.*;
import exception.DukeException;
import task.Originator;
import task.Task;

import java.util.List;

/**
 *Represents the parser class to parse correct information from users to respective commands.
 * */
public class Parser{
    /**
     *Takes input from user and splits out the important keywords to return to the correct command.
     * */
    public static Command parse(String fullCommand, List<Originator.Memento> savedStates) throws DukeException {
        String command = fullCommand.split(" ")[0];
        Originator originator = new Originator();
        int state = 0;
        switch (command) {
            case "bye":
            case "": // exit if user input is empty
                return new ByeCommand();
            case "todo":
                originator.set(state + 1);
                savedStates.add(originator.saveToMemento());
                return new TodoCommand(fullCommand);
            case "deadline":
                originator.set(state + 1);
                savedStates.add(originator.saveToMemento());
                return new DeadlineCommand(fullCommand);
            case "event":
                originator.set(state + 1);
                savedStates.add(originator.saveToMemento());
                return new EventCommand(fullCommand);
            case "delete":
                originator.set(state + 1);
                savedStates.add(originator.saveToMemento());
                return new DeleteCommand(fullCommand);
            case "list":
                return new ListCommand();
            case "done":
                originator.set(state + 1);
                savedStates.add(originator.saveToMemento());
                return new DoneCommand(fullCommand);
            case "save":
                originator.set(state + 1);
                savedStates.add(originator.saveToMemento());
                return new SaveCommand();
            case "saveTo":
                originator.set(state + 1);
                savedStates.add(originator.saveToMemento());
                return new SaveToCommand(fullCommand);
            case "loadFrom":
                originator.set(state + 1);
                savedStates.add(originator.saveToMemento());
                return new LoadFromCommand(fullCommand);
            case "find":
                return new FindCommand(fullCommand);
            case "help":
                return new HelpCommand(fullCommand);
            case "undo":
                return new UndoCommand(fullCommand,savedStates, originator);
            default:
                throw new DukeException("☹ OOPS!!! Unknown command! please try again or you may try typing 'help' to see list of commands." + System.lineSeparator());
        }
    }
}

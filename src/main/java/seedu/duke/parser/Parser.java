package seedu.duke.parser;

import seedu.duke.command.AddDeadlineCommand;
import seedu.duke.command.AddEventCommand;
import seedu.duke.command.AddToDoCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.data.exception.DukeException;

public class Parser{
    public static Command parseInput(String userInput) throws DukeException{
        String input = userInput;

        switch(input.split(" ")[0]){
            case AddDeadlineCommand.INPUT_WORD:
                return new AddDeadlineCommand(false, input);
            case AddEventCommand.INPUT_WORD:
                return new AddEventCommand(false, input);
            case AddToDoCommand.INPUT_WORD:
                return new AddToDoCommand(false, input);
            case ListCommand.INPUT_WORD:
                return new ListCommand(false, "");
            case DeleteCommand.INPUT_WORD:
                return new DeleteCommand(false, input);
            case DoneCommand.INPUT_WORD:
                return new DoneCommand(false, input);
            case ByeCommand.INPUT_WORD:
                return new ByeCommand(true, "");
            case FindCommand.INPUT_WORD:
                return new FindCommand(false, input);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");   
        }
    }
}
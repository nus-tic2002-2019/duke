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
import seedu.duke.command.ScheduleCommand;
import seedu.duke.exception.DukeException;

    /**
     * Parse the input of the user and runs the corresponding command.
     */

public class Parser{
    
    /** 
     * Parse the input of the user and returns a command based on the input.
     * @param userInput         The input given by the user.
     * @return Command          The command with reference to the given input.
     * @throws DukeException    When an invalid command is given.
     */
    public static Command parseInput(String userInput) throws DukeException{

        switch(userInput.split(" ")[0]){
            case AddDeadlineCommand.INPUT_WORD:
                return new AddDeadlineCommand(false, userInput);
            case AddEventCommand.INPUT_WORD:
                return new AddEventCommand(false, userInput);
            case AddToDoCommand.INPUT_WORD:
                return new AddToDoCommand(false, userInput);
            case ListCommand.INPUT_WORD:
                return new ListCommand(false, "");
            case DeleteCommand.INPUT_WORD:
                return new DeleteCommand(false, userInput);
            case DoneCommand.INPUT_WORD:
                return new DoneCommand(false, userInput);
            case ByeCommand.INPUT_WORD:
                return new ByeCommand(true, "");
            case FindCommand.INPUT_WORD:
                return new FindCommand(false, userInput);
            case ScheduleCommand.INPUT_WORD:
                return new ScheduleCommand(false, userInput);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");   
        }
    }
}
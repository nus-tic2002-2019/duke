package duke.command;

import duke.DukeException;

import java.text.ParseException;
import java.util.List;
import java.io.IOException;

public interface Command {

    /**
     * Returns the message to user after the command is run.
     *
     * @param fullCommand array of command from the user input.
     * @return message to the user.
     */
    List<String> run(String[] fullCommand) throws DukeException, IOException, ParseException;

    /**
     * Return false when execute this command.
     *
     * @return false by default, the program should not exit when run this command.
     */
    default boolean isExit() {
        return false;
    }
}

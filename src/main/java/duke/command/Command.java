package duke.command;

import duke.DukeException;
import java.util.List;
import java.io.IOException;

public interface Command {
    /**
     * Returns the message to user after the command is run.
     * @param fullCommand array of command from the user input.
     * @return message to the user.
     * @throws DukeException
     * @throws IOException
     */

    List<String> run(String[] fullCommand) throws DukeException, IOException;

    /**
     * Return false when execute this command.
     *
     * @return false by default, the program should not exit when run this command.
     */
    default boolean isExit(){
        return false;
    }
}

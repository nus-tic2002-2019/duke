package duke.command;

import duke.DukeException;
import java.util.List;
import java.io.IOException;

public interface Command {

    List<String> run(String[] fullCommand) throws DukeException, IOException;

    default boolean isExit(){
        return false;
    }
}

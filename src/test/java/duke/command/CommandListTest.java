package duke.command;

import duke.error_handling.DukeException;
import duke.storage.Storage;
import duke.storage.TempTaskList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CommandListTest {

    String test = "todo This is a sentence";
    String[] expect = {"todo", "This is a sentence"};
    @Test
    void splitKeywordTest() throws IOException, DukeException {
        assertEquals(expect, new CommandList(new TempTaskList(), new Storage()).splitKeyword(test));
    }
}
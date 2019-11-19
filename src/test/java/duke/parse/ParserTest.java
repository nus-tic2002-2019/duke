package duke.parse;

import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    Task test = new Todo("to do a new one");

    @Test
    void taskToText() {
        assertEquals("T | 0 | to do a new one", new Parser().taskToText(test));
    }

}


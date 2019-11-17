package tasklist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void testToString() {
        Task t = new ToDo("Duke");
        assertEquals("[T][" + t.getStatusIcon() + "] Duke", t.toString());
    }

    @Test
    void saveFormat() {
        Task t = new ToDo("Duke");
        assertEquals("T | 0 | Duke", t.saveFormat());
    }

    @ParameterizedTest
    @CsvSource({
            "tasks",
            "todos",
            "deadlines",
            "events"
    })
    void testTaskType(ArgumentsAccessor arguments) throws ParseException {
        Task t = new ToDo("Bills");
        if (arguments.getString(0).equals("todos")){
            assertTrue(t.taskType(arguments.getString(0)));
        }else {
            assertFalse(t.taskType(arguments.getString(0)));
        }

    }
}
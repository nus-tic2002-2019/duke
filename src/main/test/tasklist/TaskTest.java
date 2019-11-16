package tasklist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {


    @Test
    void testToString() {
        Task t = new Task("Duke");
        assertEquals("["+ t.getStatusIcon() + "] Duke", t.toString());
    }

    @Test
    void saveFormat() {
        Task t = new Task("Duke");
        assertEquals("| 0 | Duke", t.saveFormat());
    }

    @ParameterizedTest
    @CsvSource({
            "tasks",
            "todos",
            "deadlines",
            "events"
    })
    void testTaskType(ArgumentsAccessor arguments) throws ParseException {
        Task t = new Task("Bills");
        if (arguments.getString(0).equals("tasks")){
            assertTrue(t.taskType(arguments.getString(0)));
        }else {
            assertFalse(t.taskType(arguments.getString(0)));
        }

    }


}
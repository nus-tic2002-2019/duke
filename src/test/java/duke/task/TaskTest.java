package duke.task;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {
    Task task = new Task("read book");

    @Test
    void getStatusIconIncomplete() {
        assertEquals("✘", task.getStatusIcon());
    }

    @Test
    void getStatusIconComplete() {
        task.markAsDone();
        assertEquals("✓", task.getStatusIcon());
    }

    @Test
    void getListIncomplete() {
        assertEquals(List.of("0", "read book") , task.getList());
    }

    @Test
    void getListComplete() {
        task.markAsDone();
        assertEquals(List.of("1", "read book") , task.getList());
    }

    @Test
    void testToStringComplete() {
        assertEquals("[" + "✘" + "]" + "read book", task.toString());
    }

    @Test
    void testToStringIncomplete() {
        task.markAsDone();
        assertEquals("[" + "✓" + "]" + "read book", task.toString());
    }
}
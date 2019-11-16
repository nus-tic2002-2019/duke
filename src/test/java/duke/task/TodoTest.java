package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    Todo todo = new Todo("join sports club");

    @Test
    void getStatusIconTest() {
        assertEquals("\u2718", todo.getStatusIcon());
    }

    @Test
    void toStringTest() {
        assertEquals("[T][\u2718] join sports club", todo.toString());
    }

    @Test
    void save_toStringTest()  {
        assertEquals("T | \u2718 | join sports club", todo.save_toString());
    }
}


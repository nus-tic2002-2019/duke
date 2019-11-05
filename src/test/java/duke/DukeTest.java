package duke;

import org.junit.jupiter.api.Test;
import subclass.*;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }
    @Test
    public void testTodo() throws todoException {
        Todo todo = new Todo("abc");
        assertEquals("[T][\u2718] abc", todo.toString());
        todo.setDone(true);
        assertEquals("[T][\u2713] abc", todo.toString());
    }

    @Test
    public void testDeadline() throws ParseException {
        Deadline deadline = new Deadline("task abc", "31-Dec-19 07:30 PM");
        assertEquals("[D][\u2718] task abc (by: 31-Dec-19 07:30 PM)", deadline.toString());
        deadline.setDone(true);
        assertEquals("[D][\u2713] task abc (by: 31-Dec-19 07:30 PM)", deadline.toString());
    }

    @Test
    public void testEvent() throws ParseException {
        Event event = new Event ("task abc", "31-Dec-19 07:30 PM");
        assertEquals("[E][\u2718] task abc (at: 31-Dec-19 07:30 PM)", event.toString());
        event.setDone(true);
        assertEquals("[E][\u2713] task abc (at: 31-Dec-19 07:30 PM)", event.toString());
    }

}

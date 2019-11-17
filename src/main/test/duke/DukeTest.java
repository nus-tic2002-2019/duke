package duke;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DukeTest {
    @Test
    public void getChoice(){
        assertEquals("event", Parser.getChoice("event"));
    }

    @Test
    public void getBody() throws DukeException {
        assertEquals("eat breakfast", Parser.getBody("todo eat breakfast"));
    }

    @Test
    public void formatDateTime() throws DukeException {
        assertEquals("Sep 9 1992, Wed", Parser.formatDate("09/09/1992"));
        assertEquals("0800 AM", Parser.formatTime("0800"));
    }

    @Test
    public void getTodo() {
        Todo todo = new Todo("eat breakfast");
        assertEquals(false, todo.getDone());
        todo.setDone(true);
        assertEquals(true, todo.getDone());
        assertEquals("eat breakfast", todo.getTodo());
        assertEquals("T", todo.getType());
    }
}
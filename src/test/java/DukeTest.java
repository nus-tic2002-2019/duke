import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void checkTodo() {
        Todo todo = new Todo("borrow book");
        assertEquals(" [✘][T] borrow book", todo.toString());
    }

    @Test
    public void checkDeadline() {
        Deadline deadline = new Deadline("return book", "Dec 2 2019, 06:30:00PM");
        assertEquals(" [✘][D] return book (by: Dec 2 2019, 06:30:00PM)", deadline.toString());
    }

    @Test
    public void checkEvent() {
        Event event = new Event("project meeting", "Dec 2 2019, 09:30:00AM - 06:30:00PM");
        assertEquals(" [✘][E] project meeting (on: Dec 2 2019, 09:30:00AM - 06:30:00PM)", event.toString());
    }
}
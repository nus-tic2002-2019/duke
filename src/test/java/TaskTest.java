import org.junit.jupiter.api.Test;
import tasks.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    public static Todo t;
    public static Deadline d;
    public static Event e;

    public TaskTest() {
        t = new Todo("return book");
        d = new Deadline("Duke project", LocalDate.parse("2019-11-17"));
        e = new Event("Drone Training", LocalDate.parse("2019-11-24"), LocalTime.parse("18:00"));
    }

    @Test
    public void getTaskTest(){
        assertEquals("return book", t.getTask());
        assertEquals("Duke project", d.getTask());
        assertEquals("Drone Training", e.getTask());
    }

    public void doneStatusTest(){
        assertEquals(false, t.getDone());
        d.isDone();
        assertEquals(true, d.getDone());
        assertEquals(false, e.getDone());
    }
}

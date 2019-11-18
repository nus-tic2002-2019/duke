package tic2002.task;

import java.time.LocalDateTime;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EventTest {
    @Test
    public void constructorTest() {
        Event e1 = new Event("test", "test");
        assertEquals("[E][✘] test (at: test)", e1.toString() );
        assertEquals("E|0|0|test|test", e1.printToFile() );

        Event e2 = new Event("test", LocalDateTime.parse("2019-11-19T05:00"));
        assertEquals("[E][✘] test (at: 19 November 2019, 5:00 AM)", e2.toString() );
        assertEquals("E|0|1|test|2019-11-19 0500", e2.printToFile() );
    }
}

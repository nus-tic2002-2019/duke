package tic2002.task;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void constructorTest() {
        Deadline d1 = new Deadline("test", "test");
        assertEquals("[D][✘] test (by: test)", d1.toString() );
        assertEquals("D|0|0|test|test", d1.printToFile() );

        Deadline d2 = new Deadline("test", LocalDateTime.parse("2019-11-19T05:00"));
        assertEquals("[D][✘] test (by: 19 November 2019, 5:00 AM)", d2.toString() );
        assertEquals("D|0|1|test|2019-11-19 0500", d2.printToFile() );
    }
}

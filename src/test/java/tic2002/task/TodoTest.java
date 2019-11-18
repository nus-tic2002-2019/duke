package tic2002.task;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TodoTest {
    @Test
    public void constructorTest() {
        Todo t = new Todo("test");
        assertEquals("[T][✘] test", t.toString() );
        assertEquals("T|0|0|test", t.printToFile() );
    }
}
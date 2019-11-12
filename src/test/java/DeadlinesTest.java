import org.junit.jupiter.api.Test;
import task.Deadlines;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    @Test
    public void deadlinesTest() {
        Deadlines list = new Deadlines("deadline ThisTest", "2017-09-11 1100", false);
        assertEquals("(by: " + "11 Sep 2017 1100" + ")", list.getDetails());
    }
}

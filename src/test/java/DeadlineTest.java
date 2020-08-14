import org.junit.jupiter.api.Test;
import tasks.Deadline;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    public static Deadline d;

    public DeadlineTest() {
        d = new Deadline("Duke Project", LocalDate.parse("2019-11-17"));
    }

    @Test
    public void testDateFormat(){
        //Event e = new Event("Drone Training", LocalDate.parse("2019-11-24"), LocalTime.parse("09:00"));
        assertEquals("Nov 17 2019", d.getDate());
    }
}

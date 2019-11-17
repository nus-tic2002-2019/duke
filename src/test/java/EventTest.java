import org.junit.jupiter.api.Test;
import tasks.Event;
import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    public static Event e;

    public EventTest() {
        e = new Event("Drone Training", LocalDate.parse("2019-11-24"), LocalTime.parse("18:00"));
    }

    @Test
    public void testDateFormat(){
        //Event e = new Event("Drone Training", LocalDate.parse("2019-11-24"), LocalTime.parse("09:00"));
        assertEquals("Nov 24 2019", e.getDate());
    }

    @Test
    public void testTimeFormat(){
        //Event e = new Event("Drone Training", LocalDate.parse("2019-11-24"), LocalTime.parse("18:30"));
        assertEquals("6:30PM", e.getTime());
    }
}

package Tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {
    @Test
    public void EventTest1() {

        LocalDate d1 = LocalDate.parse("2019-12-12");
        LocalTime t1 = LocalTime.parse("10:00");
        Events e1 = new Events("Learn Java", d1);
        Events e2 = new Events("Finals", d1, t1);

        assertEquals("[E] [✘] Learn Java (at: Dec 12 2019)", e1.toString());
        assertEquals("[E] [✘] Finals (at: Dec 12 2019 10:00 AM)", e2.toString());
        assertEquals("Dec 12 2019",e1.getDateTimeStringFormat());
        assertEquals("Dec 12 2019 10:00 AM",e2.getDateTimeStringFormat());
        assertEquals("2019-12-12",e1.getDateTimeString());
        assertEquals("2019-12-12 10:00",e2.getDateTimeString());

    }
}

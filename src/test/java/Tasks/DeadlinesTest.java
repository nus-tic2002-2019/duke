package Tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    @Test
    public void DeadlinesTest1() {

        LocalDate d1 = LocalDate.parse("2019-12-12");
        LocalTime t1 = LocalTime.parse("10:00");
        Deadlines dead1 = new Deadlines("Phone bills", d1);
        Deadlines dead2 = new Deadlines("Duke", d1, t1);

        assertEquals("[D] [✘] Phone bills (at: Dec 12 2019)", dead1.toString());
        assertEquals("[D] [✘] Duke (at: Dec 12 2019 10:00 AM)", dead2.toString());
        assertEquals("Dec 12 2019", dead1.getDateTimeStringFormat());
        assertEquals("Dec 12 2019 10:00 AM", dead2.getDateTimeStringFormat());
        assertEquals("2019-12-12", dead1.getDateTimeString());
        assertEquals("2019-12-12 10:00", dead2.getDateTimeString());

    }
}

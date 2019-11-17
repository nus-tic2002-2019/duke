import date.time.management.DateTime;
import org.junit.jupiter.api.Test;
import taskclasses.Deadline;
import taskclasses.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskRegistrationTest {
    @Test
    public void TodoTaskRegistration(){
        String desciption = "wake up";
        Todo morning = new Todo(desciption);

        assertEquals("wake up", morning.getDescription());
    }

    @Test
    public void DeadlineTesting(){
        String desciption = "wake up";
        String date_D = "2019-12-11";
        String time_D = "15:00";

        DateTime timing = new DateTime(date_D, time_D);
        Deadline WakeUp = new Deadline(desciption, timing);

        assertEquals("2019-12-11T15:00:00", WakeUp.getDeadline_DateTime_String());
    }
}

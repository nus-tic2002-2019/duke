package exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ErrTypeTest {
    @Test
    void toInteger(){
        assertEquals(1,ErrType.toInteger("1",5));
        assertEquals(5,ErrType.toInteger("5",5));
        assertEquals(-1,ErrType.toInteger("6",5));
    }

    @Test
    void isTaskNumberValid() throws DukeException {
        assertEquals(true,ErrType.isTaskNumberValid(4,5));
        assertThrows(DukeException.class,
                     ()->{ErrType.isTaskNumberValid(6,5);});
    }

    @Test
    void isTask(){
        String correct_input = "deadline submit report /by 2019-10-10 2359";
        String wrong_input = "deadline /by 2019-10-10 2359";
        assertEquals(true,ErrType.isTask(correct_input));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                     ()->{String user_task = wrong_input.split("/")[0].split(" ")[1];});
    }

    @Test
    void isSchedule(){
        String correct_input = "deadline submit report /2019-10-10 2359";
        String wrong_input = "deadline submit report ";
        assertEquals(true, ErrType.isSchedule(correct_input));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                     ()->{String user_schedule = wrong_input.split("/")[1];});
    }

    @Test
    void isTime(){
        String correct1 = "2019-01-01 2359";
        String correct2 = "2019-01-01 abcd";
        String scheduleString = "2019-01-01";
        assertEquals(true,ErrType.isTime(correct1));
        assertEquals(true,ErrType.isTime(correct2));
        assertThrows(ArrayIndexOutOfBoundsException.class,
                     ()->{String user_time = scheduleString.split(" ")[1];});
    }
}

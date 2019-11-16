package tasklist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testToString() {
        Task t = new Deadline("Duke", "12 oct 2019");
        assertEquals("[D][" + t.getStatusIcon() + "] Duke (by: 12 oct 2019)", t.toString());
    }

    @Test
    void testSaveFormat() {
        Task t = new Deadline("Duke", "12 oct 2019");
        assertEquals("D | 0 | Duke | 12 oct 2019", t.saveFormat());
    }


    @ParameterizedTest
    @CsvSource({
            "Duke, 12 oct 2019",
            "Duke, Date not specified"
    })
    void testFindDate(ArgumentsAccessor arguments) throws ParseException {
        Task t = new Deadline(arguments.getString(0),arguments.getString(1));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date date = dateFormat.parse("12 oct 2019");

        if (((Deadline) t).time.equals("Date not specified")) {
            assertFalse(t.findDate(date, "deadline"));
        }else {
            assertTrue(t.findDate(date, "deadline"));
        }

    }


    @ParameterizedTest
    @CsvSource({
            "Duke, 13 oct 2019",
            "Duke, Date not specified"
    })
    void testFindFromDateRange(ArgumentsAccessor arguments) throws ParseException {
        Task t = new Deadline(arguments.getString(0),arguments.getString(1));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date date = dateFormat.parse("12 oct 2019");

        if (((Deadline) t).time.equals("Date not specified")) {
            assertFalse(t.findFromDateRange(date, "deadline"));
        }else {
            assertTrue(t.findFromDateRange(date, "deadline"));
        }

    }

    @ParameterizedTest
    @CsvSource({
            "Duke, 14 oct 2019",
            "Duke, Date not specified"
    })
    void testFindBetweenDateRange(ArgumentsAccessor arguments) throws ParseException {
        Task t = new Deadline(arguments.getString(0), arguments.getString(1));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date fromDate = dateFormat.parse("11 oct 2019");
        Date endDate = dateFormat.parse("15 oct 2019");

        if (((Deadline) t).time.equals("Date not specified")) {
            assertFalse(t.findBetweenDateRange(fromDate, endDate, "deadline"));
        } else {
            assertTrue(t.findBetweenDateRange(fromDate, endDate, "deadline"));
        }
    }

        @ParameterizedTest
        @CsvSource({
                "tasks",
                "todos",
                "deadlines",
                "events"
        })
        void testTaskType(ArgumentsAccessor arguments) throws ParseException {
            Task t = new Deadline("Duke", "12 oct 2019");
            if (arguments.getString(0).equals("deadlines")){
                assertTrue(t.taskType(arguments.getString(0)));
            }else {
                assertFalse(t.taskType(arguments.getString(0)));
            }


    }


}
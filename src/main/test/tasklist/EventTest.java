package tasklist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testToString() {
        Task t = new Event("Makeup Class", "12 oct 2019");
        assertEquals("[E][" + t.getStatusIcon() + "] Makeup Class (at: 12 oct 2019)", t.toString());
    }

    @Test
    void saveFormat() {
        Task t = new Event("Makeup Class", "12 oct 2019");
        assertEquals("E | 0 | Makeup Class | 12 oct 2019", t.saveFormat());
    }

    @ParameterizedTest
    @CsvSource({
            "Class gathering, 12 oct 2019",
            "Class gathering, Date not specified"
    })
    void testFindDate(ArgumentsAccessor arguments) throws ParseException {
        Task t = new Event(arguments.getString(0), arguments.getString(1));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date date = dateFormat.parse("12 oct 2019");

        if (((Event) t).time.equals("Date not specified")) {
            assertFalse(t.findDate(date, "event"));
        } else {
            assertTrue(t.findDate(date, "event"));
        }

    }


    @ParameterizedTest
    @CsvSource({
            "Class gathering, 13 oct 2019",
            "Class gathering, Date not specified"
    })
    void testFindFromDateRange(ArgumentsAccessor arguments) throws ParseException {
        Task t = new Event(arguments.getString(0), arguments.getString(1));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date date = dateFormat.parse("12 oct 2019");

        if (((Event) t).time.equals("Date not specified")) {
            assertFalse(t.findFromDateRange(date, "event"));
        } else {
            assertTrue(t.findFromDateRange(date, "event"));
        }

    }

    @ParameterizedTest
    @CsvSource({
            "Class gathering, 14 oct 2019",
            "Class gathering, Date not specified"
    })
    void testFindBetweenDateRange(ArgumentsAccessor arguments) throws ParseException {
        Task t = new Event(arguments.getString(0), arguments.getString(1));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        Date fromDate = dateFormat.parse("11 oct 2019");
        Date endDate = dateFormat.parse("15 oct 2019");

        if (((Event) t).time.equals("Date not specified")) {
            assertFalse(t.findBetweenDateRange(fromDate, endDate, "event"));
        } else {
            assertTrue(t.findBetweenDateRange(fromDate, endDate, "event"));
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
        Task t = new Event("Class gathering", "12 oct 2019");
        if (arguments.getString(0).equals("events")) {
            assertTrue(t.taskType(arguments.getString(0)));
        } else {
            assertFalse(t.taskType(arguments.getString(0)));
        }
    }
}
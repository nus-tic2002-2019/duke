package Parser;

import Exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void ParserDateTest1() throws DukeException {
        LocalDate d1 = Parser.convertStringToDate("2020-12-12");
        LocalDate d2 = Parser.convertStringToDate("2019-12-12");

        assertEquals("2020-12-12", d1.toString());
        assertEquals("2019-12-12", d2.toString());
    }

    @Test
    public void ParserTimeTest1() throws DukeException {
        LocalTime t1 = Parser.convertStringToTime("1000");
        LocalTime t2 = Parser.convertStringToTime("10:00");

        assertEquals("10:00", t1.toString());
        assertEquals("10:00", t2.toString());
        assertEquals("10:00 AM", t2.format(DateTimeFormatter.ofPattern("hh:mm a")));
    }
}

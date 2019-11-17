import org.junit.jupiter.api.Test;
import uiParser.Parser;

import java.text.DateFormatSymbols;
import java.time.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private static String strTodo;
    private static String strDeadline;
    private static String strEvent;
    private static Parser p;
    private static String[] temp;

    public ParserTest() {
        strTodo = "todo finish up duke project";
        strDeadline = "DEADLINE return library books /by 2019-11-21";
        strEvent = "EvEnt volunteer dinner /at 2019-11-23 18:00";
        p = new Parser();
    }

    @Test
    public void parseUiTest() {
        temp = p.parseUi(strTodo);
        assertEquals(("todo - finish up duke project"), (temp[0] + " - " + temp[1]));

        temp = p.parseUi(strDeadline);
        assertEquals(("deadline - return library books , 2019-11-21"), (temp[0] + " - " + temp[1] + ", " + temp[2]));

        temp = p.parseUi(strEvent);
        assertEquals(("event - volunteer dinner , 2019-11-23 18:00"), (temp[0] + " - " + temp[1] + ", " + temp[2]));
    }

    @Test
    public void parseDateTimeTest(){
        temp = p.parseUi(strEvent);
        LocalDate date1 = new Parser().parseDate(temp[2]);
        assertEquals("2019-11-23", date1.toString());

        LocalTime time1 = new Parser().parseTime(temp[2]);
        assertEquals("18:00", time1.toString());
    }
}
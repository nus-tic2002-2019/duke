package DukeTest.Parser;

import Parser.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseTest {
    /**Test convertDateTime in class Parse**/
    @Test
    public void testConvertDateTime() throws Throwable {

        Parser testing = new Parser();

        String dateToConvert = "31/12/2019 23:59";
        String output = testing.convertDateTime(dateToConvert);
        assertEquals("31 Dec 2019 11:59 PM", output);
    }

    @Test
    public void testParseTodo() throws Throwable {
        Parser testing = new Parser();

        String tryWrongCommand = "todotask 1";
        String output = testing.parseTodo(tryWrongCommand);
        assertEquals("task 1", output);
    }
}


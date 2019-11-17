package parser;

import duke.Duke;
import exception.DukeException;
import org.junit.jupiter.api.Test;
import util.Util;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    void parseHelpTest()
    {
        try{
            assertEquals("Event", Parser.parseHelp("Help Event"));

        } catch (DukeException e)
        {
            System.out.println((e.toString()));
        }
    }
}

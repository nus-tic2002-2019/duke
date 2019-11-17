package util;

//import duke.Duke;

import exception.DukeException;
import util.Util;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilTest {
    @Test
    void convertDateTimeTest()
    {
        try {
            assertEquals("May 25 2019  23:59PM",Util.convertDateTime("25/05/2019 2359").format(DateTimeFormatter.ofPattern("MMM dd yyyy  HH:mma")));
        }
        catch (DukeException e)
        {
            System.out.println("Exception found in converting Date Time");
        }

    }
}

package parser;

import exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDateTime;


public class ParseTest {
    @Test
    void dateParser(){
        LocalDateTime expected = LocalDateTime.parse("2019-11-01T23:59");
        //LocalDateTime expectedFlag = LocalDateTime.parse("1900-01-01T00:00");
        //assertEquals(expected,Parse.dateParser("2019-10-10 2359"));
        //assertEquals(expected,Parse.dateParser("10-10-2019 2359"));
        //assertEquals(expectedFlag,Parse.dateParser("2019-10-10 abcd"));
        //assertEquals(expectedFlag,Parse.dateParser("9999-99-99 abcd"));
        //assertEquals(expectedFlag,Parse.dateParser("2019/10/10 2359"));
    }


    //@Test
    //void parserTest() {
    //    assertThrows(DukeException.class, () -> parser.parse("buy cake"));
    //    }

}
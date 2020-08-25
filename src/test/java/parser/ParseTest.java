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
        LocalDateTime expectedFlag = LocalDateTime.parse("1900-01-01T00:00");

    }


}
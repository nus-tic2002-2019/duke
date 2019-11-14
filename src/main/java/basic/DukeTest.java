package basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

class DukeTest {

    @Test
    void testMain() throws FileNotFoundException, UnsupportedEncodingException {
        Duke duke = new Duke();
        Assertions.assertEquals(duke, duke);
    }

}
package basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DukeTest {

    @Test
    void testMain() {
        Duke duke = new Duke();
        Assertions.assertEquals(duke, duke);
    }

}
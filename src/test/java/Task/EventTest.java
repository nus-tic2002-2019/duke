package Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void eventTest(){
        Event event = new Event ("meeting", "2019-10-10");

        assertEquals("[E]meeting(at:Oct 10 2019)", event.toString());
    }


}

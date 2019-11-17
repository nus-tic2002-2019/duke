import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void taskTypeTest(){
        assertEquals('E',new Event("Wedding","2019-10-17 06:30").getTaskType());
    }

    @Test
    public void eventDateTimeTest(){
        assertEquals("2019-10-17 06:30",new Event("Wedding","2019-10-17 06:30").getLast());
    }
}

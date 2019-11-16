import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void taskTypeTest(){
        assertEquals('D',new Deadline("Project","2019-10-20 08:40").getTaskType());
    }

    @Test
    public void eventDateTimeTest(){
        assertEquals("2019-10-20 08:40",new Event("Project","2019-10-20 08:40").getLast());
    }
}

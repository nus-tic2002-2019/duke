package Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void deadlineTest(){
        Deadline dealine = new Deadline("duke project","2019-11-17");
        assertEquals("[D]duke project(by:Nov 17 2019)",dealine.toString());
    }
}
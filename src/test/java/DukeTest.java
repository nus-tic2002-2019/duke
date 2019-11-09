import org.junit.jupiter.api.Test;
import task.Deadlines;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void dummyTest(){
        Deadlines list = new Deadlines("deadline ThisTest", "/by 2017-09-11 1100",false);
        assertEquals("(by: " + "11 Sept 2018 1100" + ")", list.getDetails() );

    }
}
//    @Test
//    public void testAddDoAfterTask(){
////        assertEquals("(by: " + "11 Sept 2018 1100" + ")", new Deadlines("deadline ThisTest", "/by 2017-09-11 1100",false).getDetails() );
//    }

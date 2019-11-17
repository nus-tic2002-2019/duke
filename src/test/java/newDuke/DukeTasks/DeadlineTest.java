package newDuke.DukeTasks; 
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testDeadlineAddedToTaskList() {
        Deadline deadline = new Deadline("Description", "2019-12-31");
    }
	
	@Test
    public void testToString() {
        Task t = new Deadline("End of the Year", "2019-12-31");
        assertEquals("[D][X] End of the Year (by: Dec 31 2019)", t.toString());
    }

    @Test
    public void testSaveFormat() {
        Task t = new Deadline("TIC2002 Duke Project Deadline", "2019-12-31");
        assertEquals("D | 0 | submit TIC2002 Duke Project | Nov 17 2019", t.toSave());
    }
}

package newDuke.DukeTasks; 
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testEventAddedToTaskList() {
        Event event = new Deadline("Description", "home");
    }
	
	@Test
    public void testToString() {
        Task t = new Event("Attend New Year's Countdown Party", "Siloso, Sentosa");
        assertEquals("[E][X] Attend New Year's Countdown Party (at: Siloso, Sentosa)", t.toString());
    }

    @Test
    public void testSaveFormat() {
        Task t = new Event("Study for TIC2002", "NUS");
        assertEquals("E | 0 | Study for TIC2002 | NUS", t.toSave());
    }
}

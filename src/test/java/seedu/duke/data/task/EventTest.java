package seedu.duke.data.task;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    @DisplayName("Inserting Event for Final Examiniations at 4/12/2019 1300H")
    public void TestToString() {
        LocalDateTime dateTime = LocalDateTime.of(2019, 12, 04, 13, 00);
        Event event = new Event("Final Examiniations", dateTime);
        assertEquals("[E][\u2718] Final Examiniations (at: 4/12/2019 1300)", event.toString());
        
        event.setDone();
        assertEquals("[E][\u2713] Final Examiniations (at: 4/12/2019 1300)", event.toString());
    }
}
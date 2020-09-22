package task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    @Test
    @DisplayName("Inserting Event for Test Case 1 /by 2019-01-05 00:30")
    void TestToString() {
        String dateTime = "2019-01-05 00:30";
        Event event = new Event("To Complete Test Case 1", dateTime);
        assertEquals("[E][✘]To Complete Test Case 1 (at: 5 Jan 2019, 0030)", event.toString());

    }
}
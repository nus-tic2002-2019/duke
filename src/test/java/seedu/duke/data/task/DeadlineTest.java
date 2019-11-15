package seedu.duke.data.task;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeadlineTest{
    @Test
    @DisplayName("Inserting Deadline for Test Case 1 by 12/08/2019 0000H")
    public void TestToString() {
        LocalDateTime dateTime = LocalDateTime.of(2019, 8, 12, 00, 00);
        Deadline deadline = new Deadline("To Complete Test Case 1", dateTime);
        assertEquals("[D][\u2718] To Complete Test Case 1 (by: 12/08/2019 0000)", deadline.toString());

        deadline.setDone();
        assertEquals("[D][\u2713] To Complete Test Case 1 (by: 12/08/2019 0000)", deadline.toString());
    }
}
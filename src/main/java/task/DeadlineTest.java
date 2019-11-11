package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeadlineTest {
    @Test
    @DisplayName("Inserting Deadline for Test Case 1 /by 2019-01-05 00:30")
    void TestToString() {
        String dateTime = "2019-01-05 00:30";
        Deadline deadline = new Deadline("To Complete Test Case 1", dateTime);
        Assertions.assertEquals("[D][✘]To Complete Test Case 1 (by: 5 Jan 2019, 0030)", deadline.toString());
    }

}
package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestDeadline{
    @Test
    void testToString() {
        String dateTime = "2019-01-05 00:30";
        Deadline deadline = new Deadline("To Complete Test Case 1", dateTime);
        Assertions.assertEquals("[D][\u2718]To Complete Test Case 1 (by: 5 Jan 2019, 0030)", deadline.toString());

    }
}
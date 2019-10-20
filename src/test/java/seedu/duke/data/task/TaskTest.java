package seedu.duke.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskTest{
    @Test
    @DisplayName("Verifying the task functions.")
    public void TestToString() {
        Task task = new Task("Complete Assignments.");
        assertEquals("[\u2718] Complete Assignments.", task.toString());

        task.setDone();
        assertEquals("[\u2713] Complete Assignments.", task.toString());
    }
}
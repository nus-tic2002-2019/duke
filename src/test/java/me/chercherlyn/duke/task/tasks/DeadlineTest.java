package me.chercherlyn.duke.task.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import me.chercherlyn.duke.task.tasks.Deadline;

public class DeadlineTest {
    @Test
    public void constructor_strTimeIn_validOut() {
        Deadline deadline = new Deadline("Description", "12.01.1987 0230");
    }
    
    @Test
    public void constructor_millisTimeIn_validOut() {
        // 537417000000 millis = 12.01.1987 02:30 am (GMT+3)
        Deadline deadline = new Deadline("Description", 537409800000L);
    }
    
    @Test
    public void getTimeMillis_validIn_validOut() {
        Deadline deadline = new Deadline("Description", 537409800000L);
        assertEquals(537409800000L, deadline.getTimeMillis());
    }
    
    @Test
    public void toString_validIn_validOut() {
        Deadline deadline = new Deadline("Description", "12.01.1987 0230");
        assertEquals(
                "[D][\u2718] Description (by: 12.01.1987 02:30 AM)",
                deadline.toString());
    }
}

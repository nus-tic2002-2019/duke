package seedu.duke.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    @DisplayName("Inserting todo to study for Final Examination")
    public void TestToString() {
        ToDo todo = new ToDo("Study for Final Examiniations");
        assertEquals("[T][\u2718] Study for Final Examiniations", todo.toString());
        
        todo.setDone();
        assertEquals("[T][\u2713] Study for Final Examiniations", todo.toString());
    }
}
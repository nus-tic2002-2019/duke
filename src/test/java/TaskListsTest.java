import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListsTest {
    @Test
    public void TaskListsTest() {
        TaskLists testList = new TaskLists();
        assertEquals(true, testList.isEmpty());
    }
}

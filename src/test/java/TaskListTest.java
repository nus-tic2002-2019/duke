import org.junit.jupiter.api.Test;
import task.Todo;
import tasklist.TaskList;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void TaskListGetSizeTest(){
        TaskList task = new TaskList();
        assertEquals(0, task.getSize());
        Todo todo = new Todo("hello", 2);
        task.addTask(todo);
        assertEquals(1, task.getSize());
    }

}

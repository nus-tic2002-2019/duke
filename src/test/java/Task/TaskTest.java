package Task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    Task task = new Task("todo");
    @Test
    public void check_getStatusIconDefaultState(){
        assertEquals("\u2718", task.getStatusIcon());
    }

}
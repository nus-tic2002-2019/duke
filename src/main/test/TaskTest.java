package Tasks;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import task.Todo;
import tasklist.TaskList;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class Task {



    @Test
    public void testToString(){
        Task t = new Task();
        assertEquals("[" + t.getStatusIcon() + "]" + this.getDescription());
    }



}

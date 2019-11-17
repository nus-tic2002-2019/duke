package Tasks;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    @Test
    public void toDoTest1(){
        ToDos todo1 = new ToDos("Duke project");
        ToDos todo2 = new ToDos("Learn how to drive");
        ToDos todo3 = new ToDos("Learn Tennis");

        assertEquals("[T] [✘] Duke project",todo1.toString());
        assertEquals("[T] [✘] Learn how to drive",todo2.toString());
        assertEquals("[T] [✘] Learn Tennis",todo3.toString());

        todo2.editDone(true);
        assertEquals("[T] [✓] Learn how to drive",todo2.toString());

    }


}

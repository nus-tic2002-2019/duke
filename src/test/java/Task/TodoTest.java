package Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    public void todoTest(){
        Todo todo = new Todo("prepare exam");
        assertEquals("[T]prepare exam", todo.toString());
    }
}
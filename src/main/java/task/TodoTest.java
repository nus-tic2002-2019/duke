package task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest{
    @Test
    @DisplayName("Inserting Todo for Test Case 1 ")
    void TestToString() {
        Todo todo = new Todo("To Complete Test Case 1");
        assertEquals("[T] [\u2718]To Complete Test Case 1", todo.toString());
    }
}
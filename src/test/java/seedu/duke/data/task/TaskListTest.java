package seedu.duke.data.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest{
    @Test
    public void TestAddToTaskList() {
        TaskList taskList = new TaskList();
        taskList.addToTaskList(new ToDo("Complete Assignment."));

        assertEquals(taskList.getSize(), 1);
        assertEquals(taskList.getTask(0).toString(), "[T][\u2718] Complete Assignment.");

        taskList.deleteFromTaskList(0);
        assertEquals(taskList.getSize(), 0);
    }
}
import org.junit.jupiter.api.Test;
import tasks.*;

import java.time.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList tasksList = new TaskList();
    private static ArrayList<Task> testArray;

    public TaskListTest() {
        testArray = tasksList.getList();
        testArray.add(new Todo("return book"));
        testArray.add(new Deadline("Duke project", LocalDate.parse("2019-11-17")));
        testArray.add(new Event("Drone Training", LocalDate.parse("2019-11-24"), LocalTime.parse("18:00")));
    }

    @Test
    public void getListTest(){
        assertEquals("[[T][✘] return book, [D][✘] Duke project (by: 2019-11-17), [E][✘] Drone Training (on: Nov 24 2019 at: 6:00PM)]",
                tasksList.getList().toString());
    }

    @Test
    public void addTasksTest(){
        tasksList.addTasks("JUnit testing");
        assertEquals("JUnit testing", testArray.get(3).getTask());

        tasksList.addTasks("return library books", "deadline", "2019-11-21");
        assertEquals(("return library books, " + false + ", Nov 21 2019"),
                testArray.get(4).getTask() + ", " + testArray.get(4).getDone() + ", " + testArray.get(4).getDate());

        tasksList.addTasks("volunteer dinner", "event", "2019-11-23 18:00");
        assertEquals(("volunteer dinner, " + false + ", Nov 23 2019, 6:00PM"),
                testArray.get(5).getTask() + ", " + testArray.get(5).getDone() + ", "
                        + testArray.get(5).getDate() + ", " + testArray.get(5).getTime());
    }

    @Test
    public void deleteTasksTest() {
        tasksList.deleteTasks("1");
        assertEquals("Duke project, Nov 17 2019", testArray.get(0).getTask() + ", " + testArray.get(0).getDate());
    }
}

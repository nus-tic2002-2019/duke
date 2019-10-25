/**
 * Test all the functions.
 * @DisplauName Name of the testcase that are being tested
 * Deadline Test will test if the function for deadline is working
 * Delete Test will test if the function for delete is working
 * Todo Test will test if the function for todo is working
 * Event Test will test if the function for event is working
 * Add Test will test if the function for add is working
 *
 */

package duke;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    @DisplayName("Todo Test")
    public void TodoTest() {
        Todo t = new Todo("Get Description");
        assertEquals("Get Description", t.getDescription());
    }
    @Test
    @DisplayName("Add Test")
    public void AddListTest() {
        Tasklist l = new Tasklist();
        Task newTask = new Task("Task Description");
        l.addTask(newTask);
        assertEquals(1, l.getTasks().size());
        assertEquals(newTask.getDescription(), l.getTask(0).getDescription());
    }
    @Test
    @DisplayName("Delete Test")
    public void DeleteListTest() {
        Tasklist l = new Tasklist();
        Task addTask = new Task("Task Description");
        Task deleteTask = new Task("Task Description");
        l.addTask(addTask);
        l.deleteTask(0);
        assertEquals(0, l.getTasks().size());
    }
    @Test
    @DisplayName("Event Test")
    public void EventTest(){
        Event e = new Event("return book", LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals("return book", e.getDescription());
    }
    @Test
    @DisplayName("Deadline Test")
    public void DeadlineTest(){
        Deadline d = new Deadline("return book", LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals("return book", d.getDescription());
    }
}
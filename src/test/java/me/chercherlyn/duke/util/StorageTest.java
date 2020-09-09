package me.chercherlyn.duke.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import me.chercherlyn.duke.task.tasks.Event;
import me.chercherlyn.duke.task.tasks.Todo;

public class StorageTest {
    @Test
    public void generalTest() {
        // prepare
        TaskList tasksInit = new TaskList();
        tasksInit.add(new Todo("a"));
        tasksInit.add(new Event("b", "01.01.2015 0200"));
        
        Storage storage = new Storage("data/duke_test.txt");
        
        // test
        storage.saveTasks(tasksInit);
        TaskList tasksLoaded = storage.loadTasks();
        
        List<String> expected = tasksInit.getTasks().stream()
                .map(Object::toString).collect(Collectors.toList());
        List<String> actual = tasksLoaded.getTasks().stream()
                .map(Object::toString).collect(Collectors.toList());
        
        assertEquals(2, actual.size());
        assertEquals(expected, actual);
    }
}

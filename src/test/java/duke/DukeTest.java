package duke;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DukeTest {

    @Test
    public void addOneTaskTest() {
        Todo t = new Todo("Walk the dog", true);
        TaskList list = new TaskList();
        list.add(t);
        assertEquals(1, list.getCount());
    }
}
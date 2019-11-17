package duke;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DukeTest {
    /***
     * testing to add a todo task
     */
    @Test
    public void addOneTaskTest() {
        Todo t = new Todo("read book", false);
        TaskList list = new TaskList();
        list.add(t);
        assertEquals(1, list.getCount());
    }

    /***
     * testing to add a todo task using parser
     */
    @Test
    public void addOneTaskByParserTest(){
        Parser parser = new Parser();
        TaskList list = new TaskList();
        parser.parseCommand("todo read books", new MockUI(), list, new MockStorage());
        assertEquals(1, list.getCount());
        assertEquals(list.get(0).getTaskName(), "read books");

    }

    /***
     * testing to add a deadline task using parser
     */
    @Test
    public void addOneDeadlineTaskByParserTest() {
        Parser parser = new Parser();
        TaskList list = new TaskList();
        parser.parseCommand("deadline read books /by 2019-11-17 11:30PM", new MockUI(), list, new MockStorage());
        assertEquals(1, list.getCount());
        assertEquals(list.get(0).getTaskName(), "read books");
        String expectedDate = "Nov 17 2019 11:30 PM";
        Task firstTask = list.line.get(0);
        String[] line = firstTask.toString().replace("(", "").replace(")", "").split("by: "); // get date from task
        assertEquals(line[1], expectedDate);
    }

    /***
     * testing to add one event task using parser
     */
    @Test
    public void addOneEventTaskByParserTest(){
            Parser parser = new Parser();
            TaskList list = new TaskList();
            parser.parseCommand("event project meeting /at 2019-11-24 11:30AM", new MockUI(), list, new MockStorage());
            assertEquals(1, list.getCount());
            assertEquals("project meeting", list.get(0).getTaskName());
            String expectedDate = "Nov 24 2019 11:30 AM";
            Task firstTask = list.line.get(0);
            String[] line = firstTask.toString().replace("(", "").replace(")", "").split("at: "); // get date from task
            assertEquals(expectedDate, line[1]);
    }

    /***
     * testing to set a task to done
     */
    @Test
    public void taskDoneByParserTest(){
        Parser parser = new Parser();
        TaskList list = new TaskList();
        Task task = new Task("read book", false);
        list.add(task);
        assertEquals(1, list.getCount());
        parser.parseCommand("done 1", new MockUI(), list, new MockStorage());
        assertEquals(true, list.get(0).getTaskDone());
    }

    /***
     * testing to delete a task
     */
    @Test
    public void deleteTaskByParser(){
        Parser parser = new Parser();
        TaskList list = new TaskList();
        Task task = new Task("read book", false);
        list.add(task);
        assertEquals(1, list.getCount());
        parser.parseCommand("delete 1", new MockUI(), list, new MockStorage());
        assertEquals(0, list.getCount());
    }

}
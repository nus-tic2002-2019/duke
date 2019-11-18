import duke.exception.DukeException;
import duke.tasklist.Deadlines;
import duke.tasklist.Priority;
import duke.tasklist.TaskList;
import duke.tasklist.Todo;
import duke.parser.Parser;
import duke.storage.Storage;

import duke.ui.Ui;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class DukeTest {

    @Test
    //getDescription from Todo Class
    public void testTodoGetDescription(){
        assertEquals("[T][\u2718][H]test Todo High Priority", new Todo("test Todo High Priority", false, Priority.HIGH).getDescription());
    }

    @Test
    //saveToFile method from Deadlines Class
    public void testDeadlineSaveToFile(){
        assertEquals("D | 1 | L | test save to file | 2019-06-06\r", new Deadlines("test save to file", LocalDate.parse("2019-06-06"), true, Priority.LOW).saveToFile());
    }

    @Test
    //deleteFromList method from TaskList.
    public void testDeleteFromList(){
        try{
            TaskList loadList = new TaskList(new Storage("testtasks.txt").loadList());
            loadList.deleteFromList("1");
            assertEquals(4, loadList.getList().size());
        } catch (FileNotFoundException | DukeException e){
            new Ui().showLoadingError();
        }
    }

    @Test
    //DukeException from Parser Class
    public void missingTime_Parser_throwException(){
        String textInput = "event test wrong time format /priority High /at 2019-11-15 22:00";
        try {
            new Parser(textInput);
            fail();
        } catch (DukeException e){
            assertEquals("  ☹ OOPS!!! Missing time information. Please use Start Time - End Time e.g. 10:00 - 12:00", e.getMessage());
        }

    }

/*
To quickly populate the file
T | 1 | H | read book
D | 0 | L | return book | 2019-06-06
E | 0 | M | project meeting | 2019-08-06 22:00 - 23:00
T | 1 | L | join sports club
D | 0 | L | test save file | 2019-11-09

 */
}

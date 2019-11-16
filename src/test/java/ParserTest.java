import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void commandTest(){
        assertEquals("todo", Parser.command("todo borrow book"));
    }


    @Test
    void taskNumberTest() {
        assertEquals(3, Parser.taskNumber("done 4"));
    }

    @Test
    void descriptionTest(){
        assertEquals("borrow book", Parser.description("todo borrow book"));
    }

    @Test
    void dateTest(){
        assertEquals("09-11-2019", Parser.date("deadline return book /by 09-11-2019"));
        assertEquals("0", Parser.date("deadline return book"));
    }

}

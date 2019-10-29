import error.IllegalStringException;
import org.junit.jupiter.api.Test;
import parser.Parser;
import command.Command;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parserTestDescription(){
        try{
            Command command = Parser.parse("deadline homework \\/by 2019-10-08 1" );
            assertEquals("homework", command.getDescription());
        } catch (IllegalStringException e) {
            //this should not happen
            System.out.println("there is error");
        }
    }

    @Test
    public void parserTestDate(){
        try{
            Command command = Parser.parse("deadline homework \\/by 2019-10-08 1" );
            assertEquals(LocalDate.parse("2019-10-08"), command.getDate());
        } catch (IllegalStringException e) {
            //this should not happen
            System.out.println("there is error");
        }
    }

    @Test
    public void dummyTest(){
        assertEquals(2,2);
    }
}
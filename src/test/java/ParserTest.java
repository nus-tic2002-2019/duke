import command.Command;
import error.IllegalStringException;
import error.InvalidPriorityException;
import error.MissingDateException;
import error.MissingIndexException;
import org.junit.jupiter.api.Test;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parserDescriptionTest(){
        try{
            Ui ui = new Ui();
            TaskList store = new TaskList();
            Storage storage = new Storage("");
            Command command = Parser.parse("todo homework 1" );
            command.execute(store, ui, storage);
            assertEquals("homework", command.getDescription());
        } catch (IllegalStringException e) {
            //this should not happen
            System.out.println("there is error");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("there is error");
        } catch (InvalidPriorityException e) {
            System.out.println("there is error");
        }catch (MissingIndexException e) {
            System.out.println("there is error");
        }catch (MissingDateException e) {
            System.out.println("there is error");
        }
    }

    @Test
    public void prepareExitTest(){
        try{
            Ui ui = new Ui();
            TaskList store = new TaskList();
            Storage storage = new Storage("");
            Command command = Parser.parse("bye" );
            command.execute(store, ui, storage);
            assertEquals(true, command.isExit());
        } catch (IllegalStringException e) {
            //this should not happen
            System.out.println("there is error");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("there is error");
        } catch (InvalidPriorityException e) {
            System.out.println("there is error");
        }catch (MissingIndexException e) {
            System.out.println("there is error");
        }catch (MissingDateException e) {
            System.out.println("there is error");
        }
    }

    @Test
    public void dummyTest(){
        assertEquals(2,2);
    }
}

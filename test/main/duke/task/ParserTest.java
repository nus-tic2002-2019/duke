package main.duke.task;

import main.duke.command.*;
import main.duke.exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseInput() throws DukeException {
        //region test: to-do, deadline, event.
        UpdateCommand uc;
        Command c = Parser.parseInput("todo dance");
        assertEquals(c.getClass(), UpdateCommand.class);
        uc = (UpdateCommand) c;
        assertEquals(uc.getTask().getDescription(), new ToDo("dance").getDescription());
        assertEquals(uc.getOperation(), UpdateCommand.Operation.Add);

        c = Parser.parseInput("TOdO   fart");   //parser must be robust against capitalization and multiple whitespaces.
        assertEquals(c.getClass(), UpdateCommand.class);
        uc = (UpdateCommand) c;
        assertEquals(uc.getTask().getDescription(), new ToDo("fart").getDescription());
        assertEquals(uc.getOperation(), UpdateCommand.Operation.Add);

        c = Parser.parseInput("deadline TIC2002 /by 17/11/2019");
        assertEquals(c.getClass(), UpdateCommand.class);
        uc = (UpdateCommand) c;
        Deadline d = (Deadline) uc.getTask();
        assertEquals(d.getDescription(), new Deadline("TIC2002", "17/11/2019").getDescription());
        assertEquals(d.getDeadline(), new Deadline("TIC2002", "17/11/2019").getDeadline());
        assertEquals(uc.getOperation(), UpdateCommand.Operation.Add);

        c = Parser.parseInput("event Exercise Wallaby Reservist /at 16/10/2019 1900");
        assertEquals(c.getClass(), UpdateCommand.class);
        uc = (UpdateCommand) c;
        Event e = (Event) uc.getTask();
        assertEquals(e.getStart_endTime(), new Event("Exercise Wallaby Reservist", "16/10/2019 1900").getStart_endTime());
        assertEquals(e.getDescription(), new Event("Exercise Wallaby Reservist", "16/10/2019 1900").getDescription());
        assertEquals(uc.getOperation(), UpdateCommand.Operation.Add);

        assertThrows(DukeMissingDescException.class, () -> {
            Parser.parseInput("todo ");
        });
        //endregion
        //region test: check, list, done, delete, bye.
        c = Parser.parseInput("check 16/10/2019");
        assertEquals(c.getClass(), CheckCommand.class);
        assertEquals(((CheckCommand) c).getCheck_time(), Parser.parseDateTimeStr("16/10/2019"));

        c = Parser.parseInput("list");
        assertEquals(c.getClass(), ListCommand.class);

        c = Parser.parseInput("done 1");
        assertEquals(c.getClass(), UpdateCommand.class);
        uc = (UpdateCommand) c;
        assertEquals(uc.getOperation(), UpdateCommand.Operation.Done);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            Parser.parseInput("done -1");
        });   //check invalid indexes

        c = Parser.parseInput("delete 1");
        assertEquals(c.getClass(), UpdateCommand.class);
        uc = (UpdateCommand) c;
        assertEquals(uc.getOperation(), UpdateCommand.Operation.Delete);


        c = Parser.parseInput("bye");
        assertEquals(c.getClass(), ExitCommand.class);
        assertTrue(c.isExit());
        //endregion
    }
}
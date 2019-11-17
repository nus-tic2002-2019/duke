package parser;

import duke.command.*;
import duke.others.DukeException;
import duke.others.Keyword;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.*;

import duke.ui.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ParserTest {

    Ui ui = new Ui();
    Storage storage = new Storage("/Users/spencernah/code/duke/data/data_test.txt");
    TaskList tasks;

    @BeforeEach
    public void setup() {
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_ViewByDateCommand_noDate() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse(Keyword.VIEW_BY_DATE);
        });
    }

    @Test
    public void parse_ViewByDateCommand_incorrectDateFormat() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse(Keyword.VIEW_BY_DATE + "/20190805");
        });
    }

    @Test
    public void parse_DoneCommand_incorrectIndex() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse("done abc");
        });
    }

    @Test
    public void parse_DeleteCommand_incorrectIndex() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse("delete abc");
        });
    }

    @Test
    public void parse_AddCommand_deadline_noDesc() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse(Keyword.ADD_DEADLINE);
        });
    }

    @Test
    public void parse_AddCommand_deadline_noDate() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse(Keyword.ADD_DEADLINE + "test/");
        });
    }

    @Test
    public void parse_AddCommand_deadline_incorrectDateFormat() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse(Keyword.ADD_DEADLINE + "test/20190805");
        });
    }

    @Test
    public void parse_AddCommand_todo_noDesc() {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = Parser.parse(Keyword.ADD_TODO);
        });
    }

    @Test
    public void parse_AddCommand_Deadline_dateFormat() {
        String test = "";
        try {
            Command c = Parser.parse(Keyword.ADD_DEADLINE + "test/2019-8-5");
            c.execute(tasks, ui, storage);
            test = ((Deadline) tasks.get(tasks.size()-1)).getFormattedDate();
        } catch (DukeException e) {
        } catch (IOException e) {
        }
        Assertions.assertEquals(test, "05 Aug 2019");
    }

}

package duke.command;

import duke.others.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SetDoAfterCommandTest {
    String filePath = "/Users/spencernah/code/duke/data/data_test.txt";
    Ui ui = new Ui();
    Storage storage = new Storage(filePath);
    TaskList tasks;


    @BeforeEach
    public void setup() {
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    @Test
    public void SetDoAfterCommand_execute_valid() throws IOException, DukeException {
            Command c = new SetDoAfterCommand(1, 3);
            c.execute(tasks, ui, storage);
            Assertions.assertEquals(tasks.get(1).getDoAfter(), 3);
    }

    @Test
    public void SetDoAfterCommand_execute_invalidParentChildIndex() throws IOException, DukeException {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = new SetDoAfterCommand(3, 2);
            c.execute(tasks, ui, storage);
        });
    }

    @Test
    public void SetDoAfterCommand_execute_indexOutOfRange_positive() throws IOException, DukeException {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = new SetDoAfterCommand(10, 3);
            c.execute(tasks, ui, storage);
        });
    }

    @Test
    public void SetDoAfterCommand_execute_indexOutOfRange_negative() throws IOException, DukeException {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = new SetDoAfterCommand(3, -1);
            c.execute(tasks, ui, storage);
        });
    }

    @Test
    public void SetDoAfterCommand_execute_sameParentChildIndex() throws IOException, DukeException {
        Assertions.assertThrows(DukeException.class, () -> {
            Command c = new SetDoAfterCommand(1, 1);
            c.execute(tasks, ui, storage);
        });
    }

}

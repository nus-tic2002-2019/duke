package duke.task;

        import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task task = new Task("join sports club");

    @Test
    void getStatusIconTest() {
        assertEquals("\u2718", task.getStatusIcon());
    }

    @Test
    void toStringTest() {
        assertEquals("[\u2718] join sports club", task.toString());
    }

    @Test
    void save_toStringTest()  {
        assertEquals("\u2718 | join sports club", task.save_toString());
    }
}

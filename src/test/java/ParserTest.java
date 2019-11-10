import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parseInputTest1() {
        Assert.assertEquals("todo","todo homework");
    }

    @Test
    public void parseInputTest2() {
        Assert.assertEquals("events","events meeting /at home");
    }
}
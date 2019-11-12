import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseInputTest() {
        assertEquals("doAfter", new Parser().parseInput("do HOMEWORK2 After monday"));
    }
}

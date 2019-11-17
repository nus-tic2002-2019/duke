import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void invalidCommand_throwsDukeException_ByDefault_Test(){
        try {
            Parser.parse("hey yoo");
            } catch (DukeException e){
            assertEquals("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}

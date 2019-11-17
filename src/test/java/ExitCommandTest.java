import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExitCommandTest {
    @Test
    public void ExitCommand_Return_ExitIsTrue_ByDefault_Test(){
        assertEquals(true,new ExitCommand().isExit());
    }


}

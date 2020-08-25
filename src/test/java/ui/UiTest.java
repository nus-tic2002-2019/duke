package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UiTest {

    @Test
    void customDate(){
        assertEquals("Nov 1 2019 10:30 PM", Ui.customDate("2019-11-01T22:30"));
        assertEquals("Nov 1 2020 08:30 PM", Ui.customDate("2020-11-01T20:30"));
    }
}

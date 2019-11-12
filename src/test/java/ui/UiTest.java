package ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    void customDate(){
        assertEquals("Oct 10 2019 11:59 PM", Ui.customDate("2019-10-10T23:59"));
    }
}

package main;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UITest {

    @Test
    void dukePrintTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        UI.dukePrint("\tThis is a test Message to be printed by Duke!");

        String expectedOutput = "\t____________________________________________________________\n" +
                "\tThis is a test Message to be printed by Duke!\n" +
                "\t____________________________________________________________\n";

        Assert.assertEquals(expectedOutput,outContent.toString());
    }
}
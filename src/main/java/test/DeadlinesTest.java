package test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlinesTest {

    @org.junit.jupiter.api.Test
    void printDeadlineDte() {
        assertEquals("Sun, 17 Nov 2019 23:59" , "17-Nov-2019 2359");
    }
}
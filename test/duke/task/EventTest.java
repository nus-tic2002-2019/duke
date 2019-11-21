/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.task;

import duke.priority.Priority;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author lug3g
 */
public class EventTest {
    
    public EventTest() {
    }
    
    @org.testng.annotations.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.testng.annotations.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getAt method, of class Event.
     */
    @org.testng.annotations.Test
    public void testGetAt() {
        System.out.println("getAt");
        Event instance = new Event("testEvent",Priority.LOW,"2019-12-14");
        String expResult = "14 December 2019";
        String result = instance.getAt();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of printTask method, of class Event.
     */
    @org.testng.annotations.Test
    public void testPrintTask() {
        System.out.println("printTask");
        Event instance = new Event("testEvent",Priority.LOW,"2019-12-14");
        instance.markAsDone();
        String expResult = "[E][\u2713] testEvent priority: LOW (at: 14 December 2019)";
        String result = instance.printTask();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeTask method, of class Event.
     */
    @org.testng.annotations.Test
    public void testWriteTask() {
        System.out.println("writeTask");
        Event instance = new Event("testEvent",Priority.LOW,"2019-12-14");
        instance.markAsDone();
        String expResult = "E | 1 | testEvent | LOW | 14 December 2019";
        String result = instance.writeTask();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateTime method, of class Event.
     */
    @org.testng.annotations.Test
    public void testGetDateTime() {
        System.out.println("getDateTime");
        Event instance = new Event("testEvent",Priority.LOW,"2019-12-14");
        String expResult = "2019-12-14";
        String result = instance.getDateTime();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.task;

import duke.priority.Priority;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author lug3g
 */
public class DeadlineTest {
    
    public DeadlineTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getBy method, of class Deadline.
     */
    @Test
    public void testGetBy() {
        System.out.println("getBy");
        Deadline instance = new Deadline("testDeadline",Priority.HIGH,"2019-11-12");
        String expResult = "12 November 2019";
        String result = instance.getBy();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of printTask method, of class Deadline.
     */
    @Test
    public void testPrintTask() {
        System.out.println("printTask");
        Deadline instance = new Deadline("testDeadline",Priority.HIGH,"2019-11-12");
        String expResult = "[D][\u2718] testDeadline priority: HIGH (by: 12 November 2019)";
        String result = instance.printTask();
        assertEquals(result, expResult);
    }

    /**
     * Test of writeTask method, of class Deadline.
     */
    @Test
    public void testWriteTask() {
        System.out.println("writeTask");
        Deadline instance = new Deadline("testDeadline",Priority.HIGH,"2019-11-12");
        String expResult = "D | 0 | testDeadline | HIGH | 12 November 2019";

        String result = instance.writeTask();
        assertEquals(result, expResult);
    }

    /**
     * Test of getDateTime method, of class Deadline.
     */
    @Test
    public void testGetDateTime() {
        System.out.println("getDateTime");
        Deadline instance = new Deadline("testDeadline",Priority.HIGH,"2019-11-12");
        String expResult = "2019-11-12";
        String result = instance.getDateTime();
        assertEquals(result, expResult);
    }
    
}

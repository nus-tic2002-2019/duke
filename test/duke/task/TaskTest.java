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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author lug3g
 */
public class TaskTest {
    
    public TaskTest() {
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
     * Test of markAsDone, markAsUndone, isDone method, of class Task.
     */
    @org.testng.annotations.Test
    public void testIsDone() {
        System.out.println("isDone");
        Task instance = new Todo("test get status",Priority.MEDIUM);
        boolean expResult = false;
        boolean result = instance.isDone();
        assertEquals(result, expResult);
        
        instance.markAsDone();
        expResult = true;
        result = instance.isDone();
        assertEquals(result, expResult);

        instance.markAsUndone();
        expResult = true;
        result = instance.isDone();
        assertEquals(result, expResult);
        
    }

    /**
     * Test of getDescription method, of class Task.
     */
    @org.testng.annotations.Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Task instance = new Todo("test get status",Priority.MEDIUM);;
        String expResult = "test get status";
        String result = instance.getDescription();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getp method, of class Task.
     */
    @org.testng.annotations.Test
    public void testGetp() {
        System.out.println("getp");
        Task instance = new Todo("test get status",Priority.MEDIUM);
        Priority expResult = Priority.MEDIUM;
        Priority result = instance.getp();
        assertEquals(result, expResult);
        
        instance.setPriority(Priority.HIGH);
        expResult = Priority.HIGH;
        result = instance.getp();
        assertEquals(result, expResult);
        
        instance.setPriority(Priority.LOW);
        expResult = Priority.LOW;
        result = instance.getp();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getStatusIcon method, of class Task.
     */
    @org.testng.annotations.Test
    public void testGetStatusIcon() {
        System.out.println("getStatusIcon");
        Task instance = new Todo("test get status",Priority.MEDIUM);
        String expResult = "\u2718";
        String result = instance.getStatusIcon();
        assertEquals(result, expResult);
        
        instance.markAsDone();
        expResult = "\u2713";
        result = instance.getStatusIcon();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getStatus method, of class Task.
     */
    @org.testng.annotations.Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Task instance = new Todo("test get status",Priority.MEDIUM);
        int expResult = 0;
        int result = instance.getStatus();
        assertEquals(result, expResult);
        
        instance.markAsDone();
        expResult = 1;
        result = instance.getStatus();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    public class TaskImpl extends Task {

        public TaskImpl() {
            super("", null);
        }

        @Override
        public String getDateTime() {
            return "";
        }

        @Override
        public String printTask() {
            return "";
        }

        @Override
        public String writeTask() {
            return "";
        }
    }
    
}

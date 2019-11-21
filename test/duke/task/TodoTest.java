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
public class TodoTest {
    
    public TodoTest() {
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
     * Test of printTask method, of class Todo.
     */
    @org.testng.annotations.Test
    public void testPrintTask() {
        System.out.println("printTask");
        Todo instance = new Todo("test Todo",Priority.HIGH);
        String expResult = "[T][\u2718] test Todo priority: HIGH";
        String result = instance.printTask();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of writeTask method, of class Todo.
     */
    @org.testng.annotations.Test
    public void testWriteTask() {
        System.out.println("writeTask");
        Todo instance = new Todo("test Todo",Priority.HIGH);
        String expResult = "T | 0 | test Todo | HIGH";
        String result = instance.writeTask();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getDateTime method, of class Todo.
     */
    @org.testng.annotations.Test
    public void testGetDateTime() {
        System.out.println("getDateTime");
        Todo instance = new Todo("test Todo",Priority.HIGH);
        String expResult = "";
        String result = instance.getDateTime();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}

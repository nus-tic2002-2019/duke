/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.task;

import duke.priority.Priority;
import java.util.ArrayList;
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
public class TaskListTest {
    
    public TaskListTest() {
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
     * Test of getSize method, of class TaskList.
     */
    @org.testng.annotations.Test
    public void testGetSize() {
        System.out.println("getSize");
        TaskList instance = new TaskList();
        Task task1 = new Todo("test",Priority.LOW);
        Task task2 = new Todo("test1",Priority.MEDIUM);
        Task task3 = new Todo("test3",Priority.HIGH);
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(result, expResult);
        instance.getTaskList().add(task1);
        expResult = 1;
        result = instance.getSize();
        assertEquals(result, expResult);
        instance.getTaskList().add(task2);
        expResult = 2;
        result = instance.getSize();
        assertEquals(result, expResult);
        instance.getTaskList().add(task3);
        expResult = 3;
        result = instance.getSize();
        assertEquals(result, expResult);
        instance.getTaskList().remove(1);
        expResult = 2;
        result = instance.getSize();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getTask method, of class TaskList.
     */
    @org.testng.annotations.Test
    public void testGetTask() {
        System.out.println("getTask");
        int x = 0;
        TaskList instance = new TaskList();
        Task task1 = new Todo("test",Priority.LOW);
        Task task2 = new Todo("test1",Priority.MEDIUM);
        Task task3 = new Todo("test3",Priority.HIGH);
        instance.getTaskList().add(task1);
        instance.getTaskList().add(task2);
        instance.getTaskList().add(task3);
        Task expResult = task1;
        Task result = instance.getTask(0);
        assertEquals(result, expResult);
        expResult = task2;
        result = instance.getTask(1);
        assertEquals(result, expResult);
        expResult = task3;
        result = instance.getTask(2);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }
}

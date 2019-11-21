/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.storage;

import duke.priority.Priority;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
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
public class StorageTest {
    
    public StorageTest() {
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
     * Test of save method, of class Storage.
     */
    @org.testng.annotations.Test
    public void testSaveandLoad() {
        System.out.println("save and load");
        TaskList tasks = new TaskList();
        tasks.getTaskList().add(new Todo("study",Priority.HIGH));
        Storage instance = new Storage(Paths.get("").toAbsolutePath().toString() + "\\src\\duke\\data\\duke.txt");
        instance.save(tasks);
        TaskList loadtasks;
        try {
            loadtasks = new TaskList(instance.load());
        } catch (FileNotFoundException ex) {
            loadtasks = new TaskList();
        }
        assertEquals(loadtasks.getSize(),tasks.getSize());
        assertEquals(loadtasks.getTaskList(),tasks.getTaskList());
        
        tasks.getTaskList().add(new Event("new event",Priority.MEDIUM,"2019-11-11"));
        instance.save(tasks);
        try {
            loadtasks = new TaskList(instance.load());
        } catch (FileNotFoundException ex) {
            loadtasks = new TaskList();
        }
        assertEquals(loadtasks.getTaskList(),tasks.getTaskList());
        
        tasks.getTaskList().add(new Deadline("submission",Priority.LOW,"2019-12-11"));
        instance.save(tasks);
        try {
            loadtasks = new TaskList(instance.load());
        } catch (FileNotFoundException ex) {
            loadtasks = new TaskList();
        }
        assertEquals(loadtasks.getTaskList(),tasks.getTaskList());
    }
}

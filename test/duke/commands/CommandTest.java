/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
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
public class CommandTest {
    
    public CommandTest() {
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
     * Test of getCommand method, of class Command.
     */
    @org.testng.annotations.Test
    public void testGetCommand() {
        System.out.println("getCommand");
        Command instance = new Command("todo") {

            @Override
            public void execute(TaskList tasks, Ui ui, Storage storage) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean isExit() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        String expResult = "todo";
        String result = instance.getCommand();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of isError method, of class Command.
     */
    @org.testng.annotations.Test
    public void testIsError() {
        System.out.println("isError");
        Command instance = new Command("Error") {
            @Override
            public void execute(TaskList tasks, Ui ui, Storage storage) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public boolean isExit() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        boolean expResult = true;
        boolean result = instance.isError();
        assertEquals(result, expResult);
        
        instance = new Command("todo") {
            @Override
            public void execute(TaskList tasks, Ui ui, Storage storage) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public boolean isExit() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        expResult = false;
        result = instance.isError();
        assertEquals(result, expResult);
        
        instance = new Command("Error") {
            @Override
            public void execute(TaskList tasks, Ui ui, Storage storage) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public boolean isExit() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        expResult = true;
        result = instance.isError();
        assertEquals(result, expResult);
    }

    /**
     * Test of isExit method, of class Command.
     */
    @org.testng.annotations.Test
    public void testIsExit() {
        System.out.println("isExit");
        Command instance = null;
        
        instance = new ExitCommand("bye");
        boolean expResult = true;
        boolean result = instance.isExit();
        assertEquals(result, expResult);
        
        instance = new AddCommand("todo");
        expResult = false;
        result = instance.isExit();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    public class CommandImpl extends Command {

        public CommandImpl() {
            super("");
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
        }

        @Override
        public boolean isExit() {
            return false;
        }
    }
}

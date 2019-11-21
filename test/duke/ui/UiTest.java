/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.ui;

import duke.exception.DukeException;
import duke.task.TaskList;
import static duke.ui.Ui.getDate;
import java.text.ParseException;
import java.util.Date;
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
public class UiTest {
    
    public UiTest() {
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
     * Test of getDate and isSameDate method, of class Ui.
     */
    @org.testng.annotations.Test
    public void testIsSameDate() throws DukeException, ParseException {
        System.out.println("isSameDate");
        Date date1 = getDate("2019-11-12");
        Date date2 = getDate("2019-11-12");
        boolean expResult = true;
        boolean result = Ui.isSameDate(date1, date2);
        assertEquals(result, expResult);
        
        date1 = getDate("2019-12-12");
        date2 = getDate("2019-11-12");
        expResult = false;
        result = Ui.isSameDate(date1, date2);
        assertEquals(result, expResult);
        
        date1 = getDate("2019-14-13");
        date2 = getDate("2019-11-12");
        expResult = false;
        result = Ui.isSameDate(date1, date2);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of printDateTime method, of class Ui.
     */
    @org.testng.annotations.Test
    public void testPrintDateTime() throws ParseException, DukeException {
        System.out.println("printDateTime");
        String printdate = "2019-12-28";
        String expResult = "28 December 2019";
        String result = Ui.printDateTime(printdate);
        assertEquals(result, expResult);
        
        printdate = "2019-12-32";
        expResult = "32 December 2019";
        result = Ui.printDateTime(printdate);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}

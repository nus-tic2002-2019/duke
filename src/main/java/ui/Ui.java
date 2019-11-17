/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.ui;

import duke.exception.DukeException;
import duke.task.TaskList;
import java.text.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author lug3g
 */
public class Ui {
    private String line;
    public Ui(){
    
    }
    
    public static void printLine(){
        System.out.println("\t____________________________________________________________");
    }
    
    public static void welcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        printLine();
    }
    
    public String readCommand(){
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        this.line = line;
        return line;
    }
    
    public static void printTasks(TaskList tasks) throws DukeException{
        if ( tasks.getSize() < 1 ) {
            throw new DukeException();
        }
        printLine();
        System.out.println("\tHere are the tasks in your list:");
        for ( int i = 0; i < tasks.getSize() ; i++ ){
            System.out.printf("\t%d. %s\n",i+1,tasks.getTask(i).printTask());
        }
        printLine();
    }
    
    public static void printSameDayTasks(TaskList tasks,String stringdate) throws DukeException{
        if ( tasks.getSize() < 1 ) {
            throw new DukeException();
        }
        printLine();
        System.out.println("\tHere are the tasks in your list:");
        int j = 1;
        for ( int i = 0; i < tasks.getSize() ; i++ ){
            if ( tasks.getTask(i).getDateTime().equals("") ){
                continue;
            }
            try {
                if ( isSameDate(getDate(stringdate),getDate(tasks.getTask(i).getDateTime()))   ){
                    System.out.printf("\t%d. %s\n",j,tasks.getTask(i).printTask());
                    j++;
                }
            } catch (ParseException ex) {
                response("☹ OOPS!!! Please include a date description (yyyy-mm-dd.");
            }
        }
        printLine();
    }
    
    public static void exit(){
        response("Bye. Hope to see you again soon!");
    }
    
    public static void response(String line){
        printLine();
        System.out.printf("\t%s\n",line);
        printLine();
    }
    
    public static void addResponse(String line, int size){
        printLine();
        System.out.printf("\tGot it. I've added this task:\n\t  %s\n\tNow you have %d tasks in the list\n",line,size);
        printLine();
    }
    
    public static void delResponse(String line, int size){
        printLine();
        System.out.printf("\tNoted. I've removed this task: \n\t  %s\n\tNow you have %d tasks in the list\n",line,size);
        printLine();
    }
    
    public static void showLoadingError(){
        response("☹ OOPS!!! There is a loading error.");
    }
    
    public String getLine(){
        return this.line;
    }
    
    
    public static void convertDate(String stringdate) throws DukeException, ParseException{
        if ( stringdate.equals("") ){
            throw new DukeException();
        }
        
        Date dateTime=new SimpleDateFormat("yyyy-MM-dd").parse(stringdate);
    }
    
    public static String printDateTime(String printdate){
        String dateItems [] = printdate.split("-");
        String mth = new DateFormatSymbols().getMonths()[Integer.parseInt(dateItems[1]) -1];
        String dateTime = dateItems[2] + " " + mth + " " + dateItems[0];
        return dateTime;
    }
    
    public static Date getDate(String stringdate) throws DukeException, ParseException{
        if ( stringdate.equals("") ){
            throw new DukeException();
        }
        Date dateTime=new SimpleDateFormat("yyyy-MM-dd").parse(stringdate);
        return dateTime;
    }
    
    public static boolean isSameDate(Date date1,Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        boolean sameDay = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
                          cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        return sameDay;
    }
}

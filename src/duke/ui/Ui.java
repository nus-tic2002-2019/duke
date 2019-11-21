/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duke.ui;

import duke.exception.DukeException;
import duke.task.TaskList;
import java.text.*;
import java.util.*;
/**
 * Ui class is a class that deals the interaction with the users
 */
public class Ui {
    private String line;
    /**
     * Constructor method of the Ui
     */
    public Ui(){
    
    }
    /**
     * print a line in to format the response
     */
    public static void printLine(){
        System.out.println("\t____________________________________________________________");
    }
    /**
     * welcomes message for the user
     */
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
    /**
     * takes in user input
     * @return user input
     */
    public String readCommand(){
        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();
        this.line = userInput;
        return userInput;
    }
    /**
     * Print all tasks in task list
     * @param tasks takes in task list to be printed
     * @throws DukeException if there is no tasks, return error
     */
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
    /**
     * List out tasks in the same day
     * @param tasks takes in task list to be printed
     * @param stringdate takes in the date to find
     * @throws DukeException if there is no tasks in the task list, return error
     */
    public static void printSameDayTasks(TaskList tasks,String stringdate) throws DukeException{
        if ( tasks.getSize() < 1 ) {
            throw new DukeException();
        }
        printLine();
        System.out.println("\tHere are the tasks in your list containing tasks in " + stringdate + ":");
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
            if ( j == 1 ){
                System.out.println("\tThere is no tasks that has the same date on " + stringdate + ".");
            }
        }
        printLine();
    }
    /**
     * List out tasks that contains a certain keyword
     * @param tasks takes in task list to be printed
     * @param keywords takes in the keywords to find
     * @throws DukeException if there is no tasks in the task list, return error
     */
    public static void find(TaskList tasks,String keywords) throws DukeException{
        if ( tasks.getSize() < 1 ) {
            throw new DukeException();
        }
        printLine();
        System.out.println("\tHere are the tasks in your list containing the keyword, " + keywords + ":");
        int j = 1;
        for ( int i = 0; i < tasks.getSize() ; i++ ){
            if ( tasks.getTask(i).getDescription().contains(keywords) ){
                System.out.printf("\t%d. %s\n",j,tasks.getTask(i).printTask());
                j++;
            }
        }
    }
    /**
     * print goodbye message
     */
    public static void exit(){
        response("Bye. Hope to see you again soon!");
    }
    /**
     * print response to the user
     * @param line response of the user
     */
    public static void response(String line){
        printLine();
        System.out.printf("\t%s\n",line);
        printLine();
    }
    /**
     * print response to the user after adding task
     * @param line response of the user
     * @param size takes in size of the task
     */
    public static void addResponse(String line, int size){
        printLine();
        System.out.printf("\tGot it. I've added this task:\n\t  %s\n\tNow you have %d tasks in the list\n",line,size);
        printLine();
    }
    /**
     * print response to the user after deleting task
     * @param line response of the user
     * @param size takes in size of the task
     */
    public static void delResponse(String line, int size){
        printLine();
        System.out.printf("\tNoted. I've removed this task: \n\t  %s\n\tNow you have %d tasks in the list\n",line,size);
        printLine();
    }
    /**
     * show error when there is a loading error
     */
    public static void showLoadingError(){
        response("☹ OOPS!!! There is a loading error.");
    }
    /**
     * get user's message
     * @return user's input in this class
     */
    public String getLine(){
        return this.line;
    }
    /**
     * convert string to date
     * @param stringdate date in string
     * @throws DukeException throws error is date is not defined
     * @throws ParseException throws error is date is invalid format
     */
    public static void convertDate(String stringdate) throws DukeException, ParseException{
        if ( stringdate.equals("") ){
            throw new DukeException();
        }
        
        Date dateTime=new SimpleDateFormat("yyyy-MM-dd").parse(stringdate);
    }
    /**
     * print datetime in a another format
     * @param printdate date to be printed in string
     * @return datetime in another format.
     */
    public static String printDateTime(String printdate) throws DukeException, ParseException{
        convertDate(printdate);
        String dateItems [] = printdate.split("-");
        String mth = new DateFormatSymbols().getMonths()[Integer.parseInt(dateItems[1]) -1];
        String dateTime = dateItems[2] + " " + mth + " " + dateItems[0];
        return dateTime;
    }
    /**
     * get the date in datetime format
     * @param stringdate date to be converted in string
     * @return the datetime value of the date
     * @throws DukeException if date don't exist
     * @throws ParseException if date cannot be parsed
     */
    public static Date getDate(String stringdate) throws DukeException, ParseException{
        if ( stringdate.equals("") ){
            throw new DukeException();
        }
        Date dateTime=new SimpleDateFormat("yyyy-MM-dd").parse(stringdate);
        return dateTime;
    }
    /**
     * check if 2 dates happened on the same day
     * @param date1 1st date to be compared
     * @param date2 2nd date to be compared
     * @return if the dates are in the same day
     */
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

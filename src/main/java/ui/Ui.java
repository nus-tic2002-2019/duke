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
        //DukeEnum.DukeCommand.printCommands();
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
        
    }
    
    public String getLine(){
        return this.line;
    }
    
    private static Date convertDate(String stringdate) throws DukeException, ParseException{
        if ( stringdate.equals("") ){
            throw new DukeException();
        }
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(stringdate);  
        return date1;
    }
}

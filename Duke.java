package duke;

import duke.DukeEnum.DukeCommand;
import java.io.*;
import java.text.SimpleDateFormat;  
import java.util.*;
import java.lang.Exception;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Duke{
    static final String [] commands = {"bye","done","list","todo","event","deadline","delete"};
    
    public static void main(String[] args)  {
        welcome();
        String line = "";
        String words[];
        ArrayList<Task> Tasks = new ArrayList <>();
        Scanner in = new Scanner(System.in);
           
        while ( true ){
            line = in.nextLine();
            if ( line.toLowerCase().equals("bye") ){
                exit();
                break;
            }
            
            if ( checkCommandError(line) ){
                continue;
            }
            
            if ( checkMissingDescriptionError(line) ){
                continue;
            }
            
            if ( line.toLowerCase().startsWith("done") ) {
                done (Tasks,line);
                continue;
            }
            
            if ( line.toLowerCase().startsWith("list") ) {
                DukeCommand.executeCommand(1);
                list (Tasks,line);
                continue;
            }
            
            if ( line.toLowerCase().startsWith("todo")){
                createTodo (Tasks,line);
            }
            
            if ( line.toLowerCase().startsWith("event")){
                createEvent (Tasks,line);
            }
            
            if ( line.toLowerCase().startsWith("deadline")){
                createDeadline (Tasks,line);
            }
        }
    }
    
    public static void printTasks(ArrayList<Task> Tasks) throws DukeException{
        if ( Tasks.size() < 1 ) {
            throw new DukeException();
        }
        printLine();
        System.out.println("\tHere are the tasks in your list:");
        for ( int i = 0; i < Tasks.size() ; i++ ){
            System.out.printf("\t%d. %s\n",i+1,Tasks.get(i).printTask());
        }
        printLine();
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
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?\n");
        DukeCommand.printCommands();
        printLine();
    }
    
    public static void exit(){
        response("Bye. Hope to see you again soon!");
        return;
    }
    
    public static void response(String line){
        printLine();
        System.out.printf("\t%s",line);
        DukeCommand.printCommands();
        printLine();
    }
    
    public static void addResponse(String line, int size){
        printLine();
        System.out.printf("\tGot it. I've added this task:\n\t  %s\n\tNow you have %d tasks in the list\n",line,size);
        DukeCommand.printCommands();
        printLine();
    }
    
    public static void delResponse(String line, int size){
        printLine();
        System.out.printf("\tNoted. I've removed this task: \n\t  %s\n\tNow you have %d tasks in the list\n",line,size);
        DukeCommand.printCommands();
        printLine();
    }
    
    private static boolean checkCommandError(String line){
        String [] words = line.split(" ",0);
        
        try {
            if ( !Arrays.asList(commands).contains( words[0].toLowerCase() ) ) {
                throw new DukeException();
            }
            return false;
        } catch ( DukeException e ) {
            response("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            return true;
        }
    }
    
    private static boolean checkMissingDescriptionError(String line){
        String [] missingDescription = {"todo","event","deadline","done","delete"};
        String [] words = line.split(" ",0);
        
        try {
            if ( Arrays.asList(missingDescription).contains( line.toLowerCase() )){
                throw new DukeException();
            }
            return false;
        } catch ( DukeException e ){
            if ( line != "done" && line != "delete" ){
                response("☹ OOPS!!! The description of a " + line + " cannot be empty.");
            } else {
                response("☹ OOPS!!! Please indicated which item you wish to set to " + line + ". You can use the list command to display the items in the list.");
            }
            return true;
        }
    }
    
    private static void done(ArrayList<Task> Tasks, String line){
        try {
            int x = Integer.parseInt(line.replaceFirst("done ",""));
            Tasks.get(x-1).markAsDone();
            response("Nice! I've marked this task as done:\n\t\t" + Tasks.get(x-1).printTask());
        } catch (IndexOutOfBoundsException e) {
            int x = Integer.parseInt(line.replaceFirst("done ",""));
            response("☹ OOPS!!! Task " + x + " does not exist the list. (Size of list: " + Tasks.size() + ")" );
        }
    }
    
    private static void delete(ArrayList<Task> Tasks, String line){
        try {
            int x = Integer.parseInt(line.replaceFirst("delete ", ""));
            System.out.println(x);
            delResponse(Tasks.get(Tasks.size()-1).printTask(),Tasks.size()-1);
            Tasks.remove(x-1);
        } catch (IndexOutOfBoundsException e){
                int x = Integer.parseInt(line.replaceFirst("done ",""));
                response("☹ OOPS!!! Task " + x + " does not exist the list. (Size of list: " + Tasks.size() + ")" );
        }
    }
    
    private static void list(ArrayList<Task> Tasks, String line){
        try {
            printTasks(Tasks);
        } catch (DukeException e) {
            response( "☹ OOPS!!! There is no task on your list. Please add a new task to the system." );
        }
    }
    
    private static void createTodo(ArrayList<Task> Tasks, String line){
        Tasks.add(new Todo( line.replaceFirst("todo ", "")));
        addResponse(Tasks.get(Tasks.size()-1).printTask(),Tasks.size());
    }
    
    private static void createEvent(ArrayList<Task> Tasks, String line){
        try {
            String words[] = line.replaceFirst("event ", "").split("/at",2);
            Tasks.add(new Event(words[0],convertDate(words[1])));
            addResponse(Tasks.get(Tasks.size()-1).printTask(),Tasks.size());
        } catch (ArrayIndexOutOfBoundsException e){
            response("☹ OOPS!!! Event description needs to include a '/at' followed by the date.");
        } catch (ParseException e) {
            response("☹ OOPS!!! Please include a valid date description after '/at'.");
        } catch (DukeException e){
            response("☹ OOPS!!! Please include a date description after '/at'.");
        }
    }            
    
    private static void createDeadline(ArrayList<Task> Tasks, String line){
        try {
            String words [] = line.replaceFirst("deadline ", "").split("/by ",2);
            Tasks.add(new Deadline(words[0],convertDate(words[1])));
            addResponse(Tasks.get(Tasks.size()-1).printTask(),Tasks.size());    
        } catch (ArrayIndexOutOfBoundsException e){
            response("☹ OOPS!!! Deadline description needs to include a '/by' followed by the date.");
        } catch (ParseException e) {
            response("☹ OOPS!!! Please include a valid date description after '/by'.");
        } catch (DukeException e){
            response("☹ OOPS!!! Please include a date description after '/by'.");
        }
    }
    
    private static Date convertDate(String stringdate) throws DukeException, ParseException{
        if ( stringdate.equals("") ){
            throw new DukeException();
        }
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(stringdate);  
        return date1;
    }
    
    
 }
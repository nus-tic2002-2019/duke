
package duke;

import java.util.*;
import java.lang.Exception;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Duke{
    
    public static void main(String[] args) {
        welcome();
        String line = "";
        String words[];
        Scanner in = new Scanner(System.in);
        ArrayList<Task> Tasks = new ArrayList <>();
        int index = 0;
        
        while ( true ){
            line = in.nextLine();
            
            if ( line.toLowerCase().equals("bye") ){
                exit();
                break;
            }
            
            try {
                commandChecker(line);
            } catch ( DukeException e ) {
                response("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                continue;
            }
            
            try {
                missingDescription(line);
            } catch ( DukeException e ){
                if ( line != "done" ){
                    response("☹ OOPS!!! The description of a " + line + " cannot be empty.");
                    continue;
                } else {
                    response("☹ OOPS!!! The description of a done cannot be empty.");
                    continue;
                }
            }
            
            try {
                if ( line.startsWith("done")){
                    int x = Integer.parseInt(line.replaceFirst("done ",""));
                    Tasks.get(x-1).markAsDone();
                    response("Nice! I've marked this task as done:\n\t\t" + Tasks.get(x-1).printTask());
                    continue;
                }
            } catch (IndexOutOfBoundsException e) {
                int x = Integer.parseInt(line.replaceFirst("done ",""));
                response("☹ OOPS!!! Task " + x + " does not exist the list. (Size of list: " + Tasks.size() + ")" );
            }

            try {
                if ( line.startsWith("list")){
                    printTasks(Tasks);
                    continue;
                }
            } catch (DukeException e) {
                response( "☹ OOPS!!! There is no task on your list. Please add a new task to the system." );
            }
            
            if ( line.startsWith("todo")){
                Tasks.add(new Todo( line.replaceFirst("todo ", "")));
                addResponse(Tasks.get(Tasks.size()-1).printTask(),Tasks.size());
                continue;
            } 
            
            try {
                if  ( line.startsWith("event")){
                    words = line.replaceFirst("event ", "").split("/at",2);
                    checkDate(words[1]);
                    Tasks.add(new Event(words[0],words[1]));
                    addResponse(Tasks.get(Tasks.size()-1).printTask(),Tasks.size());
                    continue;
                }
            } catch (ArrayIndexOutOfBoundsException e){
                response("☹ OOPS!!! Event description needs to include a '/at' followed by the date.");
            } catch (DukeException e) {
                response("☹ OOPS!!! Please include a date description after '/at'.");
            }
            
            try {
                if  ( line.startsWith("deadline")){
                    words = line.replaceFirst("deadline ", "").split("/by ",2);
                    checkDate(words[1]);
                    Tasks.add(new Deadline(words[0],words[1]));
                    addResponse(Tasks.get(Tasks.size()-1).printTask(),Tasks.size());
                    continue;
                }
            } catch (ArrayIndexOutOfBoundsException e){
                response("☹ OOPS!!! Deadline description needs to include a '/by' followed by the date.");
            } catch (DukeException e) {
                response("☹ OOPS!!! Please include a date description after '/by'.");
            }
        }
    }
    
    public static void printTasks(ArrayList<Task> Tasks) throws DukeException{
        printLine();
        for ( int i = 0; i < Tasks.size() ; i++ ){
            System.out.printf("\t%d. %s\n",i+1,Tasks.get(i).printTask());
        }
        printLine();
        if ( Tasks.size() < 1 ) {
            throw new DukeException();
        }
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
    
    private static void commandChecker(String line) throws DukeException{
        String [] commands = {"bye","done","list","todo","event","deadline"};
        String [] words = line.split(" ",0);
            
        if ( !Arrays.asList(commands).contains( words[0].toLowerCase() ) ) {
            throw new DukeException();
        }
    }
    
    private static void missingDescription(String line) throws DukeException{
        String [] missingDescription = {"todo","event","deadline","done"};
        String [] words = line.split(" ",0);
        
        if ( Arrays.asList(missingDescription).contains( line.toLowerCase() )){
            throw new DukeException();
        }
    }
    
    private static void checkDate(String line) throws DukeException{
        if ( line == "" ){
            throw new DukeException();
        }
    }
}

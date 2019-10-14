package duke;

import java.util.Scanner;
import java.util.ArrayList;
public class Duke{
    
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        welcome();
        String line = "";
        String words[];
        Scanner in = new Scanner(System.in);
        ArrayList<Task> Tasks = new ArrayList <>();
        int index = 0;
        
        while ( true ){
            line = in.nextLine();
            
            if ( line.startsWith("done ")){
                int x = Integer.parseInt(line.replaceFirst("done ",""));
                if ( x <= index ) {
                    Tasks.get(x-1).markAsDone();
                    response("Nice! I've marked this task as done:\n\t\t" + Tasks.get(x-1).printTask());
                    continue;
                }
            }
            
            if ( line.toLowerCase().equals("bye") ){
                exit();
                break;
            }

            if ( line.startsWith("list")){
                printTasks(Tasks);
                continue;
            }
            
            if ( line.startsWith("todo ")){
                Tasks.add(new Todo(line.replaceFirst("todo ", "")));
                addresponse(Tasks.get(Tasks.size()-1).printTask(),Tasks.size());
                continue;
            } 
            
            if  ( line.startsWith("event ")){
                words = line.replaceFirst("event ", "").split("/at",0);
                Tasks.add(new Event(words[0],words[1]));
                addresponse(Tasks.get(Tasks.size()-1).printTask(),Tasks.size());
                continue;
            } 
            
            if  ( line.startsWith("deadline ")){
                words = line.replaceFirst("deadline ", "").split("/at",0);
                Tasks.add(new Deadline(words[0],words[1]));
                addresponse(Tasks.get(Tasks.size()-1).printTask(),Tasks.size());
                continue;
            }
        }
    }
    
    public static void printTasks(ArrayList<Task> Tasks){
        printLine();
        for ( int i = 0; i < Tasks.size() ; i++ ){
            System.out.printf("\t%d. %s\n",i+1,Tasks.get(i).printTask());
        }
        printLine();
    }
    
    public static void printLine(){
        System.out.println("\t____________________________________________________________");
    }
    
    public static void welcome(){
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
    
    public static void addresponse(String line, int size){
        printLine();
        System.out.printf("\tGot it. I've added this task:\n\t  %s\n\tNow you have %d tasks in the list\n",line,size);
        printLine();
    }
}

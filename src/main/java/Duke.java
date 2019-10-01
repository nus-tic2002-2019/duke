package duke;

import java.util.Scanner;

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
        Scanner in = new Scanner(System.in);
        Todo[] Tasks = new Todo[100];
        int index = 0;
        
        while ( true ){
            line = in.nextLine();
            if (line.contains(" ")){
                String[] words = line.split(" ",0);
                if ( words[0].equals("done")){
                    int x = Integer.parseInt(words[1]);
                    if ( x <= index ) {
                        Tasks[x-1].markAsDone();
                        response("Nice! I've marked this task as done:\n\t\t" + printTask(Tasks[x-1]));
                        continue;
                    }
                }
            }
            if ( line.toLowerCase().equals("bye") ){
                exit();
                break;
            }

            if ( line.toLowerCase().equals("list")){
                printTasks(Tasks);
            } else {  
                if ( index < 100 ){
                    Todo t = new Todo(line);
                    Tasks[index] = t;
                    index += 1;
                    response("added:" + line);
                } else {
                    response("You cannot add any more items. You have exceeded the maximum numer of items on the list.");
                }
                
            }
        }
    }
    
    public static void printTasks(Todo Tasks[]){
        printLine();
        for ( int i = 0; Tasks[i] != null; i++ ){
            System.out.printf("\t%d. %s\n",i+1,printTask(Tasks[i]));
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
    
    public static String printTask(Todo t){
        return("[" + t.getStatusIcon() + "]" + t.getDescription());
    }
}

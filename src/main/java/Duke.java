public class Duke {
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
        
        while ( true ){
            line = in.nextLine();
            if ( line.toLowerCase().equals("bye") ){
                exit();
                break;
            }
            response(line);
        }
        
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
}

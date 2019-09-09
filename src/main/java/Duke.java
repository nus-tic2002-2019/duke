public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
                System.out.println("Hello from\n" + logo);
        welcome();
        
        String line = "";
        Scanner in = new Scanner(System.in);
        String[] list = new String [100];
        int index = 0;
        
        while ( true ){
            line = in.nextLine();
            if ( line.toLowerCase().equals("bye") ){
                exit();
                break;
            }
            
            if ( line.toLowerCase().equals("list")){
                printList(list);
            } else {  
                if ( index < 100 ){
                    list[index]=line;
                    index += 1;
                    response("added:" + line);
                } else {
                    response("You cannot add any more items. You have exceeded the maximum numer of items on the list.");
                }
                
            }
        }
    }
    
    public static void printList(String list[]){
        printLine();
        for ( int i = 0; list[i] != null; i++ ){
            System.out.printf("\t%d. %s\n",i+1,list[i]);
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
}


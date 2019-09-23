public class Message {
    public static void welcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\t--------------------------------------------------");
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("\t--------------------------------------------------");
    }
    public static void byeMessage(){
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t--------------------------------------------------");
    }
    public static void acknowledgeMessage(Task[] t, Integer taskNo){
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + t[taskNo-1]);
        System.out.println("\tNow you have " + taskNo + " tasks in the list");
        System.out.println("\t--------------------------------------------------");
    }
    public static void doneMessage(Task[] t, Integer index){
        t[index - 1].taskDone();
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tNice! I marked this task as done");
        System.out.println("\t" + t[index-1]);
        System.out.println("\t--------------------------------------------------");
    }
    public static void listMessage(Task[] t, Integer taskNo){
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < taskNo ; i++) {
            System.out.println("\t" + (i+1) + "." + t[i]);
        }
        System.out.println("\t--------------------------------------------------");
    }
}

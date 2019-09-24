import java.util.ArrayList;

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
    public static void acknowledgeMessage(ArrayList<Task> t, Integer index){
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + t.get(index-1)); //t[taskNo-1])
        System.out.println("\tNow you have " + index + " tasks in the list");
        System.out.println("\t--------------------------------------------------");
    }
    public static void doneMessage(ArrayList<Task> t, Integer index){
        t.get(index - 1).taskDone();
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tNice! I marked this task as done");
        System.out.println("\t" + t.get(index-1));
        System.out.println("\t--------------------------------------------------");
    }
    public static void deleteMessage(ArrayList<Task> t, Integer index){
        //t[index - 1].taskDone();
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tNoted! I have removed this task.");
        System.out.println("\t" + t.get(index-1));
        System.out.println("\tNow you have " + (t.size()-1) + " tasks in the list");
        System.out.println("\t--------------------------------------------------");
        t.remove(index-1);
    }
    public static void listMessage(ArrayList<Task> t, Integer index){
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < index ; i++) {
            System.out.println("\t" + (i+1) + "." + t.get(i));
        }
        System.out.println("\t--------------------------------------------------");
    }
}

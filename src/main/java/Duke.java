import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String inData = null;
        ArrayList<Task> arlist = new ArrayList<Task>( );
        Task T = null;
        int count = 0;

        while (!Objects.equals(inData, "bye")){
            Scanner scan = new Scanner(System.in);
            inData = scan.nextLine();
            if(Objects.equals(inData, "list")){
                for (int i = 0; i < count; i++) {
                    System.out.println((i+1) + "." + arlist.get(i).toString());
                }
            }
            else if(inData.contains("done")){
                int Marked = (Integer.parseInt(inData.substring(inData.indexOf("done")+5, inData.length())));
                T = arlist.get(Marked-1);
                T.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n" + T.toString());
            }
            else if(!Objects.equals(inData, "list")){
                    if(!Objects.equals(inData, "bye")) {
                            if(!Objects.equals(inData.substring(0,4), "done")) {
                                if(inData.contains("ToDo")){
                                    String itemName = inData.substring(4, inData.length());
                                    ToDo To = new ToDo(itemName);
                                    count++;
                                    arlist.add(To);
                                    System.out.println("Got it. I've added this task: \n" + To.toString() + "\nNow you have " + count + " tasks in list.");

                                }
                                else if(inData.contains("Deadline")){

                                }

                        }
                }

            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}



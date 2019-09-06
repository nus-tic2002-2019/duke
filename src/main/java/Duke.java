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
        ArrayList<String> arlist = new ArrayList<String>( );

        int count = 0;
        while (!Objects.equals(inData, "bye")){
            Scanner scan = new Scanner(System.in);
            inData = scan.nextLine();
            if(Objects.equals(inData, "list")){
                for (int i = 0; i < count; i++) {
                    Task T = new Task(arlist.get(i));
                    System.out.println((i+1) + "." + T.toString());
                }
            }
            else if(Objects.equals(inData.substring(0,4), "done")){
                int Marked = (Integer.parseInt(inData.substring(inData.indexOf("done")+5, inData.length())));
                Task T = new Task(arlist.get(Marked-1));
                T.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n" + T.toString());
            }
            else if(!Objects.equals(inData, "list")){
                    if(!Objects.equals(inData, "bye")) {
                            if(!Objects.equals(inData.substring(0,4), "done")) {
                            System.out.println("added:" + inData);
                            arlist.add(inData);
                            count++;
                        }
                }

            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}



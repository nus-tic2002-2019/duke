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
        while (!Objects.equals(inData, "bye")){
            int count = 1;
            Scanner scan = new Scanner(System.in);
            inData = scan.nextLine();
            if(Objects.equals(inData, "list")){
                for (int i = 0; i < count; i++) {
                    System.out.println(count + "." + arlist.get(count - 1));
                    count++;
                }
            }
            if(!Objects.equals(inData, "list")){
                if(!Objects.equals(inData, "bye")) {
                    System.out.println("added:" + inData);
                    arlist.add(inData);
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}



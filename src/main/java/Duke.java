import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String separateLine="_____________________________________________";
        System.out.println(separateLine);
        System.out.println("Hello! I am Duke\nWhat can I do for you?");
        System.out.println(separateLine);

        String line="";
        //String[] list=new String[100];
        List<String> list = new ArrayList<String>();
        Scanner in =new Scanner(System.in);
        while(!( line = in.nextLine()).equals("bye")) {
            if(!line.equals("list")) {
                System.out.println(separateLine + "\n" +"Added: "+ line + "\n" + separateLine);
                list.add(line);
            }
            else
            {
                System.out.println(separateLine);
                //System.out.println(list.toString());
                //Stream.of(list.toString()).forEach(System.out::println);
                for(int i=1;i<=list.size();i++)
                {
                    System.out.println(i+". "+list.get(i-1));
                }
                System.out.println(separateLine);
            }
        }
        if (line.equals("bye")) {
            System.out.println(separateLine+"\nBye. Hope to see you again soon!");
            System.exit(0);
        }


    }
}

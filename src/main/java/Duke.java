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


//        String line="";
//        //String[] list=new String[100];
//        List<String> list = new ArrayList<String>();
//        Scanner in =new Scanner(System.in);
//        while(!( line = in.nextLine()).equals("bye")) {
//            if(!line.equals("list")) {
//                System.out.println(separateLine + "\n" +"Added: "+ line + "\n" + separateLine);
//                list.add(line);
//            }
//            else
//            {
//                System.out.println(separateLine);
//                //System.out.println(list.toString());
//                //Stream.of(list.toString()).forEach(System.out::println);
//                for(int i=1;i<=list.size();i++)
//                {
//                    System.out.println(i+". "+list.get(i-1));
//                }
//                System.out.println(separateLine);
//            }
//        }


        String line="";
        List<Task> list = new ArrayList<Task>();
        Scanner in =new Scanner(System.in);
        while(!( line = in.nextLine()).equals("bye")) {
            if(line.equals("list")) {
                System.out.println(separateLine+"\n"+"Here are the tasks in your list:");
                //System.out.println(list.toString());
                //Stream.of(list.toString()).forEach(System.out::println);
                for(int i=1;i<=list.size();i++)
                {
                    System.out.println(i+". "+"["+list.get(i-1).getStatusIcon()+"] "+list.get(i-1).getDescription());
                }
                System.out.println(separateLine);
            }
            else if (line.contains("done"))
            {
                int idx=Integer.parseInt(line.substring(5));
                list.get(idx-1).markAsDone();
                System.out.println(separateLine + "\n" +"Nice! I've marked this task as done");
                System.out.println("["+list.get(idx-1).getStatusIcon()+"] "+list.get(idx-1).getDescription());
            }
            else{
                System.out.println(separateLine + "\n" +"Added: "+ line + "\n" + separateLine);
                list.add(new Task(line));
            }
        }
        if (line.equals("bye")) {
            System.out.println(separateLine+"\nBye. Hope to see you again soon!");
            System.exit(0);
        }


    }
}

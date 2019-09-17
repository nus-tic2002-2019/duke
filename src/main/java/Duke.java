import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +" What can I do for you?");

        String[] list = new String[100];
        String line;
        Scanner in = new Scanner(System.in);
        while(true) {
            line = in.nextLine();
            if (line.equals("list")) PrintList(list);
            else if (line.equals("blah")) System.out.println("blah");
            else if (line.equals("bye"))
            {
                System.out.println("Bye. Hope to see you again soon");
                break;
            }
            else StoreList(list, line);
        }

    }

    public static String[] StoreList(String[] list, String input) {
        //int count = list.length;
        int count = 0;
        for(int i = 0; i < list.length; i++)
        {
            if(null != list[i]) count++;
        }
        list[count] = input;
        System.out.println("added:" + input);
        return list;
    }

    public static void PrintList(String[] list)
    {
        int size = 0;
        for(int i = 0; i < list.length; i++)
        {
            if(null != list[i]) size++;
        }

        for(int i=0;i<size;i++)
        {
            System.out.println(i + 1 + "." + list[i]);
        }
    }
}

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

        //String[] list = new String[100];
        Task[] task = new Task[100];
        String line;
        Scanner in = new Scanner(System.in);
        while(true) {
            line = in.nextLine();
            if (line.equals("list")) PrintTask(task);
            else if (line.equals("blah")) System.out.println("blah");
            else if (line.equals("bye"))
            {
                System.out.println("Bye. Hope to see you again soon");
                break;
            }
            else if (line.length()>3&&line.substring(0,4).equalsIgnoreCase("done"))
            {
                int number = Integer.parseInt(line.substring(5));
                task[number-1].markAsDone();
                System.out.println("Nice! I have marked this task as done:");
                System.out.println(task[number-1].getStatusIcon()+task[number-1].getDescription());

            }
            else StoreTask(task, line);
        }


        Task t = new Task("read book");
        t.markAsDone();

    }

    public static Task[] StoreTask(Task[] task, String input) {
        //int count = list.length;
        int count = 0;
        for(int i = 0; i < task.length; i++)
        {
            if(null != task[i]) count++;
        }
        Task t = new Task(input);
        task[count] = t;
        System.out.println("added:" + input);
        return task;
    }


    public static void PrintTask(Task[] task)
    {
        int size = 0;
        for(int i = 0; i < task.length; i++)
        {
            if(null != task[i]) size++;
        }

        for(int i=0;i<size;i++)
        {
            System.out.println(i + 1 + "." + task[i].getStatusIcon()+task[i].getDescription());
        }
    }
}

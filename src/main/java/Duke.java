import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontal_line = ("____________________________________\n");
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(horizontal_line + "Hello I'm Duke\nWhat can I do for you? \n" + horizontal_line);

        Scanner in = new Scanner(System.in);
        String line;
        line = in.nextLine();

        // Level 2
        Task[] list = new Task[100];
        int count = 0;
        while (!line.equals("bye")) {
             if(line.equals("list")) {
                 System.out.println(horizontal_line + '\n' + "Here are the tasks in your list:");
                 for (int i = 0; i < count ; i++) {
                     System.out.println(i + 1 + "." + '[' + list[i].getStatusIcon() + "] " + list[i].description);
                 }
                 System.out.println(horizontal_line);
             }
             else if ((line.substring(0,4)).equals("done")){
                 int position = Integer.parseInt(line.substring(5));
                 list[position - 1].setDone();
                 System.out.println( horizontal_line + "Nice! I've marked this task as done: " );
                 System.out.println('[' + list[position -1].getStatusIcon() + "] " +
                         list[position -1].description + '\n' + horizontal_line);
             }
             else
             {
                 Task add_task = new Task (line);
                 list[count] = add_task;
                 System.out.println(horizontal_line + "Added: " + list[count].description + '\n' + horizontal_line);
                 count++;
             }
             line = in.nextLine();
        } System.out.println(horizontal_line + "Bye. Hope to see you again soon!" +'\n'+ horizontal_line);
    }
}

//public class Task {

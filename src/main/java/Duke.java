import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        Task[] line = new Task[100];
        String input = "";
        int count = 0;
        while (!input.equals("bye")) //if string doesn't equal to bye
        {
            input = sc.nextLine();

            if (input.equals("list"))
            {

                for (int i = 0; i < count; i++)
                {
                    System.out.println(i+1 +". " + line[i]);
                }
            }
            else {

                line[count] = new Task(input,false);
                System.out.println("added: " + line[count]);
                count++;
            }


        }

        System.out.println("Bye. Hope to see you again soon !");
    }
}

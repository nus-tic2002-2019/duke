import java.sql.SQLOutput;
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
            String[] arrOfString = input.split(" ");
            if (input.equals("list"))
            {

                for (int i = 0; i < count; i++)
                {
                    System.out.println(i+1 + ". " + line[i]);
                }
            }
            else if (arrOfString[0].equals("done"))
            {
                int index = Integer.parseInt(arrOfString[1]);
                line[index-1].markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("\t["+line[index-1].getStatusIcon() + "] "+ line[index-1]);
            }

            else if (arrOfString[0].equals("todo"))
            {
                String replaceString = input.replace("todo ", "");
                line[count] = new Todo(replaceString, false);
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + line[count]);
                count ++;
                System.out.println("Now you have " + count + " tasks in the list.");


            }

        }

        System.out.println("Bye. Hope to see you again soon !");
    }
}

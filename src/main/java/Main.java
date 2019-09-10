import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String logo = " __        _        \n"
                + "|  _ \\ _   | | __ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| || | || |   <  __/\n"
                + "|_/ \\,||\\\\___|\n";

        // TODO Auto-generated method stub
        Task list[] = new Task[100];
        int counter = 0;
        String input;

        Scanner sc = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        input = sc.nextLine();

        while(!input.equalsIgnoreCase("bye")) {
            System.out.println("____________________________________________________________");

            if(input.equalsIgnoreCase("list")) {
                for(int i = 0; i < counter; i++) {
                    System.out.println(i + 1 + ".[" + list[i].getStatusIcon() + "] " + list[i].getDescription());
                }
            }

            else if (input.substring(0,4).equalsIgnoreCase("done")) {
                int taskNo = Integer.parseInt(input.substring(5));
                list[taskNo-1].markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + list[taskNo-1].getStatusIcon() + "] " + list[taskNo-1].getDescription());
            }

            else if (input.equalsIgnoreCase("blah")) {
                System.out.println("blah");
            }

            else {
                list[counter] = new Task(input);
                counter++;

                System.out.println("added: " + input);
            }
            System.out.println("____________________________________________________________");
            input = sc.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

}


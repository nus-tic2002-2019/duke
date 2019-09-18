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
                    System.out.println(i + 1 + "."+list[i].toString());
                }
            }
            else if (input.substring(0,4).equalsIgnoreCase("done")) {
                int taskNo = Integer.parseInt(input.substring(5));
                list[taskNo-1].markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + list[taskNo-1].getStatusIcon() + "] " + list[taskNo-1].getDescription());
            }
            else if (input.substring(0,4).equalsIgnoreCase("todo")) {
                list[counter] = new Todo(input.substring(5));
                System.out.println("Got it. I've added this task: ");
                System.out.println(list[counter].toString());
                counter++;
                System.out.println("Now you have todo borrow book" + counter + " tasks in the list.");
            }
            else if (input.substring(0,8).equalsIgnoreCase("deadline")) {
                list[counter] = new Deadline(input.substring(9, input.indexOf("/")-1),input.substring(input.indexOf("/")+3));
                System.out.println("Got it. I've added this task: ");
                System.out.println(list[counter].toString());
                counter++;
                System.out.println("Now you have " + counter + " tasks in the list.");
            }
            else if (input.substring(0,5).equalsIgnoreCase("event")) {
                list[counter] = new Event(input.substring(6, input.indexOf("/")-1),input.substring(input.indexOf("/")+3));
                System.out.println("Got it. I've added this task: ");
                System.out.println(list[counter].toString());
                counter++;
                System.out.println("Now you have " + counter + " tasks in the list.");
            }

            else if (input.equalsIgnoreCase("blah")) {
                System.out.println("blah");
            }
            System.out.println("____________________________________________________________");
            input = sc.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

}


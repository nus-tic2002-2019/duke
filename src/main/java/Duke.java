import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner myObj = new Scanner(System.in);
        String userInput;
        String[] arr01 = new String[100];
        int index = 0;
        userInput=myObj.nextLine();
        while(!userInput.equals("bye")){
            if (userInput.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + arr01[i]);
                }
                userInput=myObj.nextLine();
            }
            else {
                System.out.println("Added: " + userInput);
                arr01[index] = userInput;
                index++;
                userInput=myObj.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}

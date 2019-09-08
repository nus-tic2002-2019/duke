import java.util.Scanner;

public class InteractionsManager {
    public void start() {
        // say welcome
        System.out.println("     Hello! I'm Duke\n" +
                "     What can I do for you?\n");
        // init some stuff
        Operations operations = new Operations();

        while(true) {
            // user gives command
            String userCommand = getUserCommand();

            if (userCommand.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            else if (userCommand.equals("list")){
                operations.printList();
            }
            else{
                // add task to list
                operations.add(userCommand);
            }

        }
    }

    private void getTaskIndexFromDoneCommand(String userCommand) {
    }
    private String getUserCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}

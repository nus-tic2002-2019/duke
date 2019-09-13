import java.util.Scanner;

public class InteractionsManager {
    public void start() {
        // say welcome
        System.out.println("Test save only");
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
            // expects input in the format "done 4"
            else if (userCommand.startsWith("done") ){
                // grab the int the user input
                String lastValue = userCommand.substring(userCommand.length()-1);
                int targetToSetDone = Integer.parseInt(lastValue);
                // mark the tasks[integer] as done
                operations.setDone(targetToSetDone);


            }

            else{
                // add task to list
                operations.add(userCommand);
            }

        }
    }
    private String getUserCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}

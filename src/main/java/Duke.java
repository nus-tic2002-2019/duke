import java.util.Scanner;

public class Duke {
    private static Task todoList = new Task(10);
    private static void echo() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while(!line.equals("bye")){
            // list
            if (line.equals("list")) {
                int i = 1;
                if (todoList.getItemCount() == 0) {
                    System.out.println("Your list is empty.");
                } else {
                    boolean[] status = todoList.getStatus();
                    for (String item : todoList.getTask()) {
                        if (item != null) {
                            System.out.println(i + "." + "[" + (status[i - 1] ? "✓" : "✗") + "] " + item);
                            i++;
                        }
                    }
                }
            // done
            } else if (line.length() >= 4 && line.substring(0, 4).equals("done")) {
                try {
                    int itemNumber = Integer.parseInt(line.substring(5, line.length())) - 1;
                    if (todoList.doTask(itemNumber) && todoList.getItemCount() > itemNumber) {
                        // TODO: change task to an array of object class instead?
                        String[] taskList = todoList.getTask();
                        boolean[] taskStatus = todoList.getStatus();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("[" + (taskStatus[itemNumber] ? "✓" : "✗") + "] " + taskList[itemNumber]);
                    } else {
                        System.out.println("Unable to mark task as done.");
                    }
                } catch (NumberFormatException error) {
                    System.out.println("Unable to mark task as done.");
                } catch (StringIndexOutOfBoundsException error) {
                    System.out.println("Unable to mark task as done.");
                } catch (ArrayIndexOutOfBoundsException error) {
                    System.out.println("Unable to mark task as done.");
                }
            // add task
            } else {
                System.out.println(line);
                if (!todoList.setTask(line)) {
                    System.out.println("Your task list is full, unable to add more task.");
                }
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        echo();
    }
}

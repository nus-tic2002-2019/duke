import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while (true) {
            String input = read.nextLine();

            if (input.contains("list")) {
                List.printList();
            }

            if (input.contains("bye")) {
                Bye.printBye(); //System.exit(0)
            }

            if (input.contains("done")) {
                String[] split = input.split(" ");
                int doneNum = Integer.parseInt(split[1]); //storing list number
                doneNum = doneNum - 1;
                if (doneNum <= Task.checkBox.size()) { //only if number is in the numbered list
                    Task.markAsDone(); //change isDone to true
                    Task.changeMark(doneNum); //add tick/cross to arraylist
                }
                System.out.println("Nice! I've marked this task as done:" + System.lineSeparator());
                System.out.println((doneNum + 1) + ". " + "[" + Task.checkBox.get(doneNum) + "] " + List.userList.get(doneNum));
                //System.out.println(doneNum);
            }

            if (input.contains("todo")) {
                String[] todoTask = input.split(" ", 2); //separate_todo

                List.enterList(todoTask[1]); //place input into arraylist

                Todo.fillTaskType(); //mark task as _todo

                System.out.println("Got it! Task added:" + System.lineSeparator());
                //System.out.println((doneNum + 1) + ". " + "[" + Task.checkBox.get(doneNum) + "] " + List.userList.get(doneNum));
            }

            if (input.contains("deadline")){
                Deadline.splitDead(input); //separate_deadline
                Deadline.toList();
                Deadline.fillTaskType();
                //System.out.println(Arrays.toString(split));
            }

            if (input.contains("event")){
                String[] eventTask = input.split(" ", 2); //separate_event
                Event.splitEvent(input);
                Event.toList();
                Event.fillTaskType();
            }
        }
    }
}

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
            if (input.contains("List")) {
                List.printList();
            }

            if (input.contains("Bye")) {
                Bye.printBye();
            }

            if (input.contains("done")) {
                String[] split = input.split(" ");
                int doneNum = Integer.parseInt(split[1]); //storing list number to checkBox later
                doneNum = doneNum - 1;
                if (doneNum <= Task.checkBox.size()) {
                    Task.markAsDone();
                    Task.changeMark(doneNum);
                }
                System.out.println("Nice! I've marked this task as done:" + System.lineSeparator());
                System.out.println((doneNum + 1) + ". " + "[" + Task.checkBox.get(doneNum) + "] " + List.userList.get(doneNum));
                //System.out.println(doneNum);
            }

            if (!input.contains("List") && !input.contains("Bye") && !input.contains("done")) {
                List.enterList(input);
            }
        }
    }
}

import java.util.Scanner;

public class Duke {
    private static Task todoList = new Task(10);
    private static void echo() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while(!line.equals("bye")){
            if (!line.equals("list")) {
                System.out.println(line);
                if (!todoList.setTask(line)) {
                    System.out.println("Your task list is full, unable to add more task.");
                }
            } else {
                int i = 1;
                for (String item : todoList.getTask()) {
                    if (item != null) {
                        System.out.println(i + ". " + item);
                        i++;
                    }
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

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line;
        Task[] taskList = new Task[10];
        Scanner in = new Scanner(System.in);
        System.out.print("Hello! I'm Duke\n" + "What can I do for you?");
        int index = 0;
        while (true){
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (line.equals("list")) {
                for (int i = 0; i < index; i++){
                    System.out.println(i+1 + ". " + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].description);
                }
            }

            else if (line.contains("done")){
                int numberList = Integer.valueOf(line.substring(5, line.length()));
                Task t = taskList[numberList-1];
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + t.getStatusIcon() + "] " + t.description);

            }

            else{
                System.out.println("added: " + line);
                Task newTask = new Task(line);
                newTask.description = line;
                taskList[index] = newTask;
                index ++;
            }

        }
    }

}


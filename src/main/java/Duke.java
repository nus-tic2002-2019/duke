import java.util.Scanner;
public class Duke {
    private static int counter = 0;
    private static Task[] tasks = new Task[100];

    public static void setTasks(Task description){
        tasks[counter] = description;
        counter++;
    }

    public static String getTasks(){
        String output = "\t____________________________________________________________";
        for(int i=0;i<counter;i++){
            output += "\n\t" + Integer.toString(i+1) + ". " + tasks[i].getStatusIcon() + tasks[i].toString();
        }
        output += "\n\t____________________________________________________________";
        return output;
    }

    public static String markAsDone(int task){
        return tasks[task].setDone();
    }

    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("\t____________________________________________________________" 
        +"\n\tHello! I'm Duke\n\tWhat can I do for you?"
        + "\n\t____________________________________________________________");

        do {
            input = sc.nextLine();
            switch(input.split(" ")[0]){
                case "bye":
                    System.out.println("\t____________________________________________________________" + 
                    "\n\tBye. Hope to see you again soon!" +
                    "\n\t____________________________________________________________");
                    break;
                case "list":
                    System.out.println(getTasks());
                    break;
                case "mark":
                    System.out.println(markAsDone(Integer.parseInt(input.split(" ")[1])-1));
                    break;
                default:
                    setTasks(new Task(input));
                    System.out.println("\t____________________________________________________________\n\t"
                    + "added: "
                    + input
                    + "\n\t____________________________________________________________");
            }
        }
        while(!(input.equals("bye")));
    }
}
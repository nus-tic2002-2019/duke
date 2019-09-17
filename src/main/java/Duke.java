import java.util.Scanner;

public class Duke {
    private static Task[] tasklist = new Task[100];
    private static int taskCount = 0;

    public static void addTask(Task s){
        tasklist[taskCount] = s;
        taskCount++;
    }
    public static void printList(){
      for ( int i = 0; i<taskCount;i++){
        System.out.println(tasklist[i].toString());
      }
    }
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
        int index = 0;

        while(myObj.hasNextLine()){
            userInput = myObj.nextLine();
            String[] split = userInput.split(" ");
            String first = split[0];
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + tasklist[i].toString());
                }
                continue;
            }
            if (userInput.equals("bye")) {
                break;
            }
            else if (first.equals("done")){
              String second = split[1];
              tasklist[Integer.parseInt(second) - 1].markAsDone();
              System.out.println("Nice! I've marked this task as done: ");
              System.out.println(tasklist[Integer.parseInt(second) - 1].getStatusIcon()+" " + tasklist[Integer.parseInt(second) - 1].description);
            }
            else {
                System.out.println("Got it. I've added this task: ");
                if (first.equals("todo")) {
                    addTask(new ToDo(userInput));
                    System.out.println(tasklist[index].toString());
                    index++;
                }
                if (first.equals("deadline")) {
                    String[] split02 = userInput.split("/by");
                    String firstD = split02[0];
                    String secondD = split02[1];
                    addTask(new Deadline(firstD,secondD));
                    System.out.println(tasklist[index].toString());
                    index++;
                }
                if (first.equals("event")) {
                    String[] split03 = userInput.split("/at");
                    String firstE = split03[0];
                    String secondE = split03[1];
                    addTask(new Event(firstE,secondE));
                    System.out.println(tasklist[index].toString());
                    index++;
                }
                System.out.println("Now you have "+index+" tasks in the list.");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}



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
        //String[] arr01 = new String[100];
        //String[] arr02 = new String[100];
        int index = 0;
        //userInput=myObj.nextLine();

        while(myObj.hasNextLine()){
            userInput = myObj.nextLine();
            String[] split = userInput.split(" ");
            String first = split[0];
            if (userInput.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + tasklist[i].toString());
                }
                continue;
                //userInput=myObj.nextLine();
            }
            if (userInput.equals("bye")) {
                break;
            }
            else if (first.equals("done")){
              String second = split[1];
              tasklist[Integer.parseInt(second) - 1].markAsDone();
            }
            else {
                System.out.println("Added: " + userInput);
                //tasklist[index] = userInput;
                addTask(new Task(userInput));
                index++;
                //userInput=myObj.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}



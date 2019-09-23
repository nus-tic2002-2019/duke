import java.util.Scanner;
//import java.util.Arrays;

public class Duke {

    public static void addText(Task[] arr, String newTask, int ctr) {
        System.out.println("Got it. I've added this task:");
        String temp = "";

        arr[ctr] = new Todo(newTask);
        temp = arr[ctr].toString();
        System.out.println(temp + "\nNow you have " + (ctr+1) + " in the list.");
    }

    public static void addText(Task[] arr, String actType, String newTask, int ctr){
        String dateline = "";
        int pos = 0;
        String temp = "";

        if (actType.equals("deadline")) {
            dateline = parseString(newTask, "/by ");
            pos = newTask.indexOf("/by");
            arr[ctr] = new Deadline(newTask.substring(0, pos-1), dateline);
        } else if (actType.equals("event")) {
            dateline = parseString(newTask, "/at ");
            pos = newTask.indexOf("/at");
            arr[ctr] = new Event(newTask.substring(0, pos-1), dateline);
        }
        temp = arr[ctr].toString();
        System.out.println(temp + "\nNow you have " + (ctr+1) + " in the list.");
    }


    public static void listArr(Task[] arr, int ctr) {
        System.out.println("Here are the tasks in your list:");

        int i = 0;
        while (i < ctr) {
            System.out.print ((i+1) + ". ");
            arr[i].print();
            i++;
        }
    }

    public static void markDone(Task[] arr, int taskNo) {
        arr[taskNo-1].isDone();
        System.out.println("Nice! I've marked this task as done:");
        arr[taskNo-1].print();
    }

    private static int taskDone(String userInput) {
        String[] d = userInput.split(" ");
        if (d.length > 2) {
            //throw Exception
        } else {
            return Integer.parseInt(d[1]);
        }
        return 0;
    }

    private static String parseString(String userInput, String delimit) {
        String[] d = userInput.split(delimit);
        if (delimit.equals(" ")) {
            return d[0].toLowerCase();
        } else {
            return d[1];
        }
    }

    public static void main(String[] args) {

        //System.out.println("Hello from\n" + logo);*/
        String userInput;
        String temp;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello, I'm Duke.\nWhat can I do for you?");

        Task[] userArr = new Task[100];
        int i = 0;
        userInput = in.nextLine();
        //temp = userInput.toLowerCase();
        temp = parseString(userInput, " ");

        while (!temp.equals("bye")) {
            switch(temp) {
                case "done":
                    int taskNo = taskDone(userInput);
                    markDone(userArr, taskNo);
                    break;
                case "list":
                    listArr(userArr, i);
                    break;
                case "todo":
                    addText(userArr, userInput.substring(5), i);
                    i++;
                    break;
                case "deadline":
                    addText(userArr, "deadline", userInput.substring(9), i);
                    i++;
                    break;
                case "event":
                    addText(userArr, "event", userInput.substring(6), i);
                    i++;
                    break;
                default:
                    addText(userArr, userInput, "", i);
                    i++;
                    break;
            }

            userInput = in.nextLine();
            temp = parseString(userInput, " ");
        }

        if (userInput.toLowerCase().equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }
}

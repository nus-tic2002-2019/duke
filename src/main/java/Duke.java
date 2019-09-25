import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static String seperatorLine = "___________________________________________\n";
    private static String seperatorLine2 = "________________________________________\n";
    private static int listCount = 0;
    private static String userInput;

    private static String[] listArray = new String[100];
    private static String[] todolistArray = new String[100];

    private static String task_words, by_words = " ";
    private static int task_stringIndex_After_taskWord = 0;
    private static int task_count = 0;
    private static String[] mark = new String[100];

    private static Task t = new Task(userInput); //****
    private static Task[] tasks_addTask = new Task[100];  //*****
    private static int taskCount_addTask = 0;


    private static void input_task() {

        if (userInput.contains("/")) {
            int by_string = userInput.indexOf("/");
            by_words = userInput.substring(by_string + 3);
            task_words = userInput.substring(task_stringIndex_After_taskWord, by_string);

        }
        else if(userInput.contains("bye")){
        }
        else {
            task_words = userInput.substring(task_stringIndex_After_taskWord);
        }
    }

    private static void print_event(){

        System.out.print("   " + seperatorLine2);
        System.out.println("     " + "Got it. I've added this task");
        System.out.println("        " + "[" + t.getStatusIcon() + "]" + todolistArray[task_count]);

        System.out.println("     "+ "Now you have "+ (task_count + 1) + " tasks in the list.");
        System.out.print("   " + seperatorLine2);
        task_count++;
    }

    public static void addTask(Task s) {

        tasks_addTask[taskCount_addTask] = s;
        todolistArray[taskCount_addTask] = tasks_addTask[taskCount_addTask].toString();
        taskCount_addTask++;
    }

    public static void added_Print(){
        System.out.print("   " + seperatorLine2);
        System.out.println("      added:" + userInput);
        System.out.print("   " + seperatorLine2);

        listArray[listCount] = userInput;
        listCount++;
    }

    public static void echo_Added_List(Scanner input) {

        boolean isBye = false;
        boolean isList;
        boolean isDone;
        boolean isToDo;
        boolean isDeadline;
        boolean isEvent;
        int listPrint, listNum = 1, doneNumber = 1;

        while (!isBye) {

            userInput = input.nextLine();
            String[] words = userInput.split(" ");

            task_stringIndex_After_taskWord = userInput.indexOf(" ");

            input_task();

            userInput = words[0];
            isList = userInput.equals("list");
            isBye = userInput.equals("bye");
            isDone = userInput.equals("done");
            isToDo = userInput.equals("todo");
            isDeadline = userInput.equals("deadline");
            isEvent = userInput.equals("event");

            if (isToDo) {
                 addTask(new Todo(task_words));
                 print_event();
            }

            else if (isDeadline) {
                 addTask(new Deadline(task_words, by_words));
                 print_event();
            }

            else if (isEvent) {
                 addTask(new Event(task_words, by_words));
                 print_event();
            }

            else if (isList){
                 int count_todo = task_count;
                 System.out.print("   " + seperatorLine2);

                 for (listPrint = 0; listPrint  < count_todo; listPrint++) {

                     System.out.println("        " + listNum + ". " + "[" + mark[listPrint] + "]"  + todolistArray[listPrint]);
                     listNum++;
                 }
                 listNum = 1;
                System.out.print("   " + seperatorLine2);
            }

            else if(isDone){
                t.markAsDone();

                System.out.print("   " + seperatorLine2);
                System.out.println("     " + "Nice! I've marked this task as done:");
                System.out.print("        " + "[" + t.getStatusIcon() + "] ");
                doneNumber = Integer.parseInt(words[1]) - 1;
//                System.out.println(listArray[doneNumber]);
                System.out.println(todolistArray[doneNumber]);
                mark[doneNumber] = t.getStatusIcon();
                System.out.print("   " + seperatorLine2);
            }

            else if (isBye){

                System.out.print(seperatorLine);
                System.out.println("       " + "Bye. Hope to see you again soon!");
                System.out.println(seperatorLine);
            }

            else {
                added_Print();
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.print(seperatorLine);
        System.out.println("Hello! I'm Duke\n"
                + "What can I do for you");
        System.out.println(seperatorLine);

        Scanner input = new Scanner(System.in);

        Arrays.fill(mark, "\u2718");  //initialize mark array = "x"

        echo_Added_List(input);
    }
}

















//            if(!isList && !isBye && !isDone){
//                Added_Print(input);
////                System.out.print("   " + seperatorLine2);
////                System.out.println("      added:" + userInput);
////                System.out.print("   " + seperatorLine2);
////
////                listArray[listCount] = userInput;
////                listCount++;
//            }
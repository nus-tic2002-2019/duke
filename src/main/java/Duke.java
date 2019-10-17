import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static String seperatorLine = "___________________________________________\n";
    private static String seperatorLine2 = "________________________________________\n";
    private static int listCount = 0;
    private static String userInput;

//    private static String[] listArray = new String[100];
    private static ArrayList<String> listArray = new ArrayList<>();
//    private static String[] todolistArray = new String[100];
    private  static ArrayList<String> todolistArray = new ArrayList<>();

    private static String task_words, by_words = " ";
    private static int task_stringIndex_After_taskWord = 0;
    private static int task_count = 0;
    private static int del_task_number = 0;

    private static String[] mark = new String[100];
    private static String[] words = new String[100];

    private static Task t = new Task(userInput); //****

    private static Task[] tasks_addTask = new Task[100];  //*****
//    private static ArrayList<Task> tasks_addTask = new ArrayList<>();  //*****

    private static int taskCount_addTask = 0;


    private static void input_task(){


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
//           System.out.println("        " + "[" + t.getStatusIcon() + "]" + todolistArray[task_count] );
        System.out.println("        " + "[" + t.getStatusIcon() + "]" + todolistArray.get(task_count) );


        System.out.println("     "+ "Now you have "+ (task_count + 1) + " tasks in the list.");
        System.out.print("   " + seperatorLine2);
        task_count++;
    }

//    private static void print_delete_event(){
//
//        System.out.print("   " + seperatorLine2);
//        System.out.println("     " + "Noted. I've removed this task");
//        System.out.println("        " + "[" + t.getStatusIcon() + "]" + todolistArray.get(del_task_number) );
//        System.out.println("     "+ "Now you have "+ (task_count-1 ) + " tasks in the list.");
//        System.out.print("   " + seperatorLine2);
//
//        task_count--;
//    }

    public static void addTask(Task s) {

        tasks_addTask[taskCount_addTask] = s;
//        tasks_addTask.add(s);
//        todolistArray[taskCount_addTask] = tasks_addTask[taskCount_addTask].toString();
        todolistArray.add(tasks_addTask[taskCount_addTask].toString());
        taskCount_addTask++;
    }


    public static void deleteTask(int del_task_number) {
        del_task_number--;
        String remove_task = todolistArray.get(del_task_number);
        print_delete_event();

        todolistArray.remove(remove_task);
    }

    public static void doneTask(int done_task_number) {
        t.markAsDone();

        System.out.print("   " + seperatorLine2);
        System.out.println("     " + "Nice! I've marked this task as done:");
        System.out.print("        " + "[" + t.getStatusIcon() + "] ");
        done_task_number = Integer.parseInt(words[1]) - 1;
//           System.out.println(listArray[doneNumber]);
//           System.out.println(todolistArray[doneNumber]);

        System.out.println(todolistArray.get(done_task_number));

        mark[done_task_number] = t.getStatusIcon();
        System.out.print("   " + seperatorLine2);
    }

    public static void added_Print(){
        System.out.print("   " + seperatorLine2);
        System.out.println("      added:" + userInput);
        System.out.print("   " + seperatorLine2);

//        listArray[listCount] = userInput;
        listArray.add(userInput);
        listCount++;
    }

    public static void echo_Added_List(Scanner input) throws DukeException{

        boolean isBye = false;
        boolean isList;
        boolean isDone;
        boolean isToDo;
        boolean isDeadline;
        boolean isEvent;
        boolean isDelete;
        int listPrint, listNum = 1, doneNumber = 1;

        while (!isBye) {
            try {
                userInput = input.nextLine();

//                  String[] words = userInput.split(" ");
                words = userInput.split(" ");
                task_stringIndex_After_taskWord = userInput.indexOf(" ");

                input_task();
                userInput = words[0];

            isList = userInput.equals("list");
            isBye = userInput.equals("bye");
            isDone = userInput.equals("done");
            isToDo = userInput.equals("todo");
            isDeadline = userInput.equals("deadline");
            isEvent = userInput.equals("event");
            isDelete = userInput.equals("delete");

            if (isToDo) {
                try{
                    if(words[1] != "")
                    addTask(new Todo(task_words));
                    print_event();
                }
                catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            }

            else if (isDeadline) {
                try {
                    if (words[1] != "")
                        addTask(new Deadline(task_words, by_words));
                        print_event();
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            }

            else if (isEvent) {
                try {
                    if (words[1] != "")
                        addTask(new Event(task_words, by_words));
                        print_event();
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            }

//            else if (isDelete) {
//                try{
//                    deleteTask(Integer.parseInt(task_words.trim()));
//                }
//                catch (NumberFormatException e) {
//                    System.out.println("Please enter which integer after delete ");
//                }
//            }

            else if (isList){
                 int count_todo = task_count;
                 System.out.print("   " + seperatorLine2);

                 for (listPrint = 0; listPrint  < count_todo; listPrint++) {
//                     System.out.println("        " + listNum + ". " + "[" + mark[listPrint] + "]"  + todolistArray[listPrint]);
                     System.out.println("        " + listNum + ". " + "[" + mark[listPrint] + "]"  + todolistArray.get(listPrint));
                     listNum++;
                 }
                 listNum = 1;
                System.out.print("   " + seperatorLine2);
            }

            else if(isDone){
                try{
                    if (words[1] != "") {
                        doneTask(Integer.parseInt(task_words.trim()));
                    }
                }
                catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The number of a done cannot be empty.");
                }
                catch (NumberFormatException e) {
                    System.out.println("Please enter which integer after delete ");
                }
            }

            else if (isBye){

                System.out.print(seperatorLine);
                System.out.println("       " + "Bye. Hope to see you again soon!");
                System.out.println(seperatorLine);
            }

            else {
                throw new DukeException();
            }

            }
            catch (StringIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! Please key in space after list");
            }

//            else {
//                added_Print();
//            }
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

        boolean retry = true;
        while (retry){
            try{
                Scanner input = new Scanner(System.in);

                Arrays.fill(mark, "\u2718");  //initialize mark array = "x"

                echo_Added_List(input);
                retry = false;
            }
            catch (DukeException e){
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//                System.out.println("Please enter keywords \"bye\" again ");
                retry = true;
            }
        }

    }
}


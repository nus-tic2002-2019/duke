import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    private static String seperatorLine = "___________________________________________\n";
    private static String seperatorLine2 = "________________________________________\n";
    public static String[] listArray = new String[100];
    public static int listCount = 0;
    public static String userInput;

    public static String[] todolistArray = new String[100];

    public static Task[] taskss = new Task[100];
    public static int taskCount = 0;

//
//    public static void addTask(Task s) {
//        taskss[taskCount] = s;
//        taskCount++;
//    }



    public static void Added_Print(Scanner input){
        System.out.print("   " + seperatorLine2);
        System.out.println("      added:" + userInput);
        System.out.print("   " + seperatorLine2);

        listArray[listCount] = userInput;
        listCount++;
    }



    public static void Echo_Added_List(Scanner input) {

        boolean isBye = false;
        boolean isList;
        boolean isDone;
        boolean isToDo;
        boolean isDeadline;
        int  listPrint, count = 0, listNum = 1, doneNumber = 1;
        int task_count = 0;

        String[] mark = new String[100];
        Arrays.fill(mark, "\u2718");

        while (!isBye) {

            userInput = input.nextLine();
            String[] words = userInput.split(" ");

            int task_string = userInput.indexOf(" ");


            int by_string = userInput.indexOf("/");
            String by_words = userInput.substring(by_string + 3);

            String task_words = userInput.substring(task_string);


//            String[] words = userInput.substring(0,task_string);
//            String[] result = new String[words.length];

            userInput = words[0];

            isList = userInput.equals("list");
            isBye = userInput.equals("bye");
            isDone = userInput.equals("done");
            isToDo = userInput.equals("todo");
            isDeadline = userInput.equals("deadline");

            Task[] tasks = new Task[100];  //****
            Task t = new Task(userInput);

//            Task todo = new Todo(userInput);

             if (isToDo) {
                tasks[task_count] = new Todo(task_words);
//                addTask(new Todo(task_words)); ***
                todolistArray[task_count] = tasks[task_count].toString();

                System.out.print("   " + seperatorLine2);
                System.out.println("     " + "Got it. I've added this task");
                System.out.println("        " + "[" + t.getStatusIcon() + "]" + todolistArray[task_count]);

//                doneNumber = Integer.parseInt(words[1]) - 1;
//                System.out.println(listArray[doneNumber]);
//                mark[doneNumber] = t.getStatusIcon();

                System.out.println("Now you have "+ (task_count + 1) + " tasks in the list.");
                System.out.print("   " + seperatorLine2);
                task_count++;


            }

             else if (isDeadline) {
                 tasks[task_count] = new Deadline(task_words, by_words);

                 todolistArray[task_count] = tasks[task_count].toString();

                 System.out.print("   " + seperatorLine2);
                 System.out.println("     " + "Got it. I've added this task");
                 System.out.println("        " + "[" + t.getStatusIcon() + "]" + todolistArray[task_count]);

                 System.out.println("Now you have "+ (task_count + 1) + " tasks in the list.");
                 System.out.print("   " + seperatorLine2);
                 task_count++;
             }

            else if (isList){
                count = listCount;
                int count_todo = task_count;
                System.out.print("   " + seperatorLine2);

//                for (listPrint = 0; listPrint < count; listPrint++) {
//
//                    System.out.println("     " + listNum + ". " + "[" + mark[listPrint] + "]"  + " " + listArray[listPrint]);
//                    listNum++;
//                }
//                listNum=1;

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
                Added_Print(input);
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

        Echo_Added_List(input);

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
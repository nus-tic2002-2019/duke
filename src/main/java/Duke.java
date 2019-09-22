import java.util.Scanner;
import java.util.Arrays;


public class Duke {
    private static Task[] task = new Task[100];
    private static Integer taskNo = 0;

    public static void addTask(Task t){
        task[ taskNo ] = t;
        taskNo ++ ;
    }

    public static void main(String[] args) {

        Message.welcomeMessage(); // Duke welcome message

        Boolean isNotBye = Boolean.TRUE;   // check if user type "Bye"
        Boolean isNotList = Boolean.TRUE;   // check if user type "List"
        Boolean done = Boolean.FALSE;       // status of task
        String bye_word = "bye";
        String list_word = "list";
        String done_word = "done";
        String line = "";
        String[] stringList = new String[50];

        String[] s1 = new String[20]; // for splitting of user entry
        String   s2 = "" ; // for due date purposes without modification
        String   s3 = "" ; // description of user task in lowercase

        int count = 0;
        int index = 0;



 //       /*
        String command = "";
        String description = "";
        while (!command.equals(bye_word)){
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            s1 = line.split("/");
            s3 = s1[0].toLowerCase();
            String[] first_word = line.split(" ");
            command = first_word[0].toLowerCase();
            switch(command){
                case "list" :
                    System.out.println("\t--------------------------------------------------");
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < taskNo ; i++) {
                        System.out.println("\t" + (i+1) + "." + task[i]);
                    }
                    System.out.println("\t--------------------------------------------------");
                    break;
                case "todo" :
                    addTask( new Todo(s3.replace("todo ","")));
                    System.out.println("\t--------------------------------------------------");
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t" + task[taskNo-1]);
                    System.out.println("\tNow you have " + taskNo + " tasks in the list");
                    System.out.println("\t--------------------------------------------------");
                    break;
                case "deadline" :
                    //s1 = line.split("/");
                    s2 = s1[1].replace("by ", ""); // due date
                    addTask( new Deadline(s3.replace("deadline ",""), s2) );
                    System.out.println("\t--------------------------------------------------");
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t" + task[taskNo-1]);
                    System.out.println("\tNow you have " + taskNo + " tasks in the list");
                    System.out.println("\t--------------------------------------------------");
                    break;
                case "event" :
                    //s1 = line.split("/");
                    s2 = s1[1].replace("at ", ""); // event date and time
                    addTask( new Event(s3.replace("event ",""), s2));
                    System.out.println("\t--------------------------------------------------");
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t" + task[taskNo-1]);
                    System.out.println("\tNow you have " + taskNo + " tasks in the list");
                    System.out.println("\t--------------------------------------------------");
                    break;
                case "done" :
                    index = Integer.parseInt(first_word[1]);
                    task[index - 1].taskDone();
                    System.out.println("\t--------------------------------------------------");
                    System.out.println("\tNice! I marked this task as done");
                    System.out.println("\t" + task[index-1]);
                    System.out.println("\t--------------------------------------------------");
                    break;
                case "delete" :
                    System.out.println("\tyou have arrive at delete function");
                    break;
                case "bye" :
                    break;
                default : // same as todo
                    addTask( new Todo(s3.toLowerCase()));
                    System.out.println("\t--------------------------------------------------");
                    System.out.println("\tadded: " + line);
                    System.out.println("\t--------------------------------------------------");
                    break;
            }
        }
        Message.byeMessage();  // */




/*
        Scanner in = new Scanner(System.in);

        while (isNotBye) {
            line = in.nextLine();
            String[] word = line.split(" ");
            done_word = word[0];
            //taskNo = Integer.parseInt(word[1]);

            isNotBye = !(line.equalsIgnoreCase(bye_word));
            isNotList = !(line.equalsIgnoreCase(list_word));
            done = !(line.equalsIgnoreCase(done_word));


            System.out.println("\t--------------------------------------------------");

            if (isNotBye && isNotList) {
                if (done) {
                    taskNo = Integer.parseInt(word[1]);
                    t[taskNo - 1].taskDone();
                    continue;
                } else {

                    System.out.println("\t" + line);
                    stringList[count] = line;
                    t[count] = new Task(line);
                    count += 1;

                }


            } else if (!isNotList) {
                for (int i = 0; i < count; i++) {
                    //System.out.println("\t" + (i+1) + ". " + stringList[i]);
                    System.out.println("\t" + (i + 1) + "." + "[" + t[i].getStatusIcon() + "]" + t[i].getDescription());
                }

            } else {
                //System.out.println("\tBye. Hope to see you again soon!");
                Message.byeMessage();
            }
            System.out.println("\t--------------------------------------------------");
        }
        stringList = Arrays.copyOf(stringList, count);   */
    }
}

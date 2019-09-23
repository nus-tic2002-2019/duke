import java.util.Scanner;


public class Duke {
    private static Task[] task = new Task[100];
    private static Integer taskNo = 0;

    public static void addTask(Task t){
        task[ taskNo ] = t;
        taskNo ++ ;
    }
    //public static String commandCheck(String command) throws DukeException {
    //}



    public static void main(String[] args) {

        Message.welcomeMessage(); // Duke welcome message

        Boolean isNotBye = Boolean.TRUE;   // check if user type "Bye"
        Boolean isNotList = Boolean.TRUE;   // check if user type "List"
        Boolean done = Boolean.FALSE;       // status of task
        String bye_word = "bye";
        String list_word = "list";
        String done_word = "done";
        String user_input = "";
        String blank = "";
        String[] stringList = new String[50];

        String[] task_sch = new String[20]; // for splitting of user entry into command / schedule
        String   s2 = "" ; // for due date purposes without modification
        String left_task = "" ; // description of user task in lowercase
        String sch_right = "" ; // description of user schedule

        int count = 0;
        int index = 0;



 //       /*
        String command = ""; // initialise command to loop while() till "bye"
        String description = "";
        while (!command.equals(bye_word)){
            Scanner in = new Scanner(System.in);
            user_input = in.nextLine();
            task_sch = user_input.split("/");
            left_task = task_sch[0].toLowerCase();
            //sch_right = task_sch[1];
            //description = ErrType.TaskCheck(user_input);
            String[] split_word = user_input.split(" ");
            command = user_input.split(" ")[0].toLowerCase();
            //command = ErrType.command(user_input);
            switch(command){
                case "list" :
                    Message.listMessage(task,taskNo);
                    break;

                case "todo" :
                    if (ErrType.TaskCheck(user_input)) {
                        addTask(new Todo(user_input.replace("todo", "").trim()));
                        Message.acknowledgeMessage(task, taskNo);
                    }
                    break;

                case "deadline" :
                    if (ErrType.TaskCheck(user_input) && ErrType.ScheduleCheck(user_input)) {
                        addTask(new Deadline(user_input.split("/")[0].replace("deadline ", ""),
                                             user_input.split("/")[1].replace("by","")));
                        Message.acknowledgeMessage(task, taskNo);
                    }
                    break;

                case "event" :
                    if (ErrType.TaskCheck(user_input) && ErrType.ScheduleCheck(user_input)) {
//                        s2 = task_sch[1].replace("at ", ""); // event date and time
//                        addTask(new Event(left_task.replace("event ", ""), s2));
                        addTask(new Event(user_input.split("/")[0].replace("event ", ""),
                                user_input.split("/")[1].replace("at","")));
                        Message.acknowledgeMessage(task, taskNo);
                    }
                    break;

                case "done" :
                    index = ErrType.toInteger(split_word[1], taskNo); // with Exceptions handling
                    if ( index == -1) {
                        System.out.println("\tPlease key a valid task number.");
                        break;
                    }
                    Message.doneMessage(task,index);
                    break;

                case "delete" :
                    System.out.println("\tyou have arrive at delete function");
                    break;

                case "bye" : // "bye" command will end loop after looping back to while()
                    break;

                default :   // any other command will be considered as error
                    System.out.println("\tOops!! You have key an invalid command.");
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

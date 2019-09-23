import java.util.Scanner;

public class Duke {
    private static Task[] task = new Task[100];
    private static Integer taskNo = 0;

    public static void addTask(Task t) {
        task[taskNo] = t;
        taskNo++;
    }

    public static void main(String[] args) {

        Message.welcomeMessage(); // Duke welcome message

        String bye_word = "bye";
        String user_input = "";
        int index = 0;

        String command = ""; // initialise command to loop while() till "bye"

        while (!command.equals(bye_word)) {
            Scanner in = new Scanner(System.in);
            user_input = in.nextLine();
            command = user_input.split(" ")[0].toLowerCase();
            switch (command) {
                case "list":
                    Message.listMessage(task, taskNo);
                    break;

                case "todo":
                    if (ErrType.TaskCheck(user_input)) {
                        addTask(new Todo(user_input.replace("todo", "").trim()));
                        Message.acknowledgeMessage(task, taskNo);
                    }
                    break;

                case "deadline":
                    if (ErrType.TaskCheck(user_input) && ErrType.ScheduleCheck(user_input)) {
                        addTask(new Deadline(user_input.split("/")[0].replace("deadline ", ""),
                                user_input.split("/")[1].replace("by", "")));
                        Message.acknowledgeMessage(task, taskNo);
                    }
                    break;

                case "event":
                    if (ErrType.TaskCheck(user_input) && ErrType.ScheduleCheck(user_input)) {
                        addTask(new Event(user_input.split("/")[0].replace("event ", ""),
                                user_input.split("/")[1].replace("at", "")));
                        Message.acknowledgeMessage(task, taskNo);
                    }
                    break;

                case "done":
                    index = ErrType.toInteger(user_input.split(" ")[1], taskNo); // with Exceptions handling
                    if (index == -1) {
                        System.out.println("\tPlease key a valid task number.");
                        break;
                    }
                    Message.doneMessage(task, index);
                    break;

                case "delete":
                    System.out.println("\tyou have arrive at delete function");
                    break;

                case "bye": // "bye" command will end loop after looping back to while()
                    break;

                default:   // any other command will be considered as error
                    System.out.println("\tOops!! You have key an invalid command.");
                    break;
            }
        }
        Message.byeMessage();  // */
    }
}
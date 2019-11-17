public class Ui {
    //Declare constant variables
    private static final String STRING_BYE = "bye";
    private static final String STRING_LIST = "list";
    private static final String STRING_DONE = "done";
    private static final String STRING_DELETE = "delete";
    private static final String STRING_TODO = "todo";
    private static final String STRING_DEADLINE = "deadline";
    private static final String STRING_EVENT = "event";
    private static final String LINE_SEPARATOR = "____________________________________________________________\n";
    private static final String MSG_GREET = LINE_SEPARATOR + "Hello! I'm Duke\n" + "What can I do for you?\n" + LINE_SEPARATOR;
    private static final String MSG_BYE = LINE_SEPARATOR + "Bye. Hope to see you again soon!\n" + LINE_SEPARATOR;
    private static final String MSG_LIST = LINE_SEPARATOR + "Here are the tasks in your list:";
    private static final String MSG_DONE = LINE_SEPARATOR + "Nice! I've marked this task as done:";
    private static final String MSG_DELETE = LINE_SEPARATOR + "Noted. I've removed this task:";
    private static final String MSG_PRE_TASK = LINE_SEPARATOR + "Got it. I've added this task:";
    private static final String MSG_POST_TASK_1 = "Now you have ";
    private static final String MSG_POST_TASK_2 = " tasks in the list.\n" + LINE_SEPARATOR;
    private static final String ERROR_MSG_IO = "Input or output failure!\n";

    //Store keywords' number of characters
    private static int doneStrLen = STRING_DONE.length();
    private static int deleteStrLen = STRING_DELETE.length();
    private static int todoStrLen = STRING_TODO.length();
    private static int deadlineStrLen = STRING_DEADLINE.length();
    private static int eventStrLen = STRING_EVENT.length();

    //Declare error inputs in an array
    private static String[] errorInputList = {"blah"};

    //Constructor
    public Ui() {
        greetUser();
    }

    //Greet user method
    public static void greetUser() {
        System.out.print(MSG_GREET);
    }
}

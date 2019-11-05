import java.util.Scanner;

public class Ui {
    private Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String readCommand() throws DukeException {
        String input = this.in.nextLine();
        if (input.isEmpty()) {
            throw new DukeException();
        }
        return input;
    }

    public String showLine() {
        return "\t________________________________________________________________\n\t";
    }

    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcome = "Hello! I'm Duke\n\tWhat can i do for you?\n";
        System.out.println(logo + showLine() + welcome + showLine());
    }

    public void showTaskAdded(String taskDetails, int listSize) {
        String currentTask = "Got it. I've added this task:\n\t\t" + taskDetails;
        String numberOfTask = "\n\tNow you have " + listSize + " tasks in the list.\n";
        System.out.println(showLine() + currentTask + numberOfTask + showLine());
    }

    public void showDeletedTask(String deletedTask, TaskLists tasklist) {
        String deletedTaskMessage = "Noted. I've removed this task: \n\t\t" + deletedTask;
        String deletedTaskMessage2 = "\n\tNow you have " + tasklist.getSize() + " tasks in the list.\n";
        System.out.println(showLine() + deletedTaskMessage + deletedTaskMessage2 + showLine());
    }

    public void showDoneTask(String doneTask) {
        String doneMessage = "Nice! I've marked this task as done: \n\t" + doneTask;
        System.out.println(showLine() + doneMessage + showLine());
    }

    public void showList(String list) {
        String listTask = "Here are the Tasks in your list:\n\t" + list + "\n";
        System.out.println(showLine() + listTask + showLine());
    }

    public void showFindResult(String list) {
        String listResult = "Here are the matching tasks in your list:\n\t" + list + "\n";
        System.out.println(showLine() + listResult + showLine());
    }

    public void showOffline() {
        String goodbye = "Bye. Hope to see you again soon!\n";
        System.out.println(showLine() + goodbye + showLine());
    }

    public void showInputError() { //can be for files input
        String errorMessage = "There is something wrong with the previous file, please check the content\n\tof the file. I'm unable to load the past task list.\n";
        System.out.println(showLine() + errorMessage + showLine());
    }

    public void showToDoEmptyError() {
        String errorMessage = "\u2639 OOPS!!! The description of a todo cannot be empty.\n";
        System.out.println(showLine() + errorMessage + showLine());
    }

    public void showUnknownInputError() {
        String errorMessage = "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        System.out.println(showLine() + errorMessage + showLine());
    }

    public void showDeadlineEmptyError() {
        String errorMessage = "\u2639 OOPS!!! The description of the deadline cannot be empty.\n";
        System.out.println(showLine() + errorMessage + showLine());
    }

    public void showEventEmptyError() {
        String errorMessage = "\u2639 OOPS!!! The description of the event cannot be empty.\n";
        System.out.println(showLine() + errorMessage + showLine());
    }

    public void showDoAfterEmptyError() {
        String errorMessage = "\u2639 OOPS!!! The description/date of the task cannot be empty.\n";
        System.out.println(showLine() + errorMessage + showLine());
    }

    public void showListEmptyError() {
        String errorMessage = "\u2639 You have an empty list.\n";
        System.out.println(showLine() + errorMessage + showLine());
    }

    public void showInvalidTaskFormatError() {
        String errorMessage = "You've entered an invalid format of Task, please check your input format again.\n";
        System.out.println(showLine() + errorMessage + showLine());
    }

    public void showInvalidTaskNumberError() {
        String errorMessage = "You've entered an invalid value for Task, please key in valid task number.\n";
        System.out.println(showLine() + errorMessage + showLine());
    }

    public void showFileError() {
        String errorMessage = "Duke is unable to read your file, please check your input filepath again.";
        System.out.println(showLine() + errorMessage + showLine());
    }
}

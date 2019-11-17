package duke.ui;

/**
 * Start of the ui module
 */
public class ui {
    /**
     * The various commonly used text (Error messages, welcome/goodbye greetongs, command explanations and formats) that make up the user interface of Duke
     */
    public void welcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String formattingLine1 = "____________________________________________________________\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(formattingLine1 + "Hello! I'm Duke\n" + "What can I do for you?\n" + formattingLine1);
    }

    public void goodbye(){
        System.out.println("____________________________________________________________\n" + "Bye. Hope to see you again soon!\n" + "____________________________________________________________\n");
    }

    public void printLine1(){
        System.out.println("____________________________________________________________\n");
    }

    public void printLine2(){
        System.out.println("____________________________________________________________");
    }

    public void availableCommands (){
        System.out.println("Available commands: deadline, event, todo, list, delete, done, find, need, bye\n");
    }

    public void invalidCommand (){
        System.out.println("You have entered an invalid command, please try again\n");
    }

    public void deadlineUsage (){
        System.out.println("Command usage: deadline <task> /<yyyy>-<mm>-<dd>");
    }

    public void deleteUsage (){
        System.out.println("Command usage: delete <task number>");
    }

    public void todoUsage (){
        System.out.println("Command usage: todo <task number>");
    }

    public void eventUsage (){
        System.out.println("Command usage: event <task> /<yyyy>-<mm>-<dd>");
    }

    public void doneUsage (){
        System.out.println("Command usage: done <number>");
    }

    public void findUsage (){
        System.out.println("Command usage: find <text to find>");
    }

    public void needUsage () {
        System.out.println("Command usage: need <task> /<hours required>");
    }

    public void readError() {
        System.out.println("Error reading from disk, new list created\n");
    }

    public void writeError() {
        System.out.println("---WARNING WARNING WARNING WARNING WARNING---\nError writing to disk, your list is --> NOT <-- saved\n---WARNING WARNING WARNING WARNING WARNING---\n");
    }
}
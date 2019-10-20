import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {

/**********************************************************************************************************************
* Task Class [Superclass]
**********************************************************************************************************************/

    public static class Task {
        // Variables
        private String description;
        private boolean isDone;
        // Constructors.
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }
        // Methods.
        public String getStatusIcon() {
            return (isDone ? "[\u2713]" : "[\u2718]"); // return tick or cross symbol.
        }
        public String getTask() {
            return description;
        }
        public void markAsDone() {
            isDone = true;
        }
        public String toString() { return getStatusIcon() + " " + getTask();}

    }
/**********************************************************************************************************************
* todo [Subclass] , Parent : Task
**********************************************************************************************************************/

    public static class Todo extends Task {
        // Variables.
        protected boolean isTodo;

        // Constructors.
        public Todo(String Description) {
            super(Description);
        }
        // Methods.
        public String toString() {
            return "[T]" + super.toString();
        }
        public String getTodoIcon() {return "[T]";}
    }

/**********************************************************************************************************************
* deadline [Subclass] , Parent : Task
**********************************************************************************************************************/

    public static class Deadline extends Task {
        // Variables.
        String by;
        // Constructors.
        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }
        // Methods.
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

/**********************************************************************************************************************
* Events [Subclass] , Parent : Task
**********************************************************************************************************************/
    public static class Events extends Task {
        // Variables.
        String by;

        // Constructors.
        public Events(String description, String by) {
            // Constructor
            super(description);
            this.by = by;
        }

        // Methods.
        public String toString() {
            return "[E]" + super.toString() + " (at: " + by +") ";
        }
    }

// My arraylist was of type Task. Hence to access subclasses, downcasting is required to access the subclass-methods.
    public static void downcastToTodo(Task abc) {
        Todo taskAssigned = (Todo) abc;
    }

/**********************************************************************************************************************
 * list Function.
 **********************************************************************************************************************/

public static void list(ArrayList<Task> ultimateStorage, String userResponse) {

    String graphicalFormatStart = ("    "
            + "____________________________________________________________"
            + "\n"
            + "    ");

    String graphicalFormatEnd = ("\n" + "    " + "____________________________________________________________");
    String spaces = "    ";
    String nextLine = "\n";

    System.out.println(graphicalFormatStart);

    for(Task ele : ultimateStorage) {
        System.out.printf(spaces); //formatting.
        if(ele instanceof Todo ) {
            downcastToTodo(ele);
            System.out.println(ultimateStorage.indexOf(ele)+1
                    + "."
                    + ele.toString()
            );
        } else {
            // This could change into a function call printList. For reuse-and better code readability.
            System.out.println(ultimateStorage.indexOf(ele)+1 + "."
                    + ele.toString()
            );
        }
    } System.out.println("    " + "____________________________________________________________");
}

/**********************************************************************************************************************
 * Done Function.
 **********************************************************************************************************************/

public static void Done(ArrayList<Task> ultimateStorage, String userResponse) {


    String graphicalFormatStart = ("    "
            + "____________________________________________________________"
            + "\n"
            + "    ");

    String graphicalFormatEnd = ("\n" + "    " + "____________________________________________________________");
    String spaces = "    ";
    String nextLine = "\n";

    if(userResponse.matches(".*\\d.*")) {
        // done 3 -> will get 3.
        int value = Integer.parseInt(userResponse.replaceAll("[^0-9]", ""));
        // real index in the array is in fact 2.
        int realIndex = value - 1;
        //for verification of the items.
        //System.out.println(ultimateStorage.get(realIndex).getTask());
        ultimateStorage.get(realIndex).markAsDone();
        System.out.println(
                graphicalFormatEnd + "\n"
                        + spaces
                        + "Nice! I've marked this task as done: \n"
                        + spaces
                        + ultimateStorage.get(realIndex).toString()
                        + graphicalFormatEnd
        );
    }
}

/**********************************************************************************************************************
 * Delete Function.
 **********************************************************************************************************************/
    public static void Delete(ArrayList<Task> ultimateStorage, String itemToDelete) {

        String graphicalFormatStart = ("    "
                + "____________________________________________________________"
                + "\n"
                + "    ");

        String graphicalFormatEnd = ("\n" + "    " + "____________________________________________________________");
        String spaces = "    ";
        String nextLine = "\n";

        String tempWord = "delete ";
        String indexToDeleteStringFormat = itemToDelete.replaceAll(tempWord, "");
        int value = Integer.parseInt(indexToDeleteStringFormat.replaceAll("[^0-9]", "")) - 1;

        System.out.println(graphicalFormatStart
                            + "Noted. I've removed this task: "
                            + spaces + nextLine + spaces + "  "
                            + ultimateStorage.get(value).toString()
                          );
        ultimateStorage.remove(value);
        System.out.println(spaces + "Now you have " + ultimateStorage.size() + " in the list." + graphicalFormatEnd);
    }


/**********************************************************************************************************************
 * parseStoreAndPrintEventsTask Function.
 **********************************************************************************************************************/

public static void parseStoreAndPrintEventTask(ArrayList<Task> ultimateStorage, String userResponse, String[] eventKeywords) {

    String graphicalFormatStart = ("    "
            + "____________________________________________________________"
            + "\n"
            + "    ");

    String graphicalFormatEnd = ("\n" + "    " + "____________________________________________________________");
    String spaces = "    ";
    String nextLine = "\n";
    Boolean eventKeywordFound = false;
    String keyword = null;
//    Boolean todoKeywordFound = false;
//    Boolean deadlineKeywordFound = false;

    for(String item : eventKeywords) {
        if(userResponse.startsWith(item)) {
            eventKeywordFound = true;
            keyword = item;
        }
    }

    if (eventKeywordFound) {
        String reSentence = userResponse.replaceAll(keyword, "");
        //To filter out relevant words.
        String eventDesc = reSentence.substring(0,reSentence.indexOf("at") - 1);
        String eventAt = reSentence.substring(reSentence.indexOf("at")+ 3);
        Events event = new Events(eventDesc,eventAt);
        ultimateStorage.add(event);

        System.out.println(
                graphicalFormatEnd
                        + "\n"
                        + spaces + "Got it. I've added this task: \n  " + spaces
                        + ultimateStorage.get(ultimateStorage.indexOf(event)).toString()
                        + "\n" + spaces + "Now you have " + ultimateStorage.size() + " tasks in the list."
                        + graphicalFormatEnd
        );
    }
}

/**********************************************************************************************************************
* parseStoreAndPrintTodoTask Function.
**********************************************************************************************************************/

    public static void parseStoreAndPrintTodoTask(ArrayList<Task> ultimateStorage, String userResponse, String[] todoKeywords) {

        String graphicalFormatStart = ("    "
                + "____________________________________________________________"
                + "\n"
                + "    ");

        String graphicalFormatEnd = ("\n" + "    " + "____________________________________________________________");
        String spaces = "    ";
        String nextLine = "\n";
        String keyword = null;
        Boolean todoKeywordFound = false;
//    Boolean deadlineKeywordFound = false;

        for(String item : todoKeywords) {
            if(userResponse.startsWith(item)) {
                todoKeywordFound = true;
                keyword = item;
            }
        }

        if (todoKeywordFound) {
            String reSentence = userResponse.replaceAll(keyword, "");
            //To filter out relevant words.
            Todo td = new Todo(reSentence);
            ultimateStorage.add(td);
            System.out.println(
                    graphicalFormatEnd
                            + "\n"
                            + spaces + "Got it. I've added this task: \n  " + spaces
                            + ultimateStorage.get(ultimateStorage.indexOf(td)).toString()
                            + "\n" + spaces + "Now you have " + ultimateStorage.size() + " tasks in the list."
                            + graphicalFormatEnd
            );
        }
    }

/**********************************************************************************************************************
 * parseStoreAndPrintTodoTask Function.
 **********************************************************************************************************************/

public static void parseStoreAndPrintDeadlineTask(ArrayList<Task> ultimateStorage, String userResponse, String[] deadlineKeywords) {

    String graphicalFormatStart = ("    "
            + "____________________________________________________________"
            + "\n"
            + "    ");

    String graphicalFormatEnd = ("\n" + "    " + "____________________________________________________________");
    String spaces = "    ";
    String nextLine = "\n";
    String keyword = null;
    Boolean deadlineKeywordFound = false;

    for (String item : deadlineKeywords) {
        if (userResponse.startsWith(item)) {
            deadlineKeywordFound = true;
            keyword = item;
        }
    }

    String reSentence = userResponse.replaceAll(keyword, "");
    // To filter out relevant words.
    String deadlineDesc = reSentence.substring(0,reSentence.indexOf("by") - 1);
    String by = reSentence.substring(reSentence.indexOf("by")+ 3);
    //System.out.println(deadlineDesc);
    //System.out.println(by);
    Deadline deadline = new Deadline(deadlineDesc,by);
    ultimateStorage.add(deadline);

    System.out.println(
            graphicalFormatEnd
                    + "\n"
                    + spaces + "Got it. I've added this task: \n  " + spaces
                    + ultimateStorage.get(ultimateStorage.indexOf(deadline)).toString()
                    + "\n" + spaces + "Now you have " + ultimateStorage.size() + " tasks in the list."
                    + graphicalFormatEnd
    );
}

/**********************************************************************************************************************
 * Exceptions
**********************************************************************************************************************/

public static class DukeExceptions extends Exception {

    public static boolean TodoError(String Sentence) {
        String check = Sentence;
        if (check.equals("todo") || check.equals("Todo") || check.equals("TODO")) {
            return true;
        } else return false;
    }


    public static boolean funnyWords(String word) {
        ArrayList<String> funnyWords = new ArrayList<String>();
        funnyWords.add("Blah");
        funnyWords.add("blah");
        funnyWords.add("BLAH");
        funnyWords.add("bleh");
        funnyWords.add("Bleh");

        for (String item : funnyWords) {
            if (word.equals(item)) {
                return true;
            }
        }
        return false;

    }

    public static boolean DeadlineError(String Sentence) {
        String check = Sentence;
        if (check.equals("deadline") || check.equals("Deadline") || check.equals("DEADLINE")) {
            return true;
        } else return false;
    }
}
    public static void main(String[] args) throws DukeExceptions {

/**********************************************************************************************************************
 * Variables.
 **********************************************************************************************************************/

        String logo =
                  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "____________________________________________________________";
        String spaces = "    ";
        String nextLine = "\n";
        Scanner scanInput = new Scanner(System.in);
        // 08-Sep-2019.
        Task[] content = new Task[100];
        ArrayList<Task> ultimateStorage = new ArrayList<Task>();
        String[] eventKeywords = new String[]{"Event ", "event ", "EVENTS "};
        String[] todoKeywords = new String[]{"Todo ", "todo ", "TODO "};
        String[] deadlineKewords = new String[]{"Deadline ", "deadline ", "DEADLINE "};


/**********************************************************************************************************************
 * Formatting
 **********************************************************************************************************************/

        String graphicalFormatStart = ("    "
                + "____________________________________________________________"
                + "\n"
                + "    ");

        String graphicalFormatEnd = ("\n" + "    " + "____________________________________________________________");

        System.out.println("Hello from" + nextLine + logo);
        System.out.println(spaces + line);
        System.out.println(spaces
                +"Hello! I'm Duke"
                + nextLine + spaces
                + "What can I do for you?"
                + nextLine + spaces + line);

/**********************************************************************************************************************
 * Level One + Level Two + To be honest... all the levels are in between being mixed up..
 **********************************************************************************************************************/

        while (true) {
            String userResponse = scanInput.nextLine();
            // if response is "bye"
            if (userResponse.equals("bye")) {
                break;
            }
            // if response is "list"
            else if (userResponse.equals("list")) {
                list(ultimateStorage,userResponse);
            }
            else if (userResponse.startsWith("DELETE") || userResponse.startsWith("Delete") || userResponse.startsWith("delete")) {
                Delete(ultimateStorage,userResponse);
            }
            else if (userResponse.startsWith("done") || userResponse.startsWith("DONE") || userResponse.startsWith("Done")) {
                Done(ultimateStorage, userResponse);
            }
            else if ( userResponse.startsWith("event") || userResponse.startsWith("Event") || userResponse.startsWith("EVENT")) {
                parseStoreAndPrintEventTask(ultimateStorage,userResponse,eventKeywords);
            }
            else if (DukeExceptions.TodoError(userResponse)) { // Exceptions Handling TODO
                System.out.println(
                        graphicalFormatEnd
                        + "\n" + spaces
                        +"OPSS Please type in a task for Todo!"
                        + "\n"
                        + graphicalFormatEnd);
            }
            else if (userResponse.startsWith("todo") || userResponse.startsWith("TODO") || userResponse.startsWith("Todo")) {
                parseStoreAndPrintTodoTask(ultimateStorage,userResponse,todoKeywords);
            }
            else if (DukeExceptions.DeadlineError(userResponse)) { // Exceptions Handling DEADLINE
                System.out.println(
                        graphicalFormatEnd
                                + "\n" + spaces
                                +"OPSS Please type in a task for deadline!"
                                + "\n"
                                + graphicalFormatEnd);
            }

            else if (userResponse.startsWith("Deadline") || userResponse.startsWith("DEADLINE") || userResponse.startsWith("deadline")) {
                parseStoreAndPrintDeadlineTask(ultimateStorage,userResponse,deadlineKewords);
            }
            else {
                if(DukeExceptions.funnyWords(userResponse)) { // Exceptions Handling
                    System.out.println(
                            graphicalFormatEnd
                            + "\n" + spaces
                            + "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :("
                            + "\n"
                            + graphicalFormatEnd);
                }
                else {
                    Task t = new Task(userResponse);
                    ultimateStorage.add(t);
                    System.out.println(
                            graphicalFormatStart
                                    + "added: "
                                    + ultimateStorage.get(ultimateStorage.indexOf(t)).getTask()
                                    + graphicalFormatEnd);
                }
            }
        }
        System.out.println(graphicalFormatStart
                +"Bye. Hope to see you again soon!"
                + graphicalFormatEnd);
        //System.out.println(Arrays.toString(content)); // use to check variables in arrays.
    }
}

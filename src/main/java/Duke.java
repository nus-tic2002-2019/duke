import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {

    public static final String LOGO =
            " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String DIVIDER = "\n" + "    " + "____________________________________________________________" + "\n" + "    ";
    public static final String graphicalFormatStart = ("    " + "____________________________________________________________" + "\n" + "    ");
    public static final String graphicalFormatEnd = ("\n" + "    " + "____________________________________________________________");

//    String line = "____________________________________________________________";
//    String spaces = "    ";
//    String nextLine = "\n";

/**********************************************************************************************************************
 * Public UI class.
 **********************************************************************************************************************/
    public static void textUI() {

        System.out.print(
                "Hello from" + "\n" + LOGO + DIVIDER
                + "Hello! I'm Duke" + "\n" + "    "
                + "What can I do for you?" +
                DIVIDER);
    }

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
        public String getBy() {return by;}
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
        public String getBy() {return by;}
    }
// My arraylist was of type Task. Hence to access subclasses, downcasting is required to access the subclass-methods.
    public static void downcastToTodo(Task abc) {
        Todo taskAssigned = (Todo) abc;
    }
    public static void downcastToDeadline(Task abc) {
        Deadline taskAssigned = (Deadline) abc;
    }
    public static void downcastToEvents(Task abc) {
        Events taskAssigned = (Events) abc;
    }
    public static Todo downcastToTodo_1(Task abc) {
        return (Todo) abc;
    }
    public static Deadline downcastToDeadline_1(Task abc) {
        return (Deadline) abc;
    }

    public static Events downcastToEvents_1(Task abc) {
        return (Events) abc;
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
        } else if (ele instanceof Deadline) {
            downcastToDeadline(ele);
            System.out.println(ultimateStorage.indexOf(ele)+1
                    + "."
                    + ele.toString());
        } else if (ele instanceof Events) {
            downcastToEvents(ele);
            System.out.println(ultimateStorage.indexOf(ele) + 1
                    + "."
                    + ele.toString());
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
        Boolean duplicates = false;
        String reSentence = userResponse.replaceAll(keyword, "");
        //To filter out relevant words.
        String eventDesc = reSentence.substring(0,reSentence.indexOf("at") - 1);
        String eventAt = reSentence.substring(reSentence.indexOf("at")+ 3);
        Events event = new Events(eventDesc,eventAt);

        for (Task task : ultimateStorage) {
            if (event.getTask().equals(task.getTask())){
                System.out.println(spaces + "You have the same task in list. It's a duplicate!" + nextLine
                        + graphicalFormatEnd);
                duplicates = true;
            }
        }

        if(!duplicates) {

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
        Boolean duplicates = false;
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

            for (Task task : ultimateStorage) {
                if (td.getTask().equals(task.getTask())){
                    System.out.println(spaces + "You have the same task in list. It's a duplicate!" + nextLine
                                        + graphicalFormatEnd);
                    duplicates = true;
                }
            }

            if(!duplicates) {
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
    }

/**********************************************************************************************************************
 * parseStoreAndPrintTodoTask Function.
 **********************************************************************************************************************/

public static void  parseStoreAndPrintDeadlineTask(ArrayList<Task> ultimateStorage, String userResponse, String[] deadlineKeywords) {

    String graphicalFormatStart = ("    "
            + "____________________________________________________________"
            + "\n"
            + "    ");

    String graphicalFormatEnd = ("\n" + "    " + "____________________________________________________________");
    String spaces = "    ";
    String nextLine = "\n";
    String keyword = null;
    Boolean deadlineKeywordFound = false;
    Boolean duplicates = false;

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

    int year = Integer.parseInt(by.substring(0,4));
    int month = Integer.parseInt(by.substring(5,7));
    LocalDate today = LocalDate.now();


    //System.out.println(deadlineDesc);

    Deadline deadline = new Deadline(deadlineDesc,by);

/************************************************************************
 * Check if there is duplicates.
************************************************************************/

    for (Task task : ultimateStorage) {
        if (deadline.getTask().equals(task.getTask())){
            System.out.println(spaces + "You have the same task in list. It's a duplicate!" + nextLine
                    + graphicalFormatEnd);
            duplicates = true;
        }
    }
    if(!duplicates) {
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

/**********************************************************************************************************************
* Exceptions
***********************************************************************************************************************/

private static void writeToFile(String filePath, String textToAdd) throws IOException {
    FileWriter fw = new FileWriter(filePath);
    fw.write(textToAdd);
    fw.close();
}

    public static void main(String[] args) throws DukeExceptions, IOException {
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
        //writeToFile test = new writeToFile();
        String pathToStore = "C:/Users/Lucas/Desktop/Duke/dukey.txt";



/*********************************************************
 * Formatting
 *********************************************************/

        textUI();

/*********************************************************
 * Level One + Level Two + To be honest...
 * all the levels are in between being mixed up..
 *********************************************************/

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


 /*********************************************************
 * Write to File.
 *********************************************************/
        File file = new File(pathToStore);
        if (file.createNewFile()) {
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }

        FileWriter writer = new FileWriter(file);

        try {
            for(Task task: ultimateStorage) {

                int doneStatus = 0;
                if (task.isDone) {
                    doneStatus = 1;
                } else {
                    doneStatus = 0;
                }
                if (task.getClass().getSimpleName().equals("Todo")) {
                    writer.write(
                                "Todo    "
                                    + " | "
                                    + doneStatus +  " | "
                                    + task.getTask()
                                    + System.lineSeparator());
                }
                else if (task.getClass().getSimpleName().equals("Deadline")) {
                    Deadline temp = downcastToDeadline_1(task);
                    writer.write(
                                "Deadline"
                                    + " | "
                                    + doneStatus +  " | "
                                    + temp.getTask() + " | "
                                    + temp.getBy()
                                    + System.lineSeparator());
                }
                else if (task.getClass().getSimpleName().equals("Events")) {
                    Events temp = downcastToEvents_1(task);
                    writer.write(
                            "Events  "
                                    + " | "
                                    + doneStatus +  " | "
                                    + temp.getTask() + " | "
                                    + temp.getBy()
                                    + System.lineSeparator());
                } else {
                    writer.write("Normal  "
                            + " | "
                            + doneStatus +  " | "
                            + task.getTask() + " | "
                            + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        writer.close();

//        System.out.println(Arrays.toString(content)); // use to check variables in arrays.
//        test.writeToFile(ultimateStorage);
    }
}

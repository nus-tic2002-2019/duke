import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> toDoList = new ArrayList<Task>();
    static Map <String,String> errList = new HashMap<String, String>();


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        generateErrList();
        try {
            greet();
            echo();
        } catch(DukeException ex) {}

    }

    public static void print(String input) {
        input = input.replace("\n", "\n\t");
        System.out.println("\t" + input);
        System.out.println("__________________________________________________________________________\n");
    }

    public static void greet() {
        print("Harrowwwwwww\nWut iz up?");
    }

    public static void generateErrList() {
        errList.put("General", "IDK what this :(");
        errList.put("EmptyTaskDesc", "Description cannot be empty >:(");
        errList.put("MissingChar/", "Please enter a date and lead it with \"/\"");
        errList.put("MissingDate", "Please include a date after the \"/\" :)");
        errList.put("TaskAlrDone", "Task is already done!! D:<");
        errList.put("TaskNotFound", "No such task here :(");

    }

    public static void echo() throws DukeException{
        Scanner in = new Scanner(System.in);
        boolean continueLoop = true;
        try {
            while (continueLoop) {
                String input = in.nextLine();
                switch (input.trim()) {
                    case ("bb"):
                    case ("bye"):
                        print("Oyasumi~");
                        continueLoop = false;
                        break;
                    case ("list"):
                        displayList();
                        break;
                    default:
                        if (input.matches("done.*")) {
                            input = input.replace("done", "").trim();
                            if (isNumber(input)) {
                                int index = Integer.parseInt(input);
                                markAsDone(index - 1);
                            }
                        }
                        else if (input.matches("delete.*")){
                            input = input.replace("delete", "").trim();
                            if (isNumber(input)) {
                                int index = Integer.parseInt(input);
                                deleteTask(index - 1);
                            }

                        }
                        else if (input.matches("todo.*") || input.matches("deadline.*") || input.matches("event.*")) {
                            createTask(input);
                        }
                        else {
                            throw new DukeException(errList.get("General"));
                        }
                }
            }
        }
        finally {
            echo();
        }
        in.close();
    }

    public static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i)) == false)
                return false;
        }
        return true;
    }

    public static void createTask(String input) {
        try {
            if (input.matches("todo.*")) {
                createToDo(input);
            } else if (input.matches("deadline.*")) {
                createDeadlineEvent(input, "deadline");
            } else if (input.matches("event.*")) {
                createDeadlineEvent(input, "event");
            }
        } catch(DukeException ex) {

        }
    }


    public static void createToDo(String input) throws DukeException {
        String taskDesc = input.replace("todo", "").trim();
        if (taskDesc.length() > 0) {
            toDoList.add(new ToDo(taskDesc));
        }
        else {
            throw new DukeException(errList.get("EmptyTaskDesc"));
        }
    }

    public static void createDeadlineEvent(String input, String taskType) throws DukeException {
        String taskDesc = input.replace(taskType, "").trim();
        if (taskDesc.length() == 0 || taskDesc.charAt(0) == '/') throw new DukeException(errList.get("EmptyTaskDesc"));
        int lastIndex = taskDesc.lastIndexOf("/");
        if (lastIndex == -1) throw new DukeException(errList.get("MissingChar/"));
        String date = taskDesc.substring(lastIndex + 1);
        if (date.length() == 0)  throw new DukeException(errList.get("MissingDate"));
        taskDesc = taskDesc.substring(0, lastIndex);
        if (taskType == "deadline") {
            if (date.indexOf("by") == -1) date = "by " + date;
            date = date.replace("by", "by:");
            String finalDesc = taskDesc + " (" + date + ")";
            toDoList.add(new Deadline(finalDesc));
        }
        else if (taskType == "event") {
            if (date.indexOf("at") == -1) date = "at " + date;
            date = date.replace("at", "at:");
            String finalDesc = taskDesc + " (" + date + ")";
            toDoList.add(new Event(finalDesc));
        }
    }

    public static void displayList() {
        String input = "Yessir! Here is the list!\n";
        if (toDoList.isEmpty())
            input = "No task~ ^o^";
        else {
            for(int i = 0; i < toDoList.size(); ++i) {
                input += "\t" + (i+1) + ". "+ toDoList.get(i).getStatusIconAndDesc() + "\n";
            }
        }
        print(input);
    }

    public static void markAsDone(int index) throws DukeException {
        if (toDoList.size() >= index) {
            if (toDoList.get(index).getIsDone()) {
                throw new DukeException(errList.get("TaskAlrDone"));
            }
            else {
                toDoList.get(index).markAsDone();
                String input = "NAISUUUUUUU!!! One task off the list!!\n\t" + toDoList.get(index).getStatusIconAndDesc() + "\n";
                print(input);
            }
        }
        else {
            throw new DukeException(errList.get("TaskNotFound"));
        }
    }

    public static int toDoListSize() {
        return toDoList.size();
    }

    public static void deleteTask(int index) throws DukeException {
        if (toDoList.size() >= index) {
            print("Yessir! Task removed!!\n\t" + toDoList.get(index).getStatusIconAndDesc() + "\n" + toDoList.size() + " tasks to go!");
            toDoList.remove(index);

        }
        else {
            throw new DukeException(errList.get("TaskNotFound"));
        }
    }
}




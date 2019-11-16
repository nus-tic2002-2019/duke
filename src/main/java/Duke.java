import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String formattingLine1 = "____________________________________________________________\n";
        String formattingLine2 = "____________________________________________________________";
        String availableCommands = "Available commands: deadline, event, todo, list, delete, done, find, need, bye\n";
        String invalidCommand = "You have entered an invalid command, please try again\n";
        String deadlineUsage = "Command usage: deadline <task> /<yyyy>-<mm>-<dd>";
        String deleteUsage = "Command usage: delete <task number>";
        String todoUsage = "Command usage: todo <task number>";
        String eventUsage = "Command usage: event <task> /<date>";
        String doneUsage = "Command usage: done <number>";
        String findUsage = "Command usage: find <text to find>";
        String needUsage = "Command usage: need <task> /<time>";
        System.out.println("Hello from\n" + logo);
        System.out.println(formattingLine1 + "Hello! I'm Duke\n" + "What can I do for you?\n" + formattingLine1);
        Scanner input = new Scanner(System.in);
        String rawInput = "";
        String filteredInputCommand = "";
        String filteredInputText = "";
        String filteredInputDate = "";
        int filteredInputTime = 0;
        LocalDate ldInputDate = LocalDate.parse("1990-01-01");
        DateTimeFormatter inputdateFormat = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        ArrayList<String> TasksList = new ArrayList<String>();
        try (BufferedReader fileInput = new BufferedReader(new FileReader("backup.txt"))) {
            String arrayInput;
            while ((arrayInput = fileInput.readLine()) != null) {
                TasksList.add(arrayInput);
            }
            System.out.println(formattingLine1 + "Backup succesfully read, listing existing tasks:");
            System.out.println(formattingLine2);
            for (int i = 0; i < TasksList.size(); i++){
                System.out.println((i+1)+"."+TasksList.get(i));
            }
            System.out.println(formattingLine1);
        } catch (Exception e) {
            System.out.println(formattingLine1 + "Error reading from backup, new list created\n" + formattingLine1);
        }
        while ( !rawInput.equals("bye") ) {
            rawInput = input.nextLine();
            try {
                filteredInputCommand = rawInput.substring(0, rawInput.indexOf(' '));
            } catch (Exception e) {
                filteredInputCommand = rawInput;
            }
            try {
                filteredInputText = rawInput.substring(rawInput.indexOf(' '));
            } catch (Exception e) {
                switch ( filteredInputCommand ) {
                    case "delete":
                        System.out.println(formattingLine1 + deleteUsage);
                        filteredInputCommand = "";
                        break;
                    case "deadline":
                        System.out.println(formattingLine1 + deadlineUsage);
                        filteredInputCommand = "";
                        break;
                    case "todo":
                        System.out.println(formattingLine1 + todoUsage);
                        filteredInputCommand = "";
                        break;
                    case "event":
                        System.out.println(formattingLine1 + eventUsage);
                        filteredInputCommand = "";
                        break;
                    case "done":
                        System.out.println(formattingLine1 + doneUsage);
                        filteredInputCommand = "";
                        break;
                    case "find":
                        System.out.println(formattingLine1 + findUsage);
                        filteredInputCommand = "";
                        break;
                    case "need":
                        System.out.println(formattingLine1 + needUsage);
                        filteredInputCommand = "";
                        break;
                }
                //filteredInputText = "<No Text Entered>";
            }
            try {
                filteredInputText = filteredInputText.substring(0, (filteredInputText.indexOf('/')));
            } catch (Exception e) {
            }
            try {
                filteredInputDate = rawInput.substring(rawInput.indexOf('/'));
                filteredInputDate = filteredInputDate.substring(1);
                ldInputDate = LocalDate.parse(filteredInputDate);
                System.out.println("debug" + inputdateFormat.format(ldInputDate));
            } catch (Exception e) {
                //filteredInputDate = "<No Date Entered>";
                switch ( filteredInputCommand ) {
                    case "deadline":
                        System.out.println(formattingLine1 + deadlineUsage);
                        filteredInputCommand = "";
                        break;
                    case "event":
                        System.out.println(formattingLine1 + eventUsage);
                        filteredInputCommand = "";
                        break;
                }
            }
            try {
                filteredInputDate = rawInput.substring(rawInput.indexOf('/'));
                filteredInputDate = filteredInputDate.substring(1);
                filteredInputTime = Integer.parseInt(filteredInputDate);
            } catch (Exception e) {
                if (filteredInputCommand.equals("need")){
                    System.out.println(formattingLine1 + needUsage);
                    filteredInputCommand = "";
                }
            }
            switch ( filteredInputCommand ) {
                case "list":
                    System.out.println(formattingLine2);
                    for (int i = 0; i < TasksList.size(); i++){
                        System.out.println((i+1)+"."+TasksList.get(i));
                    }
                    System.out.println(formattingLine1);
                    break;
                case "find":
                    System.out.println(formattingLine2);
                    boolean missingText = true;
                    for (int i = 0; i < TasksList.size(); i++){
                        boolean foundText = TasksList.get(i).contains(filteredInputText);
                        if (foundText){
                            System.out.println((i+1)+"."+TasksList.get(i));
                            missingText = false;
                        }
                    }
                    if (missingText){
                        System.out.println("No results");
                    }
                    System.out.println(formattingLine1);
                    break;
                case "done":
                    try {
                        int doneInt = Integer.parseInt(filteredInputText.substring(1));
                        doneInt--;
                        String filteredList = TasksList.get(doneInt);
                        if ( filteredList.indexOf("][") == 2 ) {
                            TasksList.set(doneInt, (filteredList.substring(0, 3) + "[" + "\u2713" + "]" + filteredList.substring(6)));
                        } else {
                            TasksList.set(doneInt, ("[" + "\u2713" + "]" + filteredList.substring(3)));
                        }
                        System.out.println(formattingLine1 + "Nice! I've marked this task as done:" +
                                TasksList.get(doneInt) + formattingLine2);
                    } catch (Exception e) {
                        System.out.println(formattingLine1 + todoUsage);
                        System.out.println(availableCommands + invalidCommand + formattingLine2);
                    }
                    break;
                case "todo":
                    TasksList.add("[T][" + "\u2718" + "]" + filteredInputText);
                    System.out.println(formattingLine1 +
                            "Got it. I've added this todo:\n" + "[T][" + "\u2718" + "]" +
                            filteredInputText +"\n" + "Now you have " + TasksList.size() + " tasks in the list.\n" +
                            formattingLine1);
                    break;
                case "event":
                    TasksList.add("[E][" + "\u2718" + "]" + filteredInputText + " (at: " + inputdateFormat.format(ldInputDate) + ")");
                    System.out.println(formattingLine1 +
                            "Got it. I've added this event:\n" + "[E][" + "\u2718" + "]" + filteredInputText + " (at: " + inputdateFormat.format(ldInputDate) + ")" +"\n" +
                            "Now you have " + TasksList.size() + " tasks in the list.\n" +
                            formattingLine1);
                    break;
                case "deadline":
                    TasksList.add("[D][" + "\u2718" + "]" + filteredInputText + " (by: " + inputdateFormat.format(ldInputDate) + ")");
                    System.out.println(formattingLine1 +
                            "Got it. I've added this deadline:\n" + "[D][" + "\u2718" + "]" + filteredInputText + " (by: " + inputdateFormat.format(ldInputDate) + ")" +"\n" +
                            "Now you have " + TasksList.size() + " tasks in the list.\n" +
                            formattingLine1);
                    break;
                case "need":
                    TasksList.add("[D][" + "\u2718" + "]" + filteredInputText + " (by: " + inputdateFormat.format(ldInputDate) + ")");
                    System.out.println(formattingLine1 +
                            "Got it. I've added this need:\n" + "[D][" + "\u2718" + "]" + filteredInputText + " (need: " + filteredInputTime + ")" +"\n" +
                            "Now you have " + TasksList.size() + " tasks in the list.\n" +
                            formattingLine1);
                    break;
                case "delete":
                    try {
                        int deleteInt = Integer.parseInt(filteredInputText.substring(1));
                        deleteInt--;
                        String tempStorage = TasksList.get(deleteInt);
                        TasksList.remove(deleteInt);
                        System.out.println(formattingLine1 + 
                                "Noted. I've removed this task: " + tempStorage + "\n" +
                                "Now you have " + TasksList.size() + " tasks in the list.\n" +
                                formattingLine2);
                    } catch (Exception e) {
                        System.out.println(formattingLine1 + deleteUsage + availableCommands + invalidCommand + formattingLine2);
                    }
                    break;
                case "bye":
                    break;
                default:
                    System.out.println(availableCommands + invalidCommand + formattingLine2);
                    break;
            }
            try{
                FileWriter fileOutput = new FileWriter("backup.txt");
                for(String str: TasksList) {
                    fileOutput.write(str + System.lineSeparator());
                }
                fileOutput.close();
            } catch (Exception e) {
                System.out.println(formattingLine1 + "---WARNING WARNING WARNING WARNING WARNING---\nError writing to backup, your list is --> NOT <-- saved\n---WARNING WARNING WARNING WARNING WARNING---\n" + formattingLine1);
            }
        }
        System.out.println(formattingLine1 + "Bye. Hope to see you again soon!\n" + formattingLine2);
    }
}

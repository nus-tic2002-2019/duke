import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" + "Hello! I'm Duke\n" + "What can I do for you?\n" + "____________________________________________________________\n");
        Scanner input = new Scanner(System.in);
        String rawInput = "";
        String filteredInputCommand = "";
        String filteredInputText = "";
        String filteredInputDate = "";
        ArrayList<String> TasksList = new ArrayList<String>();
        try (BufferedReader fileInput = new BufferedReader(new FileReader("backup.txt"))) {
            String arrayInput;
            while ((arrayInput = fileInput.readLine()) != null) {
                TasksList.add(arrayInput);
            }
            System.out.println("____________________________________________________________\n" + "Backup succesfully read, listing existing tasks:");
            System.out.println("____________________________________________________________");
            for (int i = 0; i < TasksList.size(); i++){
                System.out.println((i+1)+"."+TasksList.get(i));
            }
            System.out.println("____________________________________________________________\n");
        } catch (Exception e) {
            System.out.println("____________________________________________________________\n" + "Error reading from backup, new list created\n" + "____________________________________________________________\n");
        }
        /*try {
            Scanner fileInput = new Scanner(new File("output.txt"));
            while (fileInput.hasNext()){
                TasksList.add(fileInput.next());
            }
            fileInput.close();
        } catch (Exception e) {
            System.out.println("____________________________________________________________\n" + "Error reading from file, new list created\n" + "____________________________________________________________\n");
        }*/
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
                filteredInputText = "<No Text Entered>";
                if (filteredInputCommand.equals("delete")){
                    System.out.println("____________________________________________________________\n" + "Command usage: delete <task number>");
                    filteredInputCommand = "";
                }
                if (filteredInputCommand.equals("deadline")){
                    System.out.println("____________________________________________________________\n" + "Command usage: deadline <task> /<date>");
                    filteredInputCommand = "";
                }
                if (filteredInputCommand.equals("todo")){
                    System.out.println("____________________________________________________________\n" + "Command usage: todo <task number>");
                    filteredInputCommand = "";
                }
                if (filteredInputCommand.equals("event")){
                    System.out.println("____________________________________________________________\n" + "Command usage: event <task> /<date>");
                    filteredInputCommand = "";
                }
                if (filteredInputCommand.equals("done")){
                    System.out.println("____________________________________________________________\n" + "Command usage: done <number>");
                    filteredInputCommand = "";
                }
            }
            try {
                filteredInputText = filteredInputText.substring(0, (filteredInputText.indexOf('/')));
            } catch (Exception e) {
            }
            try {
                filteredInputDate = rawInput.substring(rawInput.indexOf('/'));
                filteredInputDate = filteredInputDate.substring(1);
            } catch (Exception e) {
                filteredInputDate = "<No Date Entered>";
                if (filteredInputCommand.equals("deadline")){
                    System.out.println("____________________________________________________________\n" + "Command usage: deadline <task> /<date>");
                    filteredInputCommand = "";
                }
                if (filteredInputCommand.equals("event")){
                    System.out.println("____________________________________________________________\n" + "Command usage: event <task> /<date>");
                    filteredInputCommand = "";
                }
            }
            switch ( filteredInputCommand ) {
                case "list":
                    System.out.println("____________________________________________________________");
                    for (int i = 0; i < TasksList.size(); i++){
                        System.out.println((i+1)+"."+TasksList.get(i));
                    }
                    System.out.println("____________________________________________________________\n");
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
                        System.out.println("____________________________________________________________\n" + "Nice! I've marked this task as done:");
                        System.out.println(TasksList.get(doneInt));
                        System.out.println("____________________________________________________________");
                    } catch (Exception e) {
                        System.out.println("____________________________________________________________\n" + "Command usage: todo <task number>");
                        System.out.println("Available commands: deadline, event, todo, list, delete, bye\n" + "You have entered an invalid command, please try again\n" + "____________________________________________________________");
                    }
                    break;
                case "todo":
                    TasksList.add("[T][" + "\u2718" + "]" + filteredInputText);
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this todo:\n" + "[T][" + "\u2718" + "]" +
                            filteredInputText +"\n" + "Now you have " + TasksList.size() + " tasks in the list.\n" +
                            "____________________________________________________________\n");
                    break;
                case "event":
                    TasksList.add("[E][" + "\u2718" + "]" + filteredInputText + " (at: " + filteredInputDate + ")");
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this event:\n" + "[E][" + "\u2718" + "]" + filteredInputText + " (at: " + filteredInputDate + ")" +"\n" +
                            "Now you have " + TasksList.size() + " tasks in the list.\n" +
                            "____________________________________________________________\n"); break;
                case "deadline":
                    TasksList.add("[D][" + "\u2718" + "]" + filteredInputText + " (by: " + filteredInputDate + ")");
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this deadline:\n" + "[D][" + "\u2718" + "]" + filteredInputText + " (by: " + filteredInputDate + ")" +"\n" +
                            "Now you have " + TasksList.size() + " tasks in the list.\n" +
                            "____________________________________________________________\n"); break;
                case "delete":
                    try {
                        int deleteInt = Integer.parseInt(filteredInputText.substring(1));
                        deleteInt--;
                        String tempStorage = TasksList.get(deleteInt);
                        TasksList.remove(deleteInt);
                        System.out.println("____________________________________________________________\n" + "Noted. I've removed this task:");
                        System.out.println(tempStorage);
                        System.out.println("Now you have " + TasksList.size() + " tasks in the list.\n");
                        System.out.println("____________________________________________________________");
                    } catch (Exception e) {
                        System.out.println("____________________________________________________________\n" + "Command usage: delete <task number>");
                        System.out.println("Available commands: deadline, event, todo, list, delete, bye\n" + "You have entered an invalid command, please try again\n" + "____________________________________________________________");
                    }
                    break;
                case "bye": break;
                default:
                    System.out.println("Available commands: deadline, event, todo, list, delete, bye\n" + "You have entered an invalid command, please try again\n" + "____________________________________________________________");
                    break;
            }
            try{
                FileWriter writer = new FileWriter("backup.txt");
                for(String str: TasksList) {
                    writer.write(str + System.lineSeparator());
                }
                writer.close();
            } catch (Exception e) {

            }

        }
        System.out.println("____________________________________________________________\n" + "Bye. Hope to see you again soon!\n" + "____________________________________________________________");
    }
}

/*public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" + "Hello! I'm Duke\n" + "What can I do for you?\n" + "____________________________________________________________\n");
        Scanner input = new Scanner(System.in);
        String text = "";
        String filteredtext = "";
        ArrayList<String> list = new ArrayList<String>();
        while ( !text.equals("bye") ) {
            text = input.nextLine();
            try {
                filteredtext = text.substring(0, text.indexOf(' '));
            } catch (Exception e) {
                filteredtext = text;
            }
            if (filteredtext.equals("done")){
                System.out.println("____________________________________________________________\n" + "Nice! I've marked this task as done:");
                String getTaskNum = text.substring(5);
                int doneInt = Integer.parseInt(getTaskNum);
                doneInt--;
                String filteredList = list.get(doneInt);
                //System.out.println(filteredList);
                //System.out.println(filteredList.indexOf("]["));
                //int newFilter = filteredList.indexOf("][");
                if ( filteredList.indexOf("][") == 2 ) {
                    list.set(doneInt, (filteredList.substring(0, 3) + "[" + "\u2713" + "]" + filteredList.substring(6)));
                } else {
                    list.set(doneInt, ("[" + "\u2713" + "]" + filteredList.substring(3)));
                }
                System.out.println(list.get(doneInt));
                System.out.println("____________________________________________________________");
            }

            switch ( filteredtext ) {
                case "bye":  System.out.println("____________________________________________________________\n" + "Bye. Hope to see you again soon!\n" + "____________________________________________________________"); break;
                case "list": System.out.println("____________________________________________________________"); for (int i = 0; i < list.size(); i++){System.out.println((i+1)+"."+list.get(i));} System.out.println("____________________________________________________________\n"); break;
                case "done": break;
                case "todo": list.add("[T][" + "\u2718" + "]" + text); System.out.println("____________________________________________________________\n" + "Got it. I've added this task:\n" + "[T][" + "\u2718" + "]" + text +"\n" + "Now you have " + list.size() + " tasks in the list.\n" + "____________________________________________________________\n"); break;
                case "event": list.add("[E][" + "\u2718" + "]" + text); System.out.println("____________________________________________________________\n" + "Got it. I've added this task:\n" + "[E][" + "\u2718" + "]" + text +"\n" + "Now you have " + list.size() + " tasks in the list.\n" + "____________________________________________________________\n"); break;
                case "deadline": list.add("[D][" + "\u2718" + "]" + text); System.out.println("____________________________________________________________\n" + "Got it. I've added this task:\n" + "[D][" + "\u2718" + "]" + text +"\n" + "Now you have " + list.size() + " tasks in the list.\n" + "____________________________________________________________\n"); break;
                default: System.out.println("____________________________________________________________\n" + "added: " + text +"\n" + "____________________________________________________________\n"); list.add("[" + "\u2718" + "]" + text);
            }
        }
    }
}*/

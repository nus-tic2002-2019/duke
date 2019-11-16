import java.util.Scanner;
import java.util.ArrayList;

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
                if (filteredInputCommand.equals("deadline")){
                    System.out.println("Command usage: deadline <task> /<date>");
                    filteredInputCommand = "";
                }
                if (filteredInputCommand.equals("todo")){
                    System.out.println("Command usage: todo <task>");
                    filteredInputCommand = "";
                }
                if (filteredInputCommand.equals("event")){
                    System.out.println("Command usage: event <task> /<date>");
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
                    System.out.println("Command usage: deadline <task> /<date>");
                    filteredInputCommand = "";
                }
                if (filteredInputCommand.equals("event")){
                    System.out.println("Command usage: event <task> /<date>");
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
                case "bye": break;
                default:
                    System.out.println("Available commands: deadline, event, todo, list, bye\n" + "You have entered an invalid command, please try again\n");
                    break;
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

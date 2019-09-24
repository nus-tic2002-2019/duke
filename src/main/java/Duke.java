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
}

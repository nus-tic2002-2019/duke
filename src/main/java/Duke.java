import java.util.Scanner;


public class Duke {
    private static void DukeReply(String replies) { // DukeReplying format, only changing variable is replies/ input from main function.
        System.out.println("\t________________________________________________________________\n" + "\t" + replies + "\n\t________________________________________________________________\n");
    }

    private static String DukeList(Task[] listing) { // Listing function: go through every string element and concatenate them together to become a huge string return the huge string back to main function.
        int index = 1;
        String answer = "";
        for (int i = 0; i < listing.length; i++) {
            answer = (answer + index + "." + listing[i].getType() + listing[i].getTaskStatus() + " " + listing[i].getTask() + " " + listing[i].getDetails());
            index++;
            if (listing[i + 1] == null) {
                break;
            }
            answer += "\n\t";
        }
        return answer;
    }

    private static String DukeListDone(Task[] listing, int index) {
        listing[index - 1].setTaskDone();
        return listing[index-1].getType() + listing[index - 1].getTaskStatus() + " " + listing[index - 1].getTask();
    }

    public static void main(String[] args) {
        int count = 0;
        Task[] history = new Task[100];
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        DukeReply("Hello! I'm Duke\n\tWhat can i do for you?");
        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            input = input.trim();
            if (input.isEmpty()) { // if input is empty space or just enter, i will ignore it and not add to the list
                continue;
            }
            if (input.equalsIgnoreCase("bye")) { // if user enters "bye", we will exit the program
                DukeReply("Bye. Hope to see you again soon!");
                break;
            }
            if (input.toLowerCase().contains("done")) {
                if (input.length() < 5 || input.charAt(4) != ' ' || input.charAt(5) == ' ') { // Handling errors: When user types done1 done2 or done   2
                    DukeReply("You've entered a value more than the number of task list, please enter a valid task number again.");
                    continue;
                }
                String number = input.substring(5);
                int index = Integer.parseInt(number);
                if (index > count){ //Handling Errors: When user keys in index which is more than the list it has.
                    input = "You've entered a value more than the number of task list, please enter a valid task number again.";
                }
                else {
                    input = "Nice! I've marked this task as done: \n\t" + DukeListDone(history, index);
                }
                DukeReply(input);
                continue;
            }
            if (input.equalsIgnoreCase("list")) { // if the user enters "list", it will list out everything, else add to the list. refer number 5.
                if (count ==0) {
                    DukeReply("There's nothing in the list.");
                    continue;
                }
                input = "Here are the Tasks in your list:\n\t" + DukeList(history);
            }
             // add the other words into the list
            else {
                int idx = input.indexOf('/');
                if (input.toLowerCase().contains("todo")) {
                    history[count] = new Todo(input.substring(5));
                } else if (input.toLowerCase().contains("deadline")) {
                    history[count] = new Deadlines(input.substring(9, idx - 1), input.substring(idx+4));
                } else if (input.toLowerCase().contains("event")) {
                    history[count] = new Events(input.substring(6, idx -1), input.substring(idx+4));
                } else {
                    DukeReply("This part needs to do handling in the future, please type a better keyword.");
                    continue;
                }
                input = "Got it. I've added this task:\n\t\t" + history[count].getType() + history[count].getTaskStatus() + " " + history[count].getTask() + " " + history[count].getDetails() + "\n\tNow you have " + (count + 1) + " tasks in the list.";
                count++;

                //  history[count] = new Task(input);
                //  count++;
                //  input = "added: " + input;
            }
            DukeReply(input);
        }
    }
}
/* idea from improvement to this chatbot
1. Wrap around function if input more than 100 entries?
2. Wrap around or ignore entry?
3. Handling errors in inputs by user?
4. if users type 'Done' only, allow user to type in value of task in the list using scanner input.
we will leave it to the future.
5. when list is empty, user types list? Error handling Null element of history
6. when users just types todo with no other input. Error handling Null element of history
 */



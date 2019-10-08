import java.util.Scanner;

public class InteractionsManager{
    public void start() throws DukeException {
        System.out.println("     Hello! I'm Duke. What can I do for you?\n");
        Operations operations = new Operations();
        while(true) {
            String userCommand = getUserCommand();
            if (userCommand.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            else if (userCommand.equals("list")){
                operations.printList();
            }
            // expects input in the format "done 4"
            else if (userCommand.startsWith("done") ){
                // grab the int the user input
                String lastValue = userCommand.substring(userCommand.length()-1);
                int targetToSetDone = Integer.parseInt(lastValue);
                operations.setDone(targetToSetDone);
            }
            else{
                // Check if user is adding ToDo, Deadline, or Event in Operations.java
                    String taskType = checkTaskType(userCommand);
                    String desc = getDesc(userCommand);
                    String by = getBy(userCommand);
                    String at = getAt(userCommand);


                    if (taskType.equals("todo")){
                        Todo toAdd = new Todo(desc);
                        operations.add(toAdd);
                    }
                    else if (taskType.equals("deadline")){
                        Deadline toAdd = new Deadline(desc, by);
                        operations.add(toAdd);
                    }
                    else if (taskType.equals("event")){
                        Event toAdd = new Event(desc, at);
                        operations.add(toAdd);
                    }
                    else{
                        //throw new DukeException();
                    }



            }

        }
    }

    private String getEnd(String userCommand) {
        return " ";
    }

    private String getAt(String userCommand) {
        String removedFirstWord = userCommand.split(" ", 2)[1];
        String at;
        if (userCommand.contains("/at")){  //
            at = removedFirstWord.split("/at", 2)[1];

            return at;
        }
        return " ";
    }


    private String getBy(String userCommand) {
        String removedFirstWord = userCommand.split(" ", 2)[1];
        String by;
        if (userCommand.contains("/by")){  //
            by = removedFirstWord.split("/by", 2)[1];
        }
        else{
            by = " No deadline";
        }
        return by;
    }

    private String getDesc(String userCommand) {
        String removedFirstWord = userCommand.split(" ", 2)[1];
        String desc;
        if (userCommand.contains("/")){  // get the text before the "/"
            desc = removedFirstWord.split("/", 2)[0];
        }
        else{
            desc = removedFirstWord;
        }
        return desc;
    }

    private String getUserCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private String checkTaskType(String userCommand){
        // sample inputs:
        // ['todo borrow book'
        //  "deadline return book /by Sunday",
        //  "event project meeting /at Mon 2-4pm"]
        String taskType = userCommand.split(" ")[0];
        taskType = taskType.toLowerCase();
        return taskType;
    }
}

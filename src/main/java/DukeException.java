public class DukeException extends Exception {

    public DukeException(){}

    public DukeException(String textInput){
        switch(textInput) {
            case "done":
                System.out.println("☹ OOPS!!! You must indicate which task is done");
                break;
            case "todo":
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                break;
            case "deadline":
                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                break;
            case "event":
                System.out.println("☹ OOPS!!! The description of an event cannot be empty");
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}

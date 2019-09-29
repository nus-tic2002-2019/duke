public class DukeException extends Exception {

    public DukeException(){}

    public DukeException(String textInput){
        switch(textInput) {
            case "done":
                System.out.println("☹ OOPS!!! You must indicate which task is done");
                break;
            case "delete":
                System.out.println ("☹ OOPS!!! You must indicate which task to delete");
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
            case "/by":
                System.out.println("☹ OOPS!!! You did not specify a specific date/time for deadline. Please use /by date/time");
                break;
            case "/at":
                System.out.println("☹ OOPS!!! You did not specify a specific start/end date/time for event. Please use /at date/time");
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


}

public class Parser {

    public static Command parse(String command) throws DukeException{
        Command c = null;
        String function = command.split(" ")[0];
        switch(function) {
            case "bye":
                c = new ExitCommand();
                break;

            case "list":
                c = new ListCommand();
                break;

            case "done":
                if(command.length()>5) {
                    int index = Integer.parseInt(command.substring(command.indexOf(" ")+1))-1;
                    c = new UpdateCommand(index);
                } else
                    throw new DukeException("☹ OOPS!!! The description of a marking a task as done cannot be empty.");
                break;
            case "delete":
                if(command.length()>7) {
                    int index = Integer.parseInt(command.substring(command.indexOf(" ")+1))-1;
                    c = new DeleteCommand(index);
                } else
                    throw new DukeException("☹ OOPS!!! The description of a delete cannot be empty.");
                break;

            case "todo":
                if(command.length()>5) {
                    Task task = new Todo(command.substring(5));
                    c = new AddCommand(task);
                } else
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                break;

            case "event":
                if(command.length()>6) {
                    if(command.contains("/")) {
                        String name = command.substring(6,command.indexOf("/")-1);
                        String dateTime = command.substring(command.indexOf("/")+3);
                        Task task = new Event(name,dateTime);
                        c = new AddCommand(task);
                    } else
                        throw new DukeException("☹ OOPS!!! Missing date/time for event.");
                } else
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                break;

            case "deadline":
                if(command.length()>9) {
                    if(command.contains("/")) {
                        String name = command.substring(9,command.indexOf("/")-1);
                        String dateTime = command.substring(command.indexOf("/")+3);
                        Task task = new Deadline(name,dateTime);
                        c = new AddCommand(task);
                    } else
                        throw new DukeException("☹ OOPS!!! Missing date/time for deadline.");
                } else
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                //c = new AddCommand();
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return c;
    }
}
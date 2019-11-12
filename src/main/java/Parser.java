public class Parser {
    static Command parse(String command) throws DukeException{
        String[] task = command.split(" ",2);
        int taskLen = task.length;
        command = task[0];
        switch (command){
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "done":
                return new DoneCommand(Integer.parseInt(task[1]));
            case "delete":
                if(taskLen < 2) {
                    throw new DukeException(command);
                } else {
                    return new DeleteCommand(Integer.parseInt(task[1]));
                }
            case "todo":
                if(taskLen < 2) {
                    throw new DukeException(command);
                } else {
                    return new AddCommand(new ToDo(task[1]));
                }
            case "event":
                if(taskLen < 2) {
                    throw new DukeException(command);
                } else {
                    String[] descrip = task[1].split(" /at ", 2);
                    return new AddCommand(new Event(task[1],task[2]));
                }
            case "deadline":
                if(taskLen < 2) {
                    throw new DukeException(command);
                } else {
                    String[] descrip = task[1].split(" /by ", 2);
                    return new AddCommand(new Deadline(task[1],task[2]));
                }
            default:
                throw new DukeException();
        }
    }
}

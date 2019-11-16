/*
 *  Parser.java
 *  Defines the flow of commands parse from user's input.
 */

public class Parser {
    /*
     * @param command Command from user's input.
     * @return Command object.
     * @throws DukeException Exception handled.
     */
    static Command parse(String command) throws DukeException{
        //task split by the first spacing
        String[] task = command.split(" ",2);

        //stores task size
        int taskLen = task.length;

        //command defined by user
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
                    AddCommand addCommand = new AddCommand(new Event(descrip[0], descrip[1]));
                    return addCommand;

                }
            case "deadline":
                if(taskLen < 2) {
                    throw new DukeException(command);
                } else {
                    String[] descrip = task[1].split(" /by ", 2);
                    AddCommand addCommand = new AddCommand(new Deadline(descrip[0], descrip[1]));
                    return addCommand;
                }
            default:
                throw new DukeException();
        }
    }
}

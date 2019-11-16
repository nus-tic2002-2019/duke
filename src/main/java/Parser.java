public class Parser{
    public static Command parseInput(String userInput) throws DukeException{
        String input = userInput;
        switch(input.split(" ")[0]){
            case "deadline":
                return new DeadlineCommand(input);
            case "event":
                return new EventCommand(input);
            case "todo":
                return new ToDoCommand(input);
            case "list":
                return new ListCommand("");
            case "delete":
                return new DeleteCommand(input);
            case "done":
                return new DoneCommand(input);
            case "find":
                return new FindCommand(input);
            case "bye":
                return new ByeCommand("");  
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");   
        }
    }
} 
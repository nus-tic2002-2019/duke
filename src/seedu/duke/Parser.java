public class Parser{
    public static Command parseInput(String userInput) throws DukeException{
        String input = userInput;

        switch(input.split(" ")[0]){
            case AddDeadlineCommand.INPUT_WORD:
                return new AddDeadlineCommand(false, input);
            case AddEventCommand.INPUT_WORD:
                return new AddEventCommand(false, input);
            case AddToDoCommand.INPUT_WORD:
                return new AddToDoCommand(false, input);
            case ListCommand.INPUT_WORD:
                return new ListCommand(false, "");
            case DeleteCommand.INPUT_WORD:
                return new DeleteCommand(false, input);
            case DoneCommand.INPUT_WORD:
                return new DoneCommand(false, input);
            case ByeCommand.INPUT_WORD:
                return new ByeCommand(true, "");  
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");   
        }
    }
}
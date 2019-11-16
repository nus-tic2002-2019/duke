import java.io.IOException;

public class DeadlineCommand extends Command{
    
    private Deadline deadline;

    public DeadlineCommand(String input){
        super(input);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws  DukeException, IOException{
        try {
            if (input.split(" ")[3] == "☹ OOPS!!! The date of a deadline cannot be empty.") {
                throw new DukeException(input);
            }
            deadline = new Deadline(input.split(" ")[1],input.split(" ")[3]);
            TaskList.addList(deadline);
            ui.showOutputToUser("Got it. I've added this task:\n\t  " + deadline.toString() + "\n\t Now you have " + TaskList.getSize() + " tasks in the list.");
            storage.saveToFile();
        } catch (IndexOutOfBoundsException | DukeException e) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }
} 
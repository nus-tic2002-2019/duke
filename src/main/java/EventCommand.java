import java.io.IOException;

public class EventCommand extends Command{

    private Event event;

    public EventCommand(String input){
        super(input);
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException{
        try {
            if (input.split(" ")[3] == "") {
                throw new DukeException("☹ OOPS!!! The date of a event cannot be empty.");
            }
            event = new Event(input.split(" ")[1],input.split(" ")[3]);
            TaskList.addList(event);
            ui.showOutputToUser("Got it. I've added this task:\n\t  " + event.toString() + "\n\t Now you have " + TaskList.getSize() + " tasks in the list.");
            storage.saveToFile();
        } catch (IndexOutOfBoundsException | DukeException e) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }
} 
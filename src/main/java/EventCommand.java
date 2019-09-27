public class EventCommand extends Command{

    public EventCommand(String fullCommand){
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{

        if (fullCommand.isEmpty()){
            throw new DukeException("☹ OOPS!!! Empty description for EVENT");
        }else if(!fullCommand.contains(" / ")){
            throw new DukeException("☹ OOPS!!! Missing keyword / for EVENT");
        }
        int dividerPosition = fullCommand.indexOf("/");
        String itemName = fullCommand.substring(5, dividerPosition);
        String itemName1 = fullCommand.substring(dividerPosition,fullCommand.length());
        String itemName2 = itemName1.replace("/", "");

        tasks.addTask(new Event(itemName,itemName2));
        ui.changed();
    }
}

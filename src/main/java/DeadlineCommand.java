public class DeadlineCommand extends Command{

    public DeadlineCommand(String fullCommand){
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{

        if (fullCommand.isEmpty()){
            throw new DukeException("☹ OOPS!!! Empty description for DEADLINE");
        }else if(!fullCommand.contains(" / ")){
            throw new DukeException("☹ OOPS!!! Missing keyword / for DEADLINE");
        }

        int dividerPosition = fullCommand.indexOf("/");
        String itemName = fullCommand.substring(8, dividerPosition);
        String itemName1 = fullCommand.substring(dividerPosition,fullCommand.length());
        String itemName2 = itemName1.replace("/", "");

        tasks.addTask(new Deadline(itemName,itemName2));
        ui.changed();
    }
}

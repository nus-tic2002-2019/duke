import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeadlineCommand extends Command{

    public DeadlineCommand(String fullCommand){
        super(fullCommand);
    }

    public static Date convertDeadline(String deadline){
        SimpleDateFormat format = new SimpleDateFormat ("dd-MM-yyyy HH:mm:ss");
        Date date;
            try {
                date = format.parse(deadline);
                return date;
            } catch (ParseException e) {
                System.out.println("Unable to parse using " + format);
                System.out.println("Please use format \"dd-MM-yyyy HH:mm:ss\" ");
                System.out.println("Default date will be inserted instead.");
            }
        return new Date();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{

        if (fullCommand.isEmpty()){
            throw new DukeException("☹ OOPS!!! Empty description for DEADLINE");
        }else if(!fullCommand.contains(" / ")){
            throw new DukeException("☹ OOPS!!! Missing keyword / for DEADLINE");
        }

        int dividerPosition = fullCommand.indexOf("/");
        String itemName = fullCommand.substring(9, dividerPosition);
        String itemName1 = fullCommand.substring(dividerPosition,fullCommand.length());
        String itemName2 = itemName1.replace("/", "");

        tasks.addTask(new Deadline(itemName,convertDeadline(itemName2)));
        ui.changed();
    }
}

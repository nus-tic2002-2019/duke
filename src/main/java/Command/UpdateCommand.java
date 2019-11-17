package duke;

import Command.Command;

import java.time.LocalDateTime;
/**
* This is the command that handles the updating of task.
*
* @author Eunice Kwang
*/
public class UpdateCommand extends Command {
	/**
	* The index of the task that is going to be updated
	*/
    private int index;

    public UpdateCommand(int index) {
        this.index = index;
    }
    @Override
    /**
     * Searches for the specific task and updates the values of the task.
     * @param   tasks Array of tasks stored in an ArrayList.
     * @param   ui User Interface (UI) to allow interaction with the user.
     * @param   storage storage allow storing and reading of tasks from file.
	 * @throws DukeException  If the application is unable to save.
     */
    public void execute(Tasklist tasks, UI ui, Storage storage) throws DukeException {
        if(index < tasks.getSize()) {
            Task task = tasks.getTask(index);
            int taskTypeIndex;
            
            if(task instanceof Todo) {
            	taskTypeIndex = 1;
            } 
            else if (task instanceof Recurring) {
            	taskTypeIndex = 3;
            } 
            else
            	taskTypeIndex = 2;
            
            ui.chooseUpdate(taskTypeIndex);
            
            int detailIndex = Integer.parseInt(ui.readCommand());
            ui.updateValue();
            String input = ui.readCommand();
            switch (detailIndex) {
                case 1:
                    task.setDescription(input);
                    break;
                case 2:
                    if(task instanceof Event){
                        Event event = (Event) task;
                        LocalDateTime dateTime = Parser.convertToDateTime(input);
                        LocalDateTime now = LocalDateTime.now();
                        if (dateTime.isAfter(now)) {
                            event.setAt(dateTime);
                        }
                        else
                            throw new DukeException("☹ OOPS!!! Date time cannot be earlier than now.");
                    } else if(task instanceof Deadline){
                        Deadline deadline = (Deadline) task;
                        LocalDateTime dateTime = Parser.convertToDateTime(input);
                        LocalDateTime now = LocalDateTime.now();
                        if (dateTime.isAfter(now)) {
                            deadline.setBy(dateTime);
                        }
                        else
                            throw new DukeException("☹ OOPS!!! Date time cannot be earlier than now.");
                    } else if(task instanceof Recurring) {
                    	Recurring recurring = (Recurring) task;
                    	LocalDateTime dateTime = Parser.convertToDateTime(input);
                        LocalDateTime now = LocalDateTime.now();
                        if (dateTime.isAfter(now)) {
                            recurring.setOn(dateTime);
                        }else
                            throw new DukeException("☹ OOPS!!! Date time cannot be earlier than now.");
                    }
                    else
                        throw new DukeException("☹ OOPS!!! Invalid Choice");
                    break;
                case 3:
                	if(task instanceof Recurring) {
                		Recurring recurring = (Recurring) task;
                		recurring.setType(input);
                	}else
                        throw new DukeException("☹ OOPS!!! Invalid Choice");
                	break;
                case 4:
                	if(task instanceof Recurring) {
                		Recurring recurring = (Recurring) task;
                		recurring.setDuration(Integer.parseInt(input));
                	}else
                        throw new DukeException("☹ OOPS!!! Invalid Choice");
                	break;
                default:
                    throw new DukeException("☹ OOPS!!! The description of a updating a task is wrong.");
            }
            ui.showUpdate(task);
        }else
            throw new DukeException("☹ OOPS!!! The description of a updating a task is not found.");
        try {
            storage.save(tasks);
        } catch(DukeException e) {
            throw e;
        }
    }

    /**
	 * Return false as system is not ready to exit.
     * @return false.
     */
	@Override
    public boolean isExit() {
        return false;
    }
}

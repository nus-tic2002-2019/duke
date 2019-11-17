package main.java.duke.commands;


/**
 * List all the tasks in the taskList.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD="list";

    public static final String MESSAGE_USAGE="||"+COMMAND_WORD+": list the all the tasks in the task list." ;


    @Override
    public void execute(){
        if(taskList.getSize()==0){
            System.out.println("There isn't any tasks in the list.");
            return;
        }
        for (int i = 1; i <= taskList.getSize(); i++) {
            System.out.println(i + ". " + taskList.getTaskByIdx(i).toString());
        }
    }
}

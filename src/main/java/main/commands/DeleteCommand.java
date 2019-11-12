package main.commands;

import main.DukeException;
import main.taskLists.Task;
import main.UI;

import static main.Duke.Tasks;

public class DeleteCommand extends Command<Integer> {


    public  DeleteCommand(Integer input) throws DukeException {
        this.execute(input);
    }

    @Override
    public void execute(Integer input) throws DukeException {

        try{
            Task t = Tasks.get(input - 1);
            Tasks.remove(input - 1);
            UI.deletedCommand(t);
        } catch (Exception e) {
            System.out.println("\tI can't delete something that does not exist. Maybe try 'list' to list current tasks?");
        }



    }


}

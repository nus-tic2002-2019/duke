package main.commands;

import main.UI;
import main.taskLists.Task;

import java.io.IOException;

import static main.Duke.Tasks;


public class AddCommand extends Command<Task> {

    /**
     * Public Method to add Tasks into ArrayList and .txt file
     *
     * @param input the Task to be added
     * @throws IOException IOException will be thrown of there is an error in writing to .txt
     */
    public AddCommand(Task input) throws IOException {
        this.execute(input);
    }

    @Override
    public void execute(Task input) throws IOException {

        Tasks.add(input);
        UI.addedCommand(input);

    }

}

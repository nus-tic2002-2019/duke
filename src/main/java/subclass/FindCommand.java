/*
Find command, displaying tasks containing input words by user
 */

package subclass;

public class FindCommand extends Command {

    public static final String INPUT = "find";
    public String input_word = "";

    public FindCommand(boolean isExit, String input) {
        super(isExit, input);
    }

    public void execute(TaskList taskList, Ui ui) throws DukeException {
        try {
            input_word = Parser.parseTask(input);
            if (Task.findTask(input_word).equals("")){
                throw new IndexOutOfBoundsException();
            }
            Ui.showLine();
            System.out.println("\tHere are the matching tasks in the list:");
            System.out.println(Task.findTask(input_word));
            Ui.showLine();
        } catch (IndexOutOfBoundsException e) {
            Ui.showLine();
            Ui.showError("No matching tasks found.");
            Ui.showLine();
        }
    }


}

/*
Simple help menu display when "help" command is entered.
 */


package subclass;

public class HelpCommand extends Command {

    public static final String INPUT = "help";

    public HelpCommand(boolean isExit, String input) {
        super(isExit, input);
    }

    public void execute(TaskList taskList, Ui ui) {
        Ui.showLine();
        System.out.println("\n\tCommands available:");
        System.out.println("\t1. todo          adding simple tasks with only description");
        System.out.println("\t\tUsage: todo [description]");
        System.out.println("\n\t2. deadline          adding tasks with deadlines to meet");
        System.out.println("\t\tUsage: deadline [DESCRIPTION] /by [DATE]");
        System.out.println("\t\tNote: DATE must be in format: dd-MMM-yy hh:mm PM/AM");
        System.out.println("\n\t3. event          adding event tasks with duration");
        System.out.println("\t\tUsage: event [DESCRIPTION] /at [DATE, e.g. Mon 2-4pm]");
        System.out.println("\n\t4. list          displaying list of tasks entered");
        System.out.println("\t\tUsage: list");
        System.out.println("\n\t5. done          mark task as done");
        System.out.println("\t\tUsage: done [TASK INDEX]");
        System.out.println("\n\t6. delete          deleting task from list");
        System.out.println("\t\tUsage: delete [TASK INDEX]");
        System.out.println("\n\t7. find          find task(s) containing word");
        System.out.println("\t\tUsage: find [INPUT]");
        System.out.println("\n\t8. bye          exit Duke");
        System.out.println("\t\tUsage: bye");
        Ui.showLine();
    }

}

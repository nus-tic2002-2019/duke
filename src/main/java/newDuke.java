import newDuke.DukeCommands.*;
import newDuke.main.*;
import newDuke.DukeTasks.*;
import newDuke.DukeExceptions.Exception;
import java.io.IOException;
import java.util.*;

/**
 * Duke runs as the main and this is where all the work is initialised at.
 */

public class newDuke {

    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs an instance of Duke and instantiates Storage and TaskList.
     */

    public newDuke() {
		try {
			storage = new Storage();
			try{ 
				ArrayList<Task> list = storage.readFromFile();
				this.taskList = new TaskList(list);
			} catch (ArrayIndexOutOfBoundsException e){
				throw new Exception(Exception.Code.FILE_CONTENTS_FORMAT_ERROR);
			}
		} catch (Exception e){
			System.out.println(e.errorDescription());
		}
    }

    /**
     * Handles the parsing of inputs and execution of parsed commands.
     *
     * As long as a ByeCommand is not parsed, Duke will continue parsing for more commands. If a ByeCommand is
     * parsed, the while loop is terminated and Duke terminates.
     *
     * @throws IOException When the Parser f
     */

    private String run(String command) throws IOException {
		// System.out.println(UI.bye());
        Parser parser = new Parser();
        // Scanner sc = new Scanner(System.in);
        Command c = parser.parse(command);
        return c.execute(taskList, storage);
		//return "test";

    }
	public static void main(String[] args) {
		String line;	
		while (true){
			newDuke duke = new newDuke();
			Scanner in = new Scanner(System.in);
			line = in.nextLine();
			try {
				System.out.println(duke.run(line));
			} catch (IOException io) {
				System.err.println(io);
			}
			if (line.equals("bye")){
				break;
			}
		}
	}
}

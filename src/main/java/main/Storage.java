package newDuke.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import newDuke.DukeTasks.*;

/**
 * Manages the storing of the TaskList to a file on the hard disk. This allows the list of Tasks to be stored and
 * accessed for subsequent initialisations of Duke.
 */
public class Storage {

    private Path path = Paths.get(System.getProperty("user.dir"));
    private File data = new File(path + "/data/duke.txt");
	private static final int INDEXOFTASKDESCRIPTION = 4;
    public Storage() {

    }
    /**
     * Returns an ArrayList of tasks from the data file on the hard disk.
     *
     * <p></p>This method is invoked whenever an instance of Duke is created. It reads from the data/duke.txt file.
     * Should the file not be found, a FileNotFoundException is thrown and caught. A parser is also implemented in this
     * method to read from the data file.
     *
     * @return An ArrayList of Tasks from the stored data file.

     */
	public ArrayList<Task> readFromFile() {

        ArrayList<Task> toReturn = new ArrayList<>();
        try {
            Scanner sc = new Scanner(data);
            while (sc.hasNextLine()) {

                String[] next = sc.nextLine().split(" | ");
                switch (next[0]) {
                case ("T"):
					String todoName = "";
					for (int a = INDEXOFTASKDESCRIPTION; a < next.length; a++) {
						todoName += next[a] + " ";
					}
                    Task todo = new Todo(todoName);
                    if (Integer.valueOf(next[2]) == 1) {
                        todo.setStatusIconTrue();
                    }
                    toReturn.add(todo);
					
                    break;
                case ("E"):
				String eventName = "";
				String venue = "";
				for (int a = INDEXOFTASKDESCRIPTION; a < next.length; a++) {
					if (!next[a].equals("|")){
						eventName += next[a];
						eventName += " ";
					} else {
						a++;
						for (int parameterIndex = a; parameterIndex < next.length; parameterIndex++){
							venue += next[parameterIndex];
							if (parameterIndex == next.length -1){
								break;
							} else {
								venue += " ";
							}
						}
						break;
					}
				}
				Task event = new Event(eventName, venue);
				if (Integer.valueOf(next[2]) == 1) {
					event.setStatusIconTrue();
				}
				toReturn.add(event);
				break;

                case ("D"):
				String deadlineName = "";
				String date = "";
				for (int a = INDEXOFTASKDESCRIPTION; a < next.length; a++) {
					if (!next[a].equals("|")){
						deadlineName += next[a];
						deadlineName += " ";
					} else {
						a++;
						for (int parameterIndex = a; parameterIndex < next.length; parameterIndex++){
							date += next[parameterIndex];
							if (parameterIndex == next.length -1){
								break;
							} else {
								date += " ";
							}
						}
						break;
					}
				}
				Task deadline = new Deadline(deadlineName, date);
				if (Integer.valueOf(next[2]) == 1) {
					deadline.setStatusIconTrue();
				}
				toReturn.add(deadline);
				break;

                default:
                    break;
                }
            }
            sc.close();

        } catch (FileNotFoundException fileExp) {
            System.out.println(fileExp);
        }
        return toReturn;
    }

	/**
     * Writes all the tasks in a given ArrayList into the data/duke.txt file.
     *
     * <p></p>A StringBuilder is used to concatenate all the strings from the writetoFile() method of each Task. This is then
     * stored into the data file.
     *
     * @param list ArrayList of all the Tasks to be written into the data file.
     */
	public void writeToFile(ArrayList<Task> list) {

        try {
            FileWriter fw = new FileWriter(data);
            StringBuilder toWrite = new StringBuilder("");

            for (Task task : list) {
                toWrite.append(task.toSave() + " \n");
            }

            fw.write(toWrite.toString());
            fw.close();

        } catch (IOException ioExp) {
            System.err.println(ioExp);
        }
    }
}

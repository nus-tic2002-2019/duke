package Storage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import Tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    /**
     * Default path of file.
     */
    public static final String DEFAULT_PATH = "D:/NUS_STUFF/Java_Mod/duke/data/dukeTaskOuput.txt";

    /**
     * Storage class Constructor
     */
    public Storage(){
        String filename = DEFAULT_PATH;
    }

//    public static void main(String[] arg) throws Exception{
//        FileWriter file = new FileWriter("DEFAULT_PATH");
//        for(int i = 0; i < )
//    }
    /**
     * Read files from the default file path of duke, duke.txt whenever duke starts up.
     * @param tasks The new task list created for this duke session to be throw into this function from the start of the program.
     * @throws IOException If the filepath has some problems.
     * @throws DukeException If the file context is not in the duke's list format.
     */
//    public void readFile(TaskLists tasks) throws IOException, DukeException {
//            BufferedReader s = null;
//            s = new BufferedReader(new FileReader(DEFAULT_PATH));
//            String input = null;
//            while ((input = s.readLine()) != null) {
//                if (input.charAt(0) == 'T' || input.charAt(0) == 'E' || input.charAt(0) == 'D' || input.charAt(0) == 'A') {
//                    char status = input.charAt(3);
//                    switch (Character.toString(input.charAt(0))) {
//                        case "T":
//                            input = input.substring(6);
//                            tasks.addToDo("task " + input);
//                            if (status == '1') {
//                                int index = tasks.getSize() - 1;
//                                tasks.getList().get(index).setTaskDone();
//                            }
//                            break;
//                        case "E":
//                            input = input.substring(6);
//                            input = input.replace("|", "/at");
//                            tasks.addEvent("event" + input);
//                            if (status == '1') {
//                                int index = tasks.getSize() - 1;
//                                tasks.getList().get(index).setTaskDone();
//                            }
//                            break;
//                        case "D":
//                            input = input.substring(6);
//                            input = input.replace("|", "/by");
//                            tasks.addDeadline("_deadline" + input);
//                            if (status == '1') {
//                                int index = tasks.getSize() - 1;
//                                tasks.getList().get(index).setTaskDone();
//                            }
//                            break;
//                        case "A":
//                            input = input.substring(6);
//                            input = input.replace("|", "after");
//                            tasks.addDoAfter("_do" + input);
//                            if (status == '1') {
//                                int index = tasks.getSize() - 1;
//                                tasks.getList().get(index).setTaskDone();
//                            }
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            }
//        }
//    }

    public ArrayList<String> load() throws FileNotFoundException {

        ArrayList<String> output = new ArrayList<>();
        File f = new File(DEFAULT_PATH);
        Scanner scan = new Scanner(f);
        while (scan.hasNextLine()) {
            output.add(scan.nextLine());
        }
        scan.close();
        return output;
    }


    public void saveFile(ArrayList<Task> list) throws IOException {
        FileWriter file = new FileWriter("dukeTaskOuput.txt");
        String tmp = "";
        for (int i = 0; i < list.size(); i++){
            tmp = tmp + list.get(i).toString() + System.lineSeparator();

            tmp = tmp.replace("(by:", "|").replace("(at:", "|").
                    replace(")", "").replaceAll("\\[", "").
                    replaceAll("]", "|").replace("\u2713", "1 ").
                    replace("\u2718", "0 ").replace("after", "|");
        }

        file.write(tmp);
        file.close();

    }




}

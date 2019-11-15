import tasklist.Deadline;
import tasklist.Event;
import tasklist.Task;
import tasklist.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {

    //Write tasks to file
    static void writeToFile(String fileContent) throws FileNotFoundException {
        File f = new File("D:\\TIC2002PJ\\data\\duke.txt");

        PrintWriter pw = new PrintWriter(f);
        pw.println(fileContent);
        pw.close();
    }

    //Load tasks from file
    static void loadFile() throws FileNotFoundException {
        File f = new File("D:\\TIC2002PJ\\data\\duke.txt");

        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            loadFormat(sc.nextLine());
        }
    }

    //Add tasks to taskList from file
    static void loadFormat(String fileContent) {
        System.out.println(fileContent);
        String str = fileContent;
        String[] storeArray = str.split(" \\| ");
        try {
            if (storeArray[0].equals("T")){
                Task t= new ToDo(storeArray[2], Boolean.valueOf(storeArray[1]));
                Duke.taskList.add(t);
            }
            else if (storeArray[0].equals("D")){
                try{
                    Task t= new Deadline(storeArray[2], Boolean.valueOf(storeArray[1]),storeArray[3]);
                    Duke.taskList.add(t);
                }catch (ArrayIndexOutOfBoundsException e){
                    Task t= new Deadline(storeArray[2], Boolean.valueOf(storeArray[1]));
                    Duke.taskList.add(t);
                }
            }
            else if (storeArray[0].equals("E")){
                try{
                    Task t= new Event(storeArray[2], Boolean.valueOf(storeArray[1]),storeArray[3]);
                    Duke.taskList.add(t);
                }catch (ArrayIndexOutOfBoundsException e){
                    Task t= new Event(storeArray[2], Boolean.valueOf(storeArray[1]));
                    Duke.taskList.add(t);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }






}

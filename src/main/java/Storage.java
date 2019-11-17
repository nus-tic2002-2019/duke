import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {

    String FilePath = "/Users/laiping/Documents/Duke/todo_record.txt";
    String TempPath =  "/Users/laiping/Documents/Duke/temp.txt";
    String horizontal_line = ("____________________________________\n");

    /**
     * Load data from the txt file to Array List whenever program started
     *
     * @param FilePath a default link to where the txt file is saved
     * @throws IOException
     */
    public ArrayList<Task> load (String FilePath) throws IOException {

        ArrayList<Task> myArr_List = new ArrayList<>();
        File f = new File(FilePath);
        Scanner s = new Scanner(f);

        while (s.hasNextLine()) {

            String input = s.nextLine();
            char first_W = input.charAt(1);
            int index_Position = 0;

            switch (first_W) {

            case 'T':
                myArr_List.add(new Todo(input.substring(7)));
                break;

            case 'E' :
                index_Position = input.indexOf("by");
                myArr_List.add(new Event(input.substring(7, index_Position-2), input.substring(index_Position+3)));
                break;

            case 'D' :
                index_Position = input.indexOf("by");
                myArr_List.add(new Deadlines (input.substring(7, index_Position-2), (input.substring((index_Position+4),input.length()-1))));
                break;

            default:
                continue;
            }
        }
        return myArr_List;
    }

    /**
     * Write content in the Array back to txt file whenever the program is closed
     *
     * @param filePath a default link to where all data is currently saved
     * @param tempPath a temperory file used to store the new data, this file will then be rename back to FilePath;
     * @param my_Arr_List the list where all Task is stored during the main program run
     * @throws IOException
     */
    public void Update_Arry_to_List(String filePath, String tempPath, ArrayList my_Arr_List) throws IOException{
        File f = new File(filePath);
        FileWriter f1 = new FileWriter(filePath, false);
        File tem = new File(tempPath);
        FileWriter temp = new FileWriter(tempPath, false);

        for(int i = 0; i < my_Arr_List.size(); i++) {
            temp.write(my_Arr_List.get(i) + System.lineSeparator());
        }

        f1.close();
        temp.close();

        tem.renameTo(f);
    }
}

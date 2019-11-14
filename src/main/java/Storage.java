import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {


    //Load Data from txt.file to ArrayList
    public ArrayList<Task> load (String FilePath) throws IOException, ClassNotFoundException {

        ArrayList<Task> myArr_list = new ArrayList<>();
        File f = new File(FilePath);
        Scanner s = new Scanner(f);

        while (s.hasNextLine()) {
            String input = s.nextLine();
            char first_w = input.charAt(1);
            int index_position = 0;
            switch (first_w) {
                case 'T':
                    myArr_list.add(new Todo(input.substring(7)));
                    break;
                case 'E':
                    index_position = input.indexOf("by");
                    myArr_list.add(new Event(input.substring(7, index_position - 2), input.substring(index_position + 3)));
                    break;
                case 'D':
                    index_position = input.indexOf("by");
                    myArr_list.add(new Deadlines(input.substring(7, index_position - 2), input.substring(index_position + 3)));
                    break;
                default:
                    continue;
            }
        }
        return myArr_list;
    }

    //Write the ArrayList back to File
    private static void Update_Arry_to_File(String FilePath, String TempPath, ArrayList my_Arr_List) throws IOException{
        File f = new File(FilePath);
        FileWriter f1 = new FileWriter(FilePath, false);
        File tem = new File(TempPath);
        FileWriter temp = new FileWriter(TempPath, false);

        for(int i=0; i< my_Arr_List.size(); i++) {
            temp.write(my_Arr_List.get(i) + System.lineSeparator());
        }
        f1.close();
        temp.close();

        boolean successful = tem.renameTo(f);
    }
}

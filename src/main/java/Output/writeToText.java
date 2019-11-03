package Output;

import DukeItems.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class writeToText {

    public static void write(ArrayList<Task> userList) {

        File file = new File("/Users/admin/Documents/TaskList.txt");
        FileWriter writer = null;
        BufferedWriter bw = null;

        try {

            writer = new FileWriter( file, true );
            bw = new BufferedWriter(writer);

            bw.write("My Tasks" + System.lineSeparator());
            bw.write("__________________________________________________________________" + System.lineSeparator());
            for (int i = 0; i < userList.size(); i++) {
                bw.write((i+1) + ". " + userList.get(i).toString() + System.lineSeparator());
            }
            bw.write("__________________________________________________________________");
            System.out.println("List saved to My Documents");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bw.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    private String filename;

    public Storage(String filename){
        this.filename = filename;
    }

    public static void SaveFile(ArrayList<Task> t){
        try {
            FileOutputStream fout = new FileOutputStream("D:\\git\\output.txt");
            for( Integer i=0 ; i<t.size() ; i++){
                String s = t.get(i).toString().replace("\u2713","1" ).replace("\u2718","0").
                        replaceAll("\\[","").replaceAll("]","|").replace("(by:","|").
                        replace("(at:","|").replace(")","") + System.lineSeparator();
                byte b[]= s.getBytes();//converting string into byte array
                fout.write(b);
            }
            fout.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }


    public static void LoadFile(ArrayList<Task> t){
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\git\\output.txt"));

            String s;

            while ((s = br.readLine()) != null){

                String[] command = s.split("\\|",0);

                switch (command[0]) {
                    case "T":
                        t.add(new Todo(command[2].trim()));
                        if (command[1].equals("1")){
                            t.get(t.size() - 1).taskDone();
                        }
                        break;
                    case "E":
                        t.add(new Event(command[2].trim(),command[3]));
                        if (command[1].equals("1")){
                            t.get(t.size() - 1).taskDone();
                        }
                        break;
                    case "D":
                        t.add(new Deadline(command[2].trim(),command[3]));
                        if (command[1].equals("1")){
                            t.get(t.size() - 1).taskDone();
                        }
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

}

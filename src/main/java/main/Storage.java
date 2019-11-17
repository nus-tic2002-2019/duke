package main;


import main.parsers.ParserText;
import main.taskLists.Task;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static main.Duke.Tasks;


public class Storage {

    private static File file;
    private String filepath;

    public Storage(String filepath) throws IOException {
        this.filepath = filepath;

    }


    /**
     * Method creates file at specified directory, if it already exists it tries to load its contents to the
     * Array List
     *
     * @throws IOException
     */
    public Boolean start() throws IOException {
        this.file = new File(filepath);

        //Create the file
        if (file.createNewFile()) {
            return true;
        } else {
           return this.load();


        }
    }

    /**
     * Loads contents of Text file into ArrayList
     *
     * @throws IOException
     * @return
     */
    public Boolean load() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String read;

        if (file.length() == 0) {
            return true;
        } else {
            while ((read = br.readLine()) != null)
                Tasks.add(ParserText.inputParse(read));
            return false;
        }
    }

    public static void load(File load) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(load));
        String read;

        if (load.length() == 0) {

        } else {
            while ((read = br.readLine()) != null)
                Tasks.add(ParserText.inputParse(read));
        }

        UI.listTasks(true);
    }

    /**
     *  Archives current list of tasks into a Dated file
     */
    public static void archive() throws IOException, DukeException {

        Date date = new Date() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
        File file = new File("C:\\Users\\yralle.lesly.gimpaya\\Desktop\\duke\\src\\data\\tasks_" + dateFormat.format(date) + ".txt") ;
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        writeToFile(out);

    }

    public static void loadview() {

        File folder = new File("C:\\Users\\yralle.lesly.gimpaya\\Desktop\\duke\\src\\data\\");
        File[] listoffiles = folder.listFiles();

        int count = 1;

        UI.line();
        if (listoffiles.length != 0) {
            System.out.println("\tHere are the files in Archive:");
        }
        for (int i = 0; i < listoffiles.length; i++) {
            if (listoffiles[i].isFile()) {
                System.out.println("\tFile " + count +" " + listoffiles[i].getName());
            } else if (listoffiles[i].isDirectory()) {
                System.out.println("\tDirectory " + count +" " + listoffiles[i].getName());
            }
            count++;
        }
        UI.line();
    }

    public static void loadFile(int input){

        File folder = new File("C:\\Users\\yralle.lesly.gimpaya\\Desktop\\duke\\src\\data\\");
        File[] listoffiles = folder.listFiles();

        try{
            Storage.load(listoffiles[input -1]);
        } catch(Exception e){
            UI.dukePrint("\tOpps, something Unexpected happened!");
        }

    }


    /**
     *  Utility Function to Copy Contents to a specified file
     * @param
     */
    private static void writeToFile(BufferedWriter buffer) throws IOException {

        for (Object input : Tasks) {
            buffer.write(ParserText.outputParse((Task) input));
            buffer.newLine();
        }
        buffer.close();

    }

    /**
     * Method Clears existing data in text file and writes a new set based
     * on the current ArrayList
     *
     * @throws IOException
     */
    public static void save() throws IOException {

        //Delete Existing Content
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();

        //Write Content
        try (FileWriter fritter = new FileWriter(file, true);
             BufferedWriter buffer = new BufferedWriter(fritter)) {

           writeToFile(buffer);

            } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }



}

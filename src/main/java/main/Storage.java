package main;


import main.commands.ClearCommand;
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
    public void start() throws IOException {
        this.file = new File(filepath);

        //Create the file
        if (file.createNewFile()) {
            System.out.println("New File is created!");
        } else {
            this.load();
        }
    }

    /**
     * Loads contents of Text file into ArrayList
     *
     * @throws IOException
     */
    public void load() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String read;

        if (file.length() == 0) {
            System.out.println("File is Empty");
        } else {
            while ((read = br.readLine()) != null)
                Tasks.add(ParserText.inputParse(read));
            System.out.println("\tRecords read from file, type 'list' to check them out");
        }
    }

    /**
     *  Archives current list of tasks into a Dated file
     */
    public static void archive() throws IOException, DukeException {

        Date date = new Date() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
        File file = new File("C:\\Users\\yralle.lesly.gimpaya\\Desktop\\duke\\src\\data\\tasks_" + dateFormat.format(date) + ".txt") ;
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        writeToFile(out);

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

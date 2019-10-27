import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileSaving {
    public static void saveFile(String filePath, ArrayList<Task> tasks){
        try {
            FileOutputStream out = new FileOutputStream(filePath);
            for( int i=0 ; i<tasks.size() ; i++ ){
                String s = tasks.get(i).toString().replace("\u2713","1" ).
                        replace("\u2718","0").
                        replaceAll("\\[","").
                        replaceAll("]","|").
                        replace("(by:","|").
                        replace("(at:","|").
                        replace(")","") +
                        System.lineSeparator();
                byte b[]= s.getBytes();
                out.write(b);
            }
            out.close();
        } catch (IOException e){
            System.out.println("Oops!! Cannot save file !");
        }
    }
}

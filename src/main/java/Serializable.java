import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public interface Serializable {

    void write(FileWriter storage) throws IOException;
    //void read(FileReader fileRead) throws IOException;

}

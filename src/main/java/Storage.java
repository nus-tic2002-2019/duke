import java.io.FileWriter;
import java.io.IOException;

public class Storage {

        public void save(String filename, Serializable serializable)
        {
            FileWriter storage = null;
            try
            {
                storage = new FileWriter(filename);
                serializable.write(storage);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try {
                    storage.close(); //close filewriter
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

}

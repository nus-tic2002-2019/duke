import javafx.scene.control.ButtonBar;

import java.io.*;
import java.lang.invoke.SwitchPoint;

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

    public void load(String filename, Serializable serializable)
    {
        BufferedReader r = null;
        File file = new File(filename);
        if(!file.exists())
        {
            return;
        }
        try
        {
            r = new BufferedReader(new FileReader(file));
            serializable.read(r);

        }
        catch (FileNotFoundException e)
        {
          e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try {
                r.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

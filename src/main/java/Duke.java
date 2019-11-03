import parser.Parser;
import storage.StorageFile;
import ui.Ui;
import taskclasses.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.Scanner;
import java.util.Vector;
import thrownexceptions.*;

import javax.naming.StringRefAddr;

import static taskclasses.TaskList.ToDoAfter_Task;
import static ui.Ui.chatting_Vector_Task;

public class Duke {
    //Main;
    public static void main(String[] args) throws IOException, DecoderUnknownError, EncoderUnknowError, FilePathNotFound, InvalidStorageFilePathException, InputDateTimeTooEarly, DateTypeChooseWrongly, TimeTypeChooseWrongly {
        Ui.LoginFace();
        Ui.Separated_Line();
        Ui.Greeting();
        Ui.Separated_Line();

        Vector<Task> List;
        Vector<Task> ToDoAfterList;
        String Date_Type, Time_Type;
        StorageFile TaskStorageFile = new StorageFile();
        StorageFile TaskToDoList = new StorageFile("data/ToDoAfterList.txt");
        List = TaskStorageFile.CopyToVector();
        ToDoAfterList = TaskStorageFile.CopyToVector();

        try {
            Date_Type = Parser.Date_Display_Format();
            Ui.Separated_Line();
            Time_Type = Parser.Time_Display_Format();
            Ui.Separated_Line();

            chatting_Vector_Task(List, ToDoAfterList, Date_Type, Time_Type);

            TaskStorageFile.TransferToFile(List);
        }
        catch (DateTypeChooseWrongly e){
            System.out.println("     The Date choice you choose is incorrect. Please try again.");
        }
        catch (TimeTypeChooseWrongly e){
            System.out.println("     The Time choice you choose is incorrect. Please try again.");
        }
    }
}
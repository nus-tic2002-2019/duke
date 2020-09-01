import date.time.management.DateTime;
import parser.Parser;
import storage.StorageFile;
import ui.Ui;
import taskclasses.*;
import java.io.IOException;
import java.util.Vector;
import thrownexceptions.*;
import static ui.Ui.chatting_Vector_Task;

public class Duke {
    //Main;
    public static void main(String[] args) throws IOException, DecoderUnknownError, EncoderUnknowError, FilePathNotFound, InvalidStorageFilePathException, InputDateTimeTooEarly, DateTypeChooseWrongly, TimeTypeChooseWrongly, ToDoAfterTypeInformationWronglyInTxt, DateTimeInputFormatWrongly, DukeException, TheTaskNotExistInTheList {
        Ui.LoginFace();
        Ui.Separated_Line();
        Ui.Greeting();
        Ui.Separated_Line();

        Vector<Task> List;
        Vector<ToDoAfter> ToDoAfterList;
        String Date_Type, Time_Type;
        StorageFile TaskStorageFile = new StorageFile();
        List = TaskStorageFile.CopyToVectorTaskList();
        ToDoAfterList = TaskStorageFile.CopyToVectorToDoAfterList();

        Date_Type = Parser.Date_Display_Format();
        Ui.Separated_Line();
        Time_Type = Parser.Time_Display_Format();
        Ui.Separated_Line();

        chatting_Vector_Task(List, ToDoAfterList, Date_Type, Time_Type);

        TaskStorageFile.TransferToFile(List, ToDoAfterList);
    }
}
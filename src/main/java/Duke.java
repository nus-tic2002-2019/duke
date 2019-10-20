import parser.Parser;
import storage.StorageFile;
import ui.Ui;
import taskclasses.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Vector;
import thrownexceptions.*;

import javax.naming.StringRefAddr;

public class Duke {

    //Chatting body;
    public static void chatting_Vector_Task(Vector<Task> List) throws DukeException {
        String Ending = "bye";

        String Input;

        Scanner in = new Scanner(System.in);
        Input = in.nextLine();

        Ui.Separated_Line();

        String[] Input_Words = Input.split(" ");
        String First_Word = Parser.Input_Type(Input);

        try {
            Parser.Input_Length_Checking(First_Word, Input_Words);

            switch (First_Word){
                case "todo":
                case "event":
                case "deadline":
                    TaskList.To_Add_New_Input_Into_List(First_Word, Input, Input_Words, List);
                    break;
                case "done":
                    TaskList.Done_Number(List, Input_Words);
                    break;
                case "delete":
                    TaskList.Delete_Number(List, Input_Words);
                    break;
                case "list":
                    TaskList.Print_List(List);
                    break;
                case "bye":
                    System.out.println("     Bye. Hope to see you again soon!");
                    Ui.Separated_Line();
                    return;
                default:
                    System.out.println("    Invalid Input! Please try again!");
            }
        }
        catch (DukeException e) {
            switch (First_Word) {
                case "todo":
                    System.out.println("     \u2639" + " OOPS!!! The description of a todo cannot be empty.");
                    break;
                case "event":
                    System.out.println("     \u2639" + " OOPS!!! The description of a event cannot be empty.");
                    break;
                case "deadline":
                    System.out.println("     \u2639" + " OOPS!!! The description of a deadline cannot be empty.");
                    break;
                default:
                    System.out.println("     \u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
            }
        }
        catch (DoneNumberException e){
            System.out.println("      The task you want to done is invalid! Please key-in again!");
        }
        catch (DeleteNumberException e){
            System.out.println("     The number of task you want to delete is invalid! Please key-in again!");
        }

        Ui.Separated_Line();
        chatting_Vector_Task(List);
    }

    //Main;
    public static void main(String[] args) throws DukeException, IOException {
        Ui.LoginFace();
        Ui.Separated_Line();
        Ui.Greeting();
        Ui.Separated_Line();

        Vector<Task> List;
        StorageFile TaskStorageFile = new StorageFile();
        List = TaskStorageFile.CopyToVector();

        chatting_Vector_Task(List);

        TaskStorageFile.TransferToFile(List);
    }
}
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

public class Duke {

    //Chatting body;

    /**
     * The inter-action UI with user
     * @param List Task List
     */
    private static void chatting_Vector_Task(Vector<Task> List) {
        String Ending = "bye";
        String Input;
        String Date_Type, Time_Type;

        try {
            Date_Type = Parser.Date_Display_Format();
            Time_Type = Parser.Time_Display_Format();

            Scanner in = new Scanner(System.in);
            Input = in.nextLine();

            Ui.Separated_Line();

            String[] Input_Words = Input.split(" ");
            String First_Word = Parser.Input_Type(Input);

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
                    TaskList.Print_List(List, Date_Type, Time_Type);
                    break;
                case "bye":
                    System.out.println("     Bye. Hope to see you again soon!");
                    Ui.Separated_Line();
                    return;
                case "search":
                    String search_type;
                    Scanner search_IN = new Scanner(System.in);
                    search_type = search_IN.nextLine();

                    Parser.Search_Type_Checking(search_type);

                    switch (search_type){
                        case "date":
                            Scanner search_Date = new Scanner(System.in);
                            search_type = search_Date.nextLine();
                            TaskList.Print_List_Date(List, search_type, Date_Type, Time_Type);
                        case "time":
                            Scanner search_Time = new Scanner(System.in);
                            search_type = search_Time.nextLine();
                            TaskList.Print_List_Time(List, search_type, Date_Type, Time_Type);
                    }
                    break;
                default:
                    System.out.println("    Invalid Input! Please try again!");
            }
        }
        catch (DukeException e) {
            String First_Word = DukeException.getFirst_Word();

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
        catch (InputTimeBeforeLocal e) {
            System.out.println("     The input time cannot earlier then local time.");
        }
        catch (DateTimeInputFormatWrongly e){
            System.out.println("     The input Datetime format wrongly. Please try again.");
        }
        catch (TimeTypeChooseWrongly e){
            System.out.println("     The Time choice you choose is incorrect. Please try again.");
        }
        catch (DateTypeChooseWrongly e){
            System.out.println("     The Date choice you choose is incorrect. Please try again.");
        }
        catch (SearchTypeWrong e) {
            System.out.println("     The search type wrongly. Please try again.");
        }
        catch (DateTimeInputWrongly e) {
            System.out.println("    The date or time input wrongly. Please try again.");
        }
        catch (MonthIndexWrong monthIndexWrong) {
            System.out.println("    The month input is not correct. Please try again.");
        }
        catch (EnumDayIndexWrongly enumDayIndexWrongly) {
            System.out.println("The day input is not correct. Please try again.");
        }

        Ui.Separated_Line();
        chatting_Vector_Task(List);
    }

    //Main;
    public static void main(String[] args) throws IOException, DecoderUnknownError, EncoderUnknowError {
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
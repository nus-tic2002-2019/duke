package taskclasses;

import parser.Parser;
import thrownexceptions.*;
import date.time.management.*;
import ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;

import static date.time.management.DateTime.Comparision;
import static date.time.management.DateTime.DateTimeChangingInformationCollector;
import static parser.Parser.*;
import static taskclasses.Task.TaskDescriptionUpdate;
import static ui.Ui.*;


public class TaskList {

    /**
     * To check whether the input String is a number
     * @param strNum input String
     * @return return boolean
     */
    private static boolean isNumeric(String strNum) {
        boolean ret = true;
        try {
            Double.parseDouble(strNum);

        }catch (NumberFormatException e) {
            ret = false;
        }
        return !ret;
    }

    /**
     * To add one input String into Vector<Task> List.
     * In the list, the function will check the task type and create Task accordingly.Then add to List.
     * @param First_Word The First_Word of user input
     * @param Input The user input String
     * @param Input_Words The input String which stored as array
     * @param List The Vector<Task> where the New Task will be added to
     * @throws InputTimeBeforeLocal If the Task input time is before the Task creation time, then an error will be thrown to user
     * @throws DateTimeInputFormatWrongly If the Task input date or time format is wrong, an error will be thrown to user
     */
    public static void To_Add_New_Input_Into_List(String First_Word, String Input, String[] Input_Words, Vector<Task> List) throws InputTimeBeforeLocal, DateTimeInputFormatWrongly {

        Task New_task = new Task();

        try{
            //if the input is Todo;
            switch (First_Word) {
                case "todo":
                    New_task = new Todo(Description(Input));
                    List.add(New_task);

                    break;
                //if the input is Deadline;
                case "deadline": {
                    String deadline_D = deadline_time(Input).trim();
                    String date_D = Parser.date(deadline_D);
                    String time_D = Parser.time(deadline_D);

                    DateTime timing = new DateTime(date_D, time_D);
                    Comparision(timing.getDateTime_Input());

                    New_task = new Deadline(Description(Input), timing);
                    List.add(New_task);
                    break;
                }
                //if the input is Event;
                case "event": {
                    String datetime_D = Event_time(Input).trim();
                    String Starting = Starting_Time(datetime_D);
                    String Starting_Date = date(Starting);
                    String Starting_Time = time(Starting);
                    String Ending = Ending_Time(datetime_D);
                    String Ending_Date = date(Starting_Date, Ending);
                    String Ending_Time = time(Ending);

                    DateTime Starting_DateTime = new DateTime(Starting_Date, Starting_Time);
                    DateTime Ending_DateTime = new DateTime(Ending_Date, Ending_Time);

                    New_task = new Event(Description(Input), Starting_DateTime, Ending_DateTime);
                    List.add(New_task);
                    break;
                }
            }
        }catch (InputDateTimeTooEarly e){
            System.out.println("The date/time you key-in is earlier than the time you create the Task. Please try again.");
        }catch (DateTimeParseException e){
            throw new DateTimeInputFormatWrongly();
        }

        Ui.Out_After_Added(New_task, First_Word, List);
    }

    /**
     * To check the number which user decide to make the according Task to Done status is in the Task List
     * After checking and no error, then function will mark the Task as done
     * @param List The Task List
     * @param Input_Words Input String array which is needed to compare with List length
     * @throws DoneNumberException If the number needed of input is not in the Task List range, an error will be thrown to user
     */
    public static void Done_Number(Vector<Task> List, String[] Input_Words) throws DoneNumberException {
        int DoneNumber = Integer.parseInt(Input_Words[1]);
        int L_size = List.size();

        if(isNumeric(Input_Words[1]) || Input_Words.length != 2 || DoneNumber > L_size || DoneNumber < 1){
            throw new DoneNumberException();
        }

        int i = Integer.parseInt(Input_Words[1]) - 1;
        List.get(i).isDone = true;
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       [" + List.get(i).getStatusIcon() + "] " + List.get(i).getDescription());
    }

    /**
     * To check delete number and delete the Task from List
     * @param List The Task List
     * @param Input_Words Input String array which is needed to compare with List length and delete from List
     * @throws DeleteNumberException If the delete number is not not whole number or our of List length, an error will thrown to user
     */
    public static void Delete_Number(Vector<Task> List, String[] Input_Words) throws DeleteNumberException {
        int DeleteNumber_Index = Integer.parseInt(Input_Words[1]);
        int L_size = List.size();

        if(isNumeric(Input_Words[1]) || Input_Words.length != 2 || DeleteNumber_Index > L_size || DeleteNumber_Index < 1) {
            throw new DeleteNumberException();
        }

        Task Deleted_Task = List.remove(DeleteNumber_Index - 1);

        Ui.Out_After_Delete(Deleted_Task, List);
    }

    /**
     * To print task list from Vector List
     * @param List Vector<Task> List
     * @param Date_Type The Date type user choose to print
     * @param Time_Type The Time type user choose to print
     * @throws DateTimeInputWrongly If the DateTime format is not correct, an error will be thrown to user
     * @throws MonthIndexWrong If the Month Index is not in the enum Month, an error will be thrown to user
     * @throws EnumDayIndexWrongly If the Day Index is not in the enum Day, an error will be thrown to user
     */
    public static void Print_List(Vector<Task> List, String Date_Type, String Time_Type) throws DateTimeInputWrongly, MonthIndexWrong, EnumDayIndexWrongly {
        Separated_Line();
        if(List.size() == 0){
            System.out.println("     There is no Task in the List.");
            return;
        }

        System.out.println("     Here are the task(s) in your list:");

        for (int i = 0; i < List.size(); i++) {
            int j = i + 1;
            Task current = List.get(i);
            String StatusIcon = current.getStatusIcon();
            String TypeIcon = current.getType();

            System.out.print("       ");
            System.out.print(j + ".");
            Print_Task(current, Date_Type, Time_Type);

            System.out.println();
        }
    }

    /**
     * To print single task timing. If the task type is todo, then nothing will be printed
     * @param current The task which is going to be print out
     * @param Date_Type Date printing type
     * @param Time_Type Time printing type
     * @throws DateTimeInputWrongly If the DateTime format is not correct, an error will be thrown to user
     * @throws MonthIndexWrong If the Month Index is not in the enum Month, an error will be thrown to user
     * @throws EnumDayIndexWrongly If the Day Index is not in the enum Day, an error will be thrown to user
     */
    private static void Print_Task(Task current, String Date_Type, String Time_Type) throws DateTimeInputWrongly, EnumDayIndexWrongly, MonthIndexWrong {
        String StatusIcon = current.getStatusIcon();
        String TypeIcon = current.getType();

        System.out.print("[" + TypeIcon + "][" + StatusIcon + "] " + current.getDescription());

        if (TypeIcon.equals("D")) {
            System.out.print(" (by: ");
            switch (Date_Type){
                case "1":
                    System.out.print(Date_Type_One(current, "deadline") + " ");
                    break;
                case "2":
                    System.out.print(Date_Type_Two(current, "deadline") + " ");
                    break;
            }
            switch (Time_Type){
                case "1":
                    System.out.print(Time_Type_One(current.getDeadline_timing()) + ")");
                    break;
                case "2":
                    System.out.print(Time_Type_Two(current.getDeadline_timing()) + ")");
                    break;
            }
        }
        else if (TypeIcon.equals("E")) {
            System.out.print(" (at: ");
            //+ current.getStarting_DateTime_String() + " -> " + current.getEnding_DateTime_String() + ")");
            switch (Date_Type){
                case "1":
                    System.out.print(Date_Type_One(current, "start") +" ");
                    break;
                case "2":
                    System.out.print(Date_Type_Two(current, "start") + " ");
                    break;
            }
            switch (Time_Type){
                case "1":
                    System.out.print(Time_Type_One(current.getStarting_Time()));
                    break;
                case "2":
                    System.out.print(Time_Type_Two(current.getStarting_Time()));
                    break;
            }
            System.out.print(" -> ");
            switch (Date_Type){
                case "1":
                    System.out.print(Date_Type_One(current, "end") + " ");
                    break;
                case "2":
                    System.out.print(Date_Type_Two(current, "end") + " ");
                    break;
            }
            switch (Time_Type){
                case "1":
                    System.out.print(Time_Type_One(current.getEnding_Time()) + ")");
                    break;
                case "2":
                    System.out.print(Time_Type_Two(current.getEnding_Time()) + ")");
                    break;
            }
        }
    }

    /**
     * The method used to print ToDoAfter List all information;
     * @param ToDoAfter ToDoAfter List
     * @param Date_Type Date printing type
     * @param Time_Type Time printing type
     * @throws DateTimeInputWrongly If the DateTime format is not correct, an error will be thrown to user
     * @throws MonthIndexWrong If the Month Index is not in the enum Month, an error will be thrown to user
     * @throws EnumDayIndexWrongly If the Day Index is not in the enum Day, an error will be thrown to user
     */
    public static void Print_List_ToDoAfter(Vector<ToDoAfter> ToDoAfter, String Date_Type, String Time_Type) throws DateTimeInputWrongly, MonthIndexWrong, EnumDayIndexWrongly {
        String Task_Type;

        if(ToDoAfter.size() == 0){
            System.out.println("     There is no Task in the List.");
            return;
        }

        for(int i=0; i<ToDoAfter.size(); i++){
            int j = i+1;
            ToDoAfter toDoAfter = ToDoAfter.get(i);
            Task_Type =toDoAfter.getToDoAfter_Type();

            assert ("date".equals(Task_Type) || "task".equals(Task_Type)): "The ToDoAfter Task type is not either 'date' or 'task'";

            //Task type
            if ("date".equals(Task_Type)) { //Date type
                LocalDate date = toDoAfter.getDate();
                String date_with_type;

                if ("1".equals(Date_Type)) {
                    date_with_type = Date_Type_One(date);
                } else {
                    date_with_type = Date_Type_Two(date);
                }

                System.out.print("      " + j + ". ");
                System.out.println("ToDoAfter type: " + Task_Type);
                System.out.print("       " + "Task: ");
                Print_Task(toDoAfter.getToDoAfterTask(), Date_Type, Time_Type);
                System.out.println("        " + "ToDoAfter Date: " + date_with_type);
            } else {
                System.out.println("     " + j + ". " + "Type: Task");
                System.out.print("        " + "Task: ");

                Print_Task(toDoAfter.getTask(), Date_Type, Time_Type);

                System.out.print("\n" + "        Task will do: ");

                Print_Task(toDoAfter.getToDoAfterTask(), Date_Type, Time_Type);

                System.out.println("");
            }
        }
    }

    /**
     * To search by Specific Date and print the task list found
     * @param List Task list
     * @param Date Date of User want to search
     * @param Date_Type The date type user choose to print
     * @param Time_Type The time type user choose to print
     * @param ToDoAfterList ToDoAfter List for searching
     * @param List_Type List type for searching
     * @throws DateTimeInputWrongly If the DateTime Input is not correct, an error will be thrown to user
     * @throws EnumDayIndexWrongly If the Month Index is not in the enum Month, an error will be thrown to user
     * @throws MonthIndexWrong If the Day Index is not in the enum Day, an error will be thrown to user
     */
    private static void Print_List_Date(Vector<Task> List, String Date, String Date_Type, String Time_Type, Vector<ToDoAfter> ToDoAfterList, String List_Type) throws DateTimeInputWrongly, EnumDayIndexWrongly, MonthIndexWrong {
        LocalDate Date_Deadline, Date_Start, Date_End, input = LocalDate.parse(Date);
        DateTime Deadline, start, end;
        Vector<Task> temp_List = new Vector<>();
        Vector<Task> temp_ToDoAfter = new Vector<>();
        String type;
        int i=0;
        Task task;
        ToDoAfter toDoAfter;
//        List Type:
//        1. Task List;
//        2. ToDoAfter List;
//        3. Both of above;

        switch (List_Type){
            case "1":  //To search Task List only
                for(i=0; i<List.size(); i++){

                    task = List.get(i);
                    type = task.getType();

                    switch (type) {
                        case "E":
                            start = task.getStarting_Time();
                            end = task.getEnding_Time();
                            Date_Start = start.getDate_Input();
                            Date_End = end.getDate_Input();

                            if(input.compareTo(Date_Start) == 0|| input.compareTo(Date_End) == 0|| (input.isAfter(Date_Start) && input.isBefore(Date_End))) {
                                temp_List.add(task);
                            }

                            break;
                        case "D":
                            Deadline = task.getDeadline_timing();
                            Date_Deadline = Deadline.getDate_Input();

                            if(input.compareTo(Date_Deadline) == 0) temp_List.add(task);

                            break;
                    }
                }
                Print_List(temp_List,Date_Type, Time_Type);
                break;
            case "2": //To search ToDoAfter List only
                //Print_List_ToDoAfter(ToDoAfterList, Date_Type, Time_Type);
                for(i=0; i<ToDoAfterList.size(); i++){

                    toDoAfter = ToDoAfterList.get(i);
                    task = toDoAfter.getToDoAfterTask();
                    type = task.getType();  //Either "E", "T" or "D" from the ToDoAfterTask;

                    switch (type) {
                        case "E":
                            start = task.getStarting_Time();
                            end = task.getEnding_Time();
                            Date_Start = start.getDate_Input();
                            Date_End = end.getDate_Input();

                            if(input.compareTo(Date_Start) == 0|| input.compareTo(Date_End) == 0|| (input.isAfter(Date_Start) && input.isBefore(Date_End))) {
                                temp_ToDoAfter.add(task);
                            }

                            break;
                        case "D":
                            Deadline = task.getDeadline_timing();
                            Date_Deadline = Deadline.getDate_Input();

                            if(input.compareTo(Date_Deadline) == 0) temp_ToDoAfter.add(task);

                            break;
                    }
                }
                Print_List_ToDoAfter(ToDoAfterList, Date_Type, Time_Type);
                break;
        }
    }

    /**
     * To search by Specific Time and print the Task(s) found
     * @param List Task list
     * @param Time Date of User want to search
     * @param Date_Type The date type user choose to print
     * @param Time_Type The time type user choose to print
     * @param ToDoAfterList ToDoAfter List for searching
     * @param List_Type Task List for searching
     * @throws DateTimeInputWrongly If the DateTime Input is not correct, an error will be thrown to user
     * @throws EnumDayIndexWrongly If the Month Index is not in the enum Month, an error will be thrown to user
     * @throws MonthIndexWrong If the Day Index is not in the enum Day, an error will be thrown to user
     */
    private static void Print_List_Time(Vector<Task> List, String Time, String Date_Type, String Time_Type, Vector<ToDoAfter> ToDoAfterList, String List_Type) throws DateTimeInputWrongly, EnumDayIndexWrongly, MonthIndexWrong {
        LocalTime Date_Deadline, Date_Start, Date_End, input = LocalTime.parse(Time);
        DateTime Deadline, start, end;
        Vector<Task> temp_List = new Vector<>();
        Vector<Task> temp_ToDoAfter = new Vector<>();
        String type;
        int i=0;
        Task task;

        switch (List_Type){
            case "1": //Task List;
                for(i=0; i<List.size(); i++){

                    task = List.get(i);
                    type = task.getType();

                    switch (type) {
                        case "E":
                            start = task.getStarting_Time();
                            end = task.getEnding_Time();
                            Date_Start = start.getTime_Input();
                            Date_End = end.getTime_Input();

                            if(input.compareTo(Date_Start) == 0 ||
                                    input.compareTo(Date_End) == 0 ||
                                    (input.isAfter(Date_Start) && input.isBefore(Date_End))) {
                                temp_List.add(task);
                            }

                            break;
                        case "D":
                            Deadline = task.getDeadline_timing();
                            Date_Deadline = Deadline.getTime_Input();

                            if(input.compareTo(Date_Deadline) == 0) temp_List.add(task);

                            break;
                    }
                }
                Print_List(temp_List,Date_Type, Time_Type);
                break;
        }
    }

    /**
     * To search by specific description and print the Task(s) found
     * @param List Task list
     * @param Description Description of User want to search
     * @param Date_Type The date type user choose to print
     * @param Time_Type The time type user choose to print
     * @param ToDoAfterList ToDoAfter List for searching
     * @param List_Type Task List for searching
     * @throws DateTimeInputWrongly If the DateTime Input is not correct, an error will be thrown to user
     * @throws EnumDayIndexWrongly If the Month Index is not in the enum Month, an error will be thrown to user
     * @throws MonthIndexWrong If the Day Index is not in the enum Day, an error will be thrown to user
     */
    private static void Print_List_Description(Vector<Task> List, String Description, String Date_Type, String Time_Type, Vector<ToDoAfter> ToDoAfterList, String List_Type) throws DateTimeInputWrongly, EnumDayIndexWrongly, MonthIndexWrong {
        String ListTask_Description;
        Vector<Task> temp_List = new Vector<>();
        Vector<Task> temp_ToDoAfter = new Vector<>();
        Task task;

        switch (List_Type){
            case "1": //Task List;
                for (Task value : List) {
                    task = value;
                    ListTask_Description = task.getDescription();

                    if (Description.equals(ListTask_Description)) {
                        temp_List.add(task);
                    }
                }
                Print_List(temp_List, Date_Type, Time_Type);
                break;
        }
    }

    /**
     * To search by specific List type and print the Task(s) found
     * @param List Task list
     * @param Type Task Type of User want to search
     * @param Date_Type The date type user choose to print
     * @param Time_Type The time type user choose to print
     * @param ToDoAfterList ToDoAfter List for searching
     * @param List_Type Task List for searching
     * @throws DateTimeInputWrongly If the DateTime Input is not correct, an error will be thrown to user
     * @throws EnumDayIndexWrongly If the Month Index is not in the enum Month, an error will be thrown to user
     * @throws MonthIndexWrong If the Day Index is not in the enum Day, an error will be thrown to user
     */
    private static void Print_List_Type(Vector<Task> List, String Type, String Date_Type, String Time_Type, Vector<ToDoAfter> ToDoAfterList, String List_Type) throws DateTimeInputWrongly, EnumDayIndexWrongly, MonthIndexWrong {
        String ListTask_Type;
        Vector<Task> temp_List = new Vector<>();
        Vector<Task> temp_ToDoAfter = new Vector<>();
        Task task;

        switch (List_Type){
            case "1": //Task List;
                for (Task value : List) {
                    task = value;
                    ListTask_Type = task.getType();

                    if (Type.equals(ListTask_Type)) {
                        temp_List.add(task);
                    }
                }
                Print_List(temp_List, Date_Type, Time_Type);
                break;
        }
    }

    /**
     * To create Task.
     * Only available for three different task types:
     * 1. todo
     * 2. event
     * 3. deadline
     * @param Input The task information for task creation
     * @return return the created task
     * @throws DateTimeInputFormatWrongly The date/time format incorrect
     * @throws DukeException The task type  incorrect
     * @throws InputDateTimeTooEarly The date/time input is before the task creation date/time
     */
    public static Task Task_Generator(String Input) throws DateTimeInputFormatWrongly, DukeException, InputDateTimeTooEarly {

        Task New_task;
        String[] input_information = Input.split(" ");
        String First_Word = input_information[0];

        try{
            switch (First_Word) {
                //if the input task type is Todo;
                case "todo":
                    //To create new Task, New_task;
                    New_task = new Todo(Description(Input));
                    break;
                //if the input task type is Deadline;
                case "deadline": {
                    String deadline_D = deadline_time(Input).trim();
                    String date_D = Parser.date(deadline_D);
                    String time_D = Parser.time(deadline_D);

                    DateTime timing = new DateTime(date_D, time_D);
                    Comparision(timing.getDateTime_Input());

                    //To create new Task, New_task;
                    New_task = new Deadline(Description(Input), timing);
                    break;
                }
                //if the input task type is Event;
                case "event": {
                    String datetime_D = Event_time(Input).trim();
                    String Starting = Starting_Time(datetime_D);
                    String Starting_Date = date(Starting);
                    String Starting_Time = time(Starting);
                    String Ending = Ending_Time(datetime_D);
                    String Ending_Date = date(Ending);
                    String Ending_Time = time(Ending);

                    DateTime Starting_DateTime = new DateTime(Starting_Date, Starting_Time);
                    DateTime Ending_DateTime = new DateTime(Ending_Date, Ending_Time);

                    //To create new Task, New_task;
                    New_task = new Event(Description(Input), Starting_DateTime, Ending_DateTime);
                    break;
                }
                default:{
                    throw new DukeException();
                }
            }
        }catch (InputDateTimeTooEarly e){
            System.out.println("     The date/time you key-in for the task is earlier than the task creation time. Please try again.");
            throw new InputDateTimeTooEarly();
        }catch (DateTimeParseException e){
            throw new DateTimeInputFormatWrongly();
        }

        return New_task;
    }

    /**
     * To check whether the Task which will be the
     * @param description Using this description to find the task with same description in Vector<Task> List
     * @param List The task list
     * @return return the Task found in the list
     * @throws TheTaskNotExistInTheList If the task in exist, throw an error to inform user.
     */
    private static Task TodoTaskIsExist(String description, Vector<Task> List) throws TheTaskNotExistInTheList {
        int List_size = List.size();
        String ListTask_description;

        for (Task task : List) {
            ListTask_description = task.getDescription();
            if (ListTask_description.equals(description)){
                return task;
            }
        }

        throw new TheTaskNotExistInTheList();
    }

    private static Task DeadlineExistInTaskList(Vector<Task> List, String timing, String description) throws TheTaskNotExistInTheList {
        String Task_timing, Task_Description;

        for (Task task : List) {
            Task_Description = task.getDescription();
            Task_timing = task.getDeadline_DateTime_String();

            if (Task_Description.equals(description) && Task_timing.equals(timing)) {
                return task;
            }
        }

        throw new TheTaskNotExistInTheList();
    }

    private static Task EventExistInTaskList(Vector<Task> List, String starting, String ending, String description) throws TheTaskNotExistInTheList {
        String Task_start, Task_end, Task_description;

        for(Task task : List){
            Task_description = task.getDescription();
            Task_start = task.getStarting_DateTime_String();
            Task_end = task.getEnding_DateTime_String();

            if(Task_description.equals(description) && Task_end.equals(ending) && Task_start.equals(starting)){
                return task;
            }
        }

        throw new TheTaskNotExistInTheList();
    }

    private static Task TaskIsExist(Task task, Vector<Task> List) throws TheTaskNotExistInTheList {
        int List_size = List.size();
        String description = task.getDescription();
        String type = task.getType();
        String deadline_timing, start_timing, end_timing;

        try {
            switch (type) {
                case "T":
                    return TodoTaskIsExist(description, List);
                case "D":
                    deadline_timing = task.getDeadline_DateTime_String();
                    return DeadlineExistInTaskList(List, deadline_timing, description);
                case "E":
                    start_timing = task.getStarting_DateTime_String();
                    end_timing = task.getEnding_DateTime_String();

                    return EventExistInTaskList(List, start_timing, end_timing, description);
                default:
                    throw new TheTaskNotExistInTheList();
            }
        }
        catch (TheTaskNotExistInTheList e){
            System.out.println("The task which needs to done first does not exist in the Task List. Please try again.");
            throw new TheTaskNotExistInTheList();
        }
    }

    /**
     * To create ToDoAfter task with date condition required.
     * This method checks the date and task information user key-in is whether correct format.
     * @throws InputDateTimeTooEarly If the date information user key-in is incorrect format, the method will throw an error to user and let user try again
     */
    private static ToDoAfter ToDoAfterDate() throws InputDateTimeTooEarly, DateTimeInputFormatWrongly {
        String date, task;

        Task Task_NeedsToDo;
        ToDoAfter Task_ToDoAfter;

        Separated_Line();

        try {
            //To get the Date information from user;
            System.out.println("     Please key in the date for ToDoAfter.\n" +
                    "       (Format: yyyy-mm-dd)");
            Scanner in_date = new Scanner(System.in);
            date = in_date.nextLine();
            //throw new InputDateTimeTooEarly();
            if (!Comparision(date)) {
                System.out.println("     The date you chose is earlier than today.");
                ContinueOrNot();
            }
            else{
                //To get Task information from user;
                System.out.println("     Please key in the task you are going to do.");
                Scanner in_task = new Scanner(System.in);
                task = in_task.nextLine();

                //To create task according to user key-in information;
                Task_NeedsToDo = Task_Generator(task);

                //To create ToDoAfter Task;
                Task_ToDoAfter = new ToDoAfter("date", LocalDate.parse(date), Task_NeedsToDo);
                return Task_ToDoAfter;
            }
        }
        catch (DukeException e){
            System.out.println("     The type of task you key-in is not correct.\n" +
                    "     Task Type choices:\n" +
                    "     1. todo\n" +
                    "     2. event\n" +
                    "     3. deadline");
            Separated_Line();
        }

        return ToDoAfterDate();
    }

    private static void ChoiceChecking(Vector<Task> ExistingTaskList, int choice) throws InputChoiceOutOfRange {
        if(choice < 1 || choice > ExistingTaskList.size()){
            throw new InputChoiceOutOfRange();
        }
    }

    private static int UserIndexChoiceChecker(Vector<Task> ExistingTaskList) {
        String index;
        int choice;

        try{
            Scanner i = new Scanner(System.in);
            index = i.nextLine();

            choice = Integer.parseInt(index);
            ChoiceChecking(ExistingTaskList, choice);

            return choice;
        }
        catch (NumberFormatException e){
            System.out.println("     Ooops! The number you choose is not whole number. Only integer number is available. \nPlease choose again. ");
            return UserIndexChoiceChecker(ExistingTaskList);
        }
        catch (InputChoiceOutOfRange e){
            System.out.println("     The choice you chose is out of range. Please choose again. ");
            return UserIndexChoiceChecker(ExistingTaskList);
        }
    }

    private static Task ExistTaskFinder_Description(String description, Vector<Task> List, String Datetype, String Timetype) throws DateTimeInputWrongly, EnumDayIndexWrongly, MonthIndexWrong, TheTaskNotExistInTheList {
        String task_Description;
        Vector<Task> ExistingTaskList = new Vector<>() ;

        for(Task task : List){
            task_Description = task.getDescription();
            if(description.equals(task_Description)){
                ExistingTaskList.add(task);
            }
        }

        if(ExistingTaskList.size() == 1){
            return ExistingTaskList.get(0);
        }
        else if(ExistingTaskList.size() == 0){
            throw new TheTaskNotExistInTheList();
        }
        else {
            System.out.println("     There are more than one Task in the list has the same description you keyed-in. Please choose one of the following(key in integer): ");
            Print_List(ExistingTaskList, Datetype, Timetype);

            int choice = UserIndexChoiceChecker(ExistingTaskList);

            return ExistingTaskList.get(choice);
        }
    }

    /**
     * To create ToDoAfter task with task condition required;
     * @param List Existing Task List
     * @return return ToDoAfter type task
     * @throws InputDateTimeTooEarly The date of the task which user keyed-in is not late than the creation time
     * @throws DateTimeInputFormatWrongly The date/time input format not correct
     */
    private static ToDoAfter ToDoAfterTask(Vector<Task> List, String Datetype, String Timetype) throws InputDateTimeTooEarly, DateTimeInputFormatWrongly, TheTaskNotExistInTheList, DateTimeInputWrongly, EnumDayIndexWrongly, MonthIndexWrong {
        //Description for the user to key the existing task's description and use this description to find the task in the Task List;
        String description;
        //Task, to be assigned by the task found in the Task list;
        String task;

        Separated_Line();

        Task Task_NeedsToDo;
        Task ExistTaskInList;

        ToDoAfter Task_ToDoAfter;

        try {
            //To get the Description information from user;
            System.out.println("     Please key in the Task description which the task exist in the Task list or key-in 'list' to view all the task in the Task List.");
            Scanner in_date = new Scanner(System.in);
            description = in_date.nextLine().toLowerCase();

            switch (description) {
                case "list":
                    Print_List(List, Datetype, Timetype);
                    return ToDoAfterTask(List, Datetype, Timetype);
                default:
                    //To get Task information from user;
                    System.out.println("     Please key in the task.");
                    Scanner in_task = new Scanner(System.in);
                    task = in_task.nextLine();

                    //To create task according to user key-in information;
                    Task_NeedsToDo = Task_Generator(task);

                    ExistTaskInList = ExistTaskFinder_Description(description, List, Datetype, Timetype);

                    //To create ToDoAfter Task;
                    Task_ToDoAfter = new ToDoAfter("task", ExistTaskInList, Task_NeedsToDo);
                    return Task_ToDoAfter;
            }
        }
        catch (DukeException e){
            Separated_Line();
            System.out.println("     The type of task you key-in is not correct. Please try again.\n" +
                    "     Task Type choices:\n" +
                    "     1. todo\n" +
                    "     2. event\n" +
                    "     3. deadline");
        }
        catch (TheTaskNotExistInTheList e){
            Separated_Line();
            System.out.println("     The task not in the Existing Task List. Please try again.");
        }

        return ToDoAfterTask(List, Datetype, Timetype);
    }

    /**
     * To create and add ToDoAfter task into ToDoAfter vector list;
     * @param ToDoAfterList The ToDoAfter vector list
     * @throws InputDateTimeTooEarly If the Task time input incorrectly, then the method will throw an error to user to ask user to try again.
     */
    public static void ToDoAfter_Task(Vector<ToDoAfter> ToDoAfterList, Vector<Task> List, String Datetype, String Timetype) throws InputDateTimeTooEarly {
        System.out.println("     Please select index from one of the types below:\n" +
                "      1. To do the task after Date;\n" +
                "      2. To do the task after task;");

        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        ToDoAfter ToDoAfter_task;

        try{
            switch (choice) {
                case "1": //Date type
                    ToDoAfter_task = ToDoAfterDate();
                    ToDoAfterList.add(ToDoAfter_task);
                    //To check whether the new ToDoAfter task needs to add into Task List;
                    ToCheckToDoAfterTaskStatus(ToDoAfter_task, List, ToDoAfterList);
                    break;
                case "2": //Task type
                    ToDoAfter_task = ToDoAfterTask(List, Datetype, Timetype); //To get the ToDoAfter task from user information;
                    ToDoAfterList.add(ToDoAfter_task);
                    //To check whether the new ToDoAfter task needs to add into Task List;
                    ToCheckToDoAfterTaskStatus(ToDoAfter_task, List, ToDoAfterList);
                    break;
                default:
                    ContinueOrNot("     The choice you chose is incorrect.", ToDoAfterList, List, Datetype, Timetype);
            }
        }
        catch (DateTimeInputFormatWrongly e){
            ContinueOrNot("     The ToDoAfter Date you key-in is incorrect format. Please try again.", ToDoAfterList, List, Datetype, Timetype);
        }
        catch (TheTaskNotExistInTheList e){
            ContinueOrNot("     The task not in the existing task list. Please try again.", ToDoAfterList, List, Datetype, Timetype);
        }
        catch (InputDateTimeTooEarly e){
            ContinueOrNot("     The date/time input is earlier than the task creation date/time. Please try again.", ToDoAfterList, List, Datetype, Timetype);
        }
        catch (EnumDayIndexWrongly | DateTimeInputWrongly | MonthIndexWrong enumDayIndexWrongly) {
            enumDayIndexWrongly.printStackTrace();
        }
    }

    /**
     * The method which used in ToDoAfter_Task method catch area. To let customer decide whether want to continue.
     * @param ErrorOutput The message about the error
     * @param ToDoAfter The ToDoAfter vector list
     * @param List The task vector list
     * @throws InputDateTimeTooEarly If the Task time input incorrectly, then the method will throw an error to user to ask user to try again.
     */
    private static void ContinueOrNot(String ErrorOutput, Vector<ToDoAfter> ToDoAfter, Vector<Task> List, String Datetype, String Timetype) throws InputDateTimeTooEarly {
        System.out.println(ErrorOutput);
        System.out.println("     You can key-in 'Quit' to Start from beginning or press Enter to try again.");

        Scanner QuitOrContinue = new Scanner(System.in);
        String QoC = QuitOrContinue.nextLine().toLowerCase();

        if (!"quit".equals(QoC)) {
            ToDoAfter_Task(ToDoAfter, List, Datetype, Timetype);
        }
    }

    /**
     * The method which used in ToDoAfter_Task method catch area. To let customer decide whether want to continue.
     * @throws InputDateTimeTooEarly If the Task time input incorrectly, then the method will throw an error to user to ask user to try again.
     */
    private static void ContinueOrNot() throws InputDateTimeTooEarly, DateTimeInputFormatWrongly {
        System.out.println("     You can key-in 'Quit' to Start from beginning or press Enter to try again.");

        Scanner QuitOrContinue = new Scanner(System.in);
        String QoC = QuitOrContinue.nextLine().toLowerCase();

        if (!"quit".equals(QoC)) {
            ToDoAfterDate();
        }
    }

    /**
     * To get Task Type from user
     * There are three choices:
     * 1. todo
     * 2. event
     * 3. deadline
     * @return return the task type user chose
     */
    private static String toGetTaskTypeFromUser(){
        System.out.println("     Please choose the task type you want to search:\n" +
                "     1. todo\n" +
                "     2. event\n" +
                "     3. deadline");
        Scanner type = new Scanner(System.in);
        String Task_type = type.nextLine().toLowerCase();

        if(!Task_type.equals("todo") && !Task_type.equals("event") && !Task_type.equals("deadline") && !Task_type.equals("1") && !Task_type.equals("2") && !Task_type.equals("3")){
            System.out.println("     The task type you chose is not correct. Please try again.");
            Task_type = toGetTaskTypeFromUser();
        }

        if(Task_type.equals("todo") || Task_type.equals("1")) return "T";
        else if (Task_type.equals("event") || Task_type.equals("2")) return "E";
        else return "D";
    }

    /**
     * To get Time from user
     * @return return Time String which get from user
     */
    private static String toGetTimeFromUser(){
        System.out.println("     Please key-in the time you want to search.");
        Scanner search_Time = new Scanner(System.in);
        String time = search_Time.nextLine();

        return time;
    }

    /**
     * Search function.
     * User can use this function to search task they want base on:
     * 1. date
     * 2. time
     * 3. description
     * 4. Task Type (e.g. "todo", "deadline"...)
     * @param Date_Type The date type user chose for printing
     * @param Time_Type The time type user chose for printing
     * @param List Task list
     * @param ToDoAfterList Task of to do after List
     * @throws SearchTypeWrong The search type wrongly erro
     * @throws DateTimeInputWrongly Date/time input wrongly
     * @throws EnumDayIndexWrongly The day of month wrongly
     * @throws MonthIndexWrong Month index wrongly
     */
    public static void Search(String Date_Type, String Time_Type, Vector<Task> List, Vector<ToDoAfter> ToDoAfterList) throws SearchTypeWrong, DateTimeInputWrongly, EnumDayIndexWrongly, MonthIndexWrong {

//        To get Search type from user;
//        "1. Date\n" +
//        "2. Time\n" +
//        "3. Description\n" +
//        "4. Task Type");
        String search_type = Searching_Type_Choice();
        Ui.Separated_Line();

//        To get the list which user want to search from;
//        1. Task List;
//        2. ToDoAfter List;
//        3. Both of above;
        String List_Type = Search_Task_List_Choice();
        Ui.Separated_Line();

        switch (search_type){
            case "1":  //Date type
                //To get the date information from user;
                System.out.println("     Please key-in the date you want to search.");
                Scanner search_Date = new Scanner(System.in);
                String date = search_Date.nextLine();
                //To print out all the result;
                TaskList.Print_List_Date(List, date, Date_Type, Time_Type, ToDoAfterList, List_Type);

                break;
            case "2":  //Time
                //To get Time information from User;
                String time = toGetTimeFromUser();
                //To print out all the result;
                TaskList.Print_List_Time(List, time, Date_Type, Time_Type, ToDoAfterList, List_Type);

                break;
            case "3":  //Description
                //To get description information from user;
                System.out.println("     Please key-in the description you want to search.");
                Scanner description = new Scanner(System.in);
                String Task_Description = description.nextLine();
                //To print out all the result;
                TaskList.Print_List_Description(List, Task_Description, Date_Type, Time_Type, ToDoAfterList, List_Type);

                break;
            case "4":  //Task Type
                //To get Task Type index from user;
                String type = toGetTaskTypeFromUser();
                //To print out the task(s) found;
                TaskList.Print_List_Type(List, type, Date_Type, Time_Type, ToDoAfterList, List_Type);

                break;
            default:
                throw new SearchTypeWrong();
        }
    }

    private static void ToCheckToDoAfterTaskStatus(ToDoAfter toDoAfter, Vector<Task> List, Vector<ToDoAfter> toDoAfterList) throws TheTaskNotExistInTheList {
        Task Condition_Task, GoingToDo_Task, temp_task;
        String type = toDoAfter.getToDoAfter_Type();

        GoingToDo_Task = toDoAfter.getToDoAfterTask();

        assert(type.equals("task") || type.equals("date")): "     The ToDoAfter task type is not correct. \n" +
                "     The type get from the ToDoAfter List is: " + type;

        try{
            switch (type) {
                case "task":
                    Condition_Task = toDoAfter.getTask();
                    temp_task = TaskIsExist(Condition_Task, List);
                    boolean ConditionTask_status = Condition_Task.getStatus();
                    boolean temp_Task_status = temp_task.getStatus();

                    if(temp_Task_status){
                        List.add(GoingToDo_Task);
                        toDoAfterList.remove(toDoAfter);
                    }

                    break;
                case "date":
                    LocalDate date = toDoAfter.getDate();
                    if(!Comparision(date.toString())) {
                        List.add(GoingToDo_Task);
                        toDoAfterList.remove(toDoAfter);
                    }
            }
        }
        catch (TheTaskNotExistInTheList e){
            List.add(GoingToDo_Task);
            toDoAfterList.remove(toDoAfter);
        }
    }

    /**
     * To check whether the Condition Task of a ToDoAfter type task is in the Task list and the Task status;
     * If the task is not in the Task list or the task is done or the date is passed, then the ToDoAfter task will be copied to Task List and removed from ToDoAfterTask List;
     * @param toDoAfterList ToDoAfter Task List which is going to be check
     * @param taskList Task List
     * @throws TheTaskNotExistInTheList if the Task not exist in the Task List, then the ToDoAfter task will be added into Task list and removed from ToDoAfterTask List
     */
    public static void ToCheckToDoAfterTaskStatus(Vector<ToDoAfter> toDoAfterList, Vector<Task> taskList) throws TheTaskNotExistInTheList {
            for (int i = 0; i < toDoAfterList.size(); i++) {
                ToDoAfter toDoAfter = toDoAfterList.get(i);
                ToCheckToDoAfterTaskStatus(toDoAfter, taskList, toDoAfterList);
            }
    }

    /**
     *The function to get update choice, whether the user choose task date or task description;
     * @param List The Task List
     * @param DateType Date printing format
     * @param TimeType Time printing format
     * @throws InputDateTimeTooEarly If the updated datetime is earlier than current datetime, then the error information will be throw to user;
     */
    public static void UpDateInformation(Vector<Task> List, String DateType, String TimeType) throws InputDateTimeTooEarly, DateTimeInputWrongly, EnumDayIndexWrongly, MonthIndexWrong {
        System.out.println("     Please choose the Task index in the Task List or Key-in 'list' to print out all task in Task List.");
        Scanner s = new Scanner(System.in);
        String UserInput = s.nextLine().toLowerCase();
        int index;
        try {
            switch (UserInput) {
                case "list":
                    Print_List(List, DateType, TimeType);
                    UpDateInformation(List, DateType, TimeType);
                    break;
                default:
                    index = Integer.parseInt(UserInput);

                    if(index > List.size() || index <= 0){
                        Separated_Line();
                        System.out.println("     The index you chose is out of range. Please try again.");
                        Separated_Line();
                        UpDateInformation(List, DateType, TimeType);
                        break;
                    }

                    Task task = List.get(index-1);
                    String type = task.getType();

                    if(type.equals("T")){
                        System.out.println("     The task you chose is a todo type task. There is no date/time information in the task.\n" +
                                "     The update function closed with no change.");
                        break;
                    }

                    System.out.println("     Please choose the aspect you want to update:\n" +
                            "      1. Task Date\n" +
                            "      2. Task description.");

                    Scanner input = new Scanner(System.in);
                    String choice = input.nextLine().toLowerCase();

                    switch (choice) {
                        case "1":
                        case "date":
                        case "task date":
                            DateTimeChangingInformationCollector(task, type);
                            break;
                        case "2":
                        case "description":
                        case "task description":
                            TaskDescriptionUpdate(task);
                            break;
                        default:
                            System.out.println("     The choice you chose does not correct. Please try again.");
                            UpDateInformation(List, DateType, TimeType);
                    }
            }
        }
        catch (NumberFormatException e){
            Separated_Line();
            System.out.println("      The index number you chose is not an Integer. Please try again.");
            Separated_Line();
            UpDateInformation(List, DateType, TimeType);
        }
    }
}

package taskclasses;

import parser.Parser;
import thrownexceptions.*;
import date.time.management.*;
import ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Vector;
import static parser.Parser.*;


public class TaskList {

    /**
     * To check whether the input String is a number
     * @param strNum input String
     * @return return boolean
     */
    public static boolean isNumeric(String strNum) {
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
                    timing.Comparision(timing.getDateTime_Input());

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
                    String Ending_Date = date(Ending);
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

        System.out.println("Which type of timing you prefer to show?");
        System.out.println("MM");

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
            System.out.print("     " + j + ".[" + TypeIcon + "][" + StatusIcon + "] " + current.getDescription());
            if (TypeIcon.equals("D")) {
                System.out.print(" (by: ");
                switch (Date_Type){
                    case "1":
                        System.out.print(Date_Type_One(current, "deadline"));
                        break;
                    case "2":
                        System.out.print(Date_Type_Two(current, "deadline"));
                        break;
                    default:
                        throw new DateTimeInputWrongly();
                }
                switch (Time_Type){
                    case "1":
                        System.out.println(Time_Type_One(current.getDeadline_timing()) + ")");
                        break;
                    case "2":
                        System.out.println(Time_Type_Two(current.getDeadline_timing()) + ")");
                        break;
                    default:
                        throw new DateTimeInputWrongly();
                }
            } else if (TypeIcon.equals("E")) {
                System.out.print(" (at: " + current.getStarting_DateTime_String() + " -> " + current.getEnding_DateTime_String() + ")");
                switch (Date_Type){
                    case "1":
                        System.out.print(Date_Type_One(current, "start"));
                        break;
                    case "2":
                        System.out.print(Date_Type_Two(current, "start"));
                        break;
                    default:
                        throw new DateTimeInputWrongly();
                }
                switch (Time_Type){
                    case "1":
                        System.out.println(Time_Type_One(current.getStarting_Time()) + ")");
                        break;
                    case "2":
                        System.out.println(Time_Type_Two(current.getStarting_Time()) + ")");
                        break;
                    default:
                        throw new DateTimeInputWrongly();
                }
                System.out.print(" -> ");
                switch (Date_Type){
                    case "1":
                        System.out.print(Date_Type_One(current, "end"));
                        break;
                    case "2":
                        System.out.print(Date_Type_Two(current, "end"));
                        break;
                    default:
                        throw new DateTimeInputWrongly();
                }
                switch (Time_Type){
                    case "1":
                        System.out.println(Time_Type_One(current.getEnding_Time()) + ")");
                        break;
                    case "2":
                        System.out.println(Time_Type_Two(current.getEnding_Time()) + ")");
                        break;
                    default:
                        throw new DateTimeInputWrongly();
                }
            }

            System.out.println();
        }
    }

    /**
     * To search by Specific Date and print the task list found
     * @param List Task list
     * @param Date Date of User want to search
     * @param Date_Type The date type user choose to print
     * @param Time_Type The time type user choose to print
     * @throws DateTimeInputWrongly If the DateTime Input is not correct, an error will be thrown to user
     * @throws EnumDayIndexWrongly If the Month Index is not in the enum Month, an error will be thrown to user
     * @throws MonthIndexWrong If the Day Index is not in the enum Day, an error will be thrown to user
     */
    public static void Print_List_Date(Vector<Task> List, String Date, String Date_Type, String Time_Type) throws DateTimeInputWrongly, EnumDayIndexWrongly, MonthIndexWrong {
        LocalDate Date_Deadline, Date_Start, Date_End, input = LocalDate.parse(Date);
        DateTime Deadline, start, end;
        Vector<Task> temp = new Vector<>();
        String type;
        int i=0;
        Task task;

        for(i=0; i<List.size(); i++){

            task = List.get(i);
            type = task.getType();

            switch (type) {
                case "E":
                    start = task.getStarting_Time();
                    end = task.getEnding_Time();
                    Date_Start = start.getDate_Input();
                    Date_End = end.getDate_Input();

                    if(input == Date_Start || input == Date_End || (input.isAfter(Date_Start) && input.isBefore(Date_End))) temp.add(task);

                    break;
                case "D":
                    Deadline = task.getDeadline_timing();
                    Date_Deadline = Deadline.getDate_Input();

                    if(input == Date_Deadline) temp.add(task);

                    break;
            }
        }

        Print_List(temp, Date_Type, Time_Type);
    }

    /**
     * To search by Specific Time and print the Task found
     * @param List Task list
     * @param Time Date of User want to search
     * @param Date_Type The date type user choose to print
     * @param Time_Type The time type user choose to print
     * @throws DateTimeInputWrongly If the DateTime Input is not correct, an error will be thrown to user
     * @throws EnumDayIndexWrongly If the Month Index is not in the enum Month, an error will be thrown to user
     * @throws MonthIndexWrong If the Day Index is not in the enum Day, an error will be thrown to user
     */
    public static void Print_List_Time(Vector<Task> List, String Time, String Date_Type, String Time_Type) throws DateTimeInputWrongly, EnumDayIndexWrongly, MonthIndexWrong {
        LocalTime Date_Deadline, Date_Start, Date_End, input = LocalTime.parse(Time);
        DateTime Deadline, start, end;
        Vector<Task> temp = new Vector<>();
        String type;
        int i=0;
        Task task;

        for(i=0; i<List.size(); i++){

            task = List.get(i);
            type = task.getType();

            switch (type) {
                case "E":
                    start = task.getStarting_Time();
                    end = task.getEnding_Time();
                    Date_Start = start.getTime_Input();
                    Date_End = end.getTime_Input();

                    if(input == Date_Start || input == Date_End || (input.isAfter(Date_Start) && input.isBefore(Date_End))) temp.add(task);

                    break;
                case "D":
                    Deadline = task.getDeadline_timing();
                    Date_Deadline = Deadline.getTime_Input();

                    if(input == Date_Deadline) temp.add(task);

                    break;
            }
        }

        Print_List(temp, Date_Type, Time_Type);
    }

    /**
     * To create Task.
     * Only avaliable for three different task types:
     * 1. todo
     * 2. event
     * 3. deadline
     * @param Input The task information for task creation
     * @return return the created task
     * @throws DateTimeInputFormatWrongly The date/time format incorrect
     * @throws DukeException The task type  incorrect
     * @throws InputDateTimeTooEarly The date/time input is before the task creation date/time
     */
    private static Task Task_Generator(String Input) throws DateTimeInputFormatWrongly, DukeException, InputDateTimeTooEarly {

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
                    timing.Comparision(timing.getDateTime_Input());

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
            System.out.println("The date/time you key-in for the task is earlier than the task creation time. Please try again.");
            throw new InputDateTimeTooEarly();
        }catch (DateTimeParseException e){
            throw new DateTimeInputFormatWrongly();
        }

        return New_task;
    }

    /**
     * To check whether the Task which will be the
     * @param description Usingthis description to find the task with same description in Vector<Task> List
     * @param List The task list
     * @return return the Task found in the list
     * @throws TheTaskNotExistInTheList If the task in exist, throw an error to inform user.
     */
    private static Task TaskIsExist(String description, Vector<Task> List) throws TheTaskNotExistInTheList {
        int List_size = List.size();
        String ListTask_description;

        for (Task task : List) {
            ListTask_description = task.getDescription();
            if (ListTask_description.equals(description)) return task;
        }

        throw new TheTaskNotExistInTheList();
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

        try {
            //To get the Date information from user;
            System.out.println("Please key in the date for ToDoAfter.\n" +
                    "(Format: yyyy-mm-dd");
            Scanner in_date = new Scanner(System.in);
            date = in_date.nextLine();
            if (!DateTime.Comparision(date)) throw new InputDateTimeTooEarly();

            //To get Task information from user;
            System.out.println("Please key in the task.");
            Scanner in_task = new Scanner(System.in);
            task = in_task.nextLine();

            //To create task according to user key-in information;
            Task_NeedsToDo = Task_Generator(task);

            //To create ToDoAfter Task;
            Task_ToDoAfter = new ToDoAfter("date", LocalDate.parse(date), Task_NeedsToDo);
            return Task_ToDoAfter;
        }
        catch (DukeException e){
            System.out.println("The type of task you key-in is not correct.\n" +
                    "Task Type choices:\n" +
                    "1. todo\n" +
                    "2. event\n" +
                    "3. deadline");
        }

        return ToDoAfterDate();
    }

    /**
     * To create ToDoAfter task with task condition required;
     * @param List Existing Task List
     * @return return ToDoAfter type task
     * @throws InputDateTimeTooEarly The date of the task which user keyed-in is not late than the creation time
     * @throws DateTimeInputFormatWrongly The date/time input format not correct
     */
    private static ToDoAfter ToDoAfterTask(Vector<Task> List) throws InputDateTimeTooEarly, DateTimeInputFormatWrongly, TheTaskNotExistInTheList {
        String description, task;

        Task Task_NeedsToDo, ExistTaskInList;
        ToDoAfter Task_ToDoAfter;

        try {
            //To get the Description information from user;
            System.out.println("Please key in the Task description which the task exist in the Task list.");
            Scanner in_date = new Scanner(System.in);
            description = in_date.nextLine();

            //To check whether the task with the same description as the user input in the Task list;
            ExistTaskInList = TaskIsExist(description, List);

            //To get Task information from user;
            System.out.println("Please key in the task.");
            Scanner in_task = new Scanner(System.in);
            task = in_task.nextLine();

            //To create task according to user key-in information;
            Task_NeedsToDo = Task_Generator(task);

            //To create ToDoAfter Task;
            Task_ToDoAfter = new ToDoAfter("date", ExistTaskInList, Task_NeedsToDo);
            return Task_ToDoAfter;
        }
        catch (DukeException e){
            System.out.println("The type of task you key-in is not correct.\n" +
                    "Task Type choices:\n" +
                    "1. todo\n" +
                    "2. event\n" +
                    "3. deadline");
        }
        catch (TheTaskNotExistInTheList e){
            System.out.println("The task not in the Existing Task List. Please try again.");
        }

        return ToDoAfterDate();
    }

    /**
     * To create and add ToDoAfter task into ToDoAfter vector list;
     * @param ToDoAfter The ToDoAfter vector list
     * @throws InputDateTimeTooEarly If the Task time input incorrectly, then the method will throw an error to user to ask user to try again.
     */
    public static void ToDoAfter_Task(Vector<Task> ToDoAfter, Vector<Task> List) throws InputDateTimeTooEarly {
        System.out.println("Please select index from one of the types below:\n" +
                "1. To do the task after Date;\n" +
                "2. To do the task after task;");

        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        ToDoAfter ToDoAfter_task;

        try{
            switch (choice) {
                case "1":
                    ToDoAfter_task = ToDoAfterDate();
                    ToDoAfter.add(ToDoAfter_task);
                    break;
                case "2":
                    ToDoAfter_task = ToDoAfterTask(List);
                    ToDoAfter.add(ToDoAfter_task);
                    break;
                default:
                    ContinueOrNot("The choice you chose is incorrect.", ToDoAfter, List);
            }
        }
        catch (DateTimeInputFormatWrongly e){
            ContinueOrNot("The ToDoAfter Date you key-in is incorrect format. Please try again.", ToDoAfter, List);
        }
        catch (TheTaskNotExistInTheList e){
            ContinueOrNot("The task not in the existing task list. Please try again.", ToDoAfter, List);
        }
        catch (InputDateTimeTooEarly e){
            ContinueOrNot("The date/time input is earlier than the task creation date/time. Please try again.", ToDoAfter, List);
        }
    }

    /**
     * The method which used in ToDoAfter_Task method catch area. To let customer decide whether want to continue.
     * @param ErrorOutput The message about the error
     * @param ToDoAfter The ToDoAfter vector list
     * @param List The task vector list
     * @throws InputDateTimeTooEarly If the Task time input incorrectly, then the method will throw an error to user to ask user to try again.
     */
    private static void ContinueOrNot(String ErrorOutput, Vector<Task> ToDoAfter, Vector<Task> List) throws InputDateTimeTooEarly {
        System.out.println(ErrorOutput);
        System.out.println("You can key-in 'Quit' to Start from beginning or press Enter to try again.");

        Scanner QuitOrContinue = new Scanner(System.in);
        String QoC = QuitOrContinue.nextLine().toLowerCase();

        if (!"quit".equals(QoC)) {
            ToDoAfter_Task(ToDoAfter, List);
        }
    }
}

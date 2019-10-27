package taskclasses;

import parser.Parser;
import thrownexceptions.*;
import date.time.management.*;
import ui.Ui;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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
                    timing.Comparation(timing.getDateTime_Input());

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
}

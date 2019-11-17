package Storage;

import DukeItems.Task;
import DukeItems.Todo;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class loadFromExcel {

    private static final String EXCEL_FILE_PATH = "/Users/admin/Documents/Tasklist.xls";
    protected ArrayList<Task> userList;
    public loadFromExcel(){}

    Workbook Taskbook = null;

    public void loadFile() {
        try {
            Taskbook = Workbook.getWorkbook(new File(EXCEL_FILE_PATH));

            Sheet Tasklist = Taskbook.getSheet(0);
            int numColumn = Tasklist.getColumns();
            int numRow = Tasklist.getRows();

            Cell[] row = Tasklist.getRow(1);
            if (row[1].getContents().contains("[T]")){
                Todo todo = new Todo("todo" + row[3].getContents());
                if (row[2].getContents().contains("\u2713")){
                    todo.markAsDone();
                }
                userList.add(todo);
            }

            //System.out.println(row[3].getContents());

            /*
            for (int i = 1; i<numRow; i++) {

                Cell[] row = Tasklist.getRow(i);

                if (row[1].getContents().contains("[T]")){
                    Todo todo = new Todo("todo" + row[3].getContents());
                    if (row[2].getContents().contains("\u2713")){
                        todo.markAsDone();
                    }
                    userList.add(todo);
                }

                if (row[1].getContents().contains("D")){
                    Deadline deadline = new Deadline(row[3].getContents(), row[4].getContents() + " " + row[5].getContents());
                    if (row[2].getContents().contains("\u2713")){
                        deadline.markAsDone();
                    }
                    userList.add(deadline);
                }

                if(row[1].getContents().contains("E")){
                    Event event = new Event(row[3].getContents(), row[4].getContents() + " " + row[5].getContents());
                    if (row[2].getContents().contains("\u2713")){
                        event.markAsDone();
                    }
                    userList.add(event);
                }
            }
            uiList uilist = new uiList(userList); //initialize
            uilist.printList();
            */

        } catch (IOException e){
            e.printStackTrace();
        } catch (BiffException ex){
            ex.printStackTrace();
        }
    }

}

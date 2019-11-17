package Storage;

import DukeItems.Task;
import jxl.Workbook;
import jxl.write.Number;
import jxl.write.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class writeToExcel {

    private static final String EXCEL_SAVE_PATH = "/Users/admin/Documents/Tasklist.xls";
    protected ArrayList<Task> userList;

    public writeToExcel(ArrayList<Task> userList) {
        this.userList = userList;
    }

    public void saveToExcel() {

        WritableWorkbook TaskBook = null;

        try {
            //Headers
            TaskBook = Workbook.createWorkbook(new File(EXCEL_SAVE_PATH));
            WritableSheet Tasklist = TaskBook.createSheet("Tasklist", 0);

            Label headerItemNum = new Label(0, 0, "No.");
            Tasklist.addCell(headerItemNum);

            Label headerItemCategory = new Label(1, 0, "Category");
            Tasklist.addCell(headerItemCategory);

            Label headerItemStatus = new Label(2, 0, "Status");
            Tasklist.addCell(headerItemStatus);

            Label headerItemDescription = new Label(3, 0, "Description");
            Tasklist.addCell(headerItemDescription);

            Label headerItemDateDue = new Label(4, 0, "Date Due");
            Tasklist.addCell(headerItemDateDue);

            Label headerItemTimeDue = new Label(5, 0, "Time");
            Tasklist.addCell(headerItemTimeDue);


            //Contents
            for (int i = 0; i < userList.size(); i++){
                Number contentItemNum = new Number(0 , i+1, i+1);
                Tasklist.addCell(contentItemNum);

                Label contentItemCategory = new Label(1, i+1, userList.get(i).getTaskType());
                Tasklist.addCell(contentItemCategory);

                Label contentItemStatus = new Label(2, i+1, "["+userList.get(i).getStatusIcon()+"]");
                Tasklist.addCell(contentItemStatus);

                Label contentItemDescription = new Label(3, i+1, userList.get(i).getDescription());
                Tasklist.addCell(contentItemDescription);

                if (userList.get(i).getDue() == "null"){
                    Label contentItemDateDue = new Label(4, i+1, userList.get(i).getDue());
                    Tasklist.addCell(contentItemDateDue);

                    Label contentItemTimeDue = new Label(4, i+1, "null");
                    Tasklist.addCell(contentItemTimeDue);
                }
                else {
                    String[] getDateTime = userList.get(i).getDue().split("at");
                    String getDate = getDateTime[0];
                    String getTime = getDateTime[1];

                    Label contentItemDateDue = new Label(4, i+1, getDate);
                    Tasklist.addCell(contentItemDateDue);

                    Label contentItemTimeDue = new Label(5, i+1, getTime);
                    Tasklist.addCell(contentItemTimeDue);
                }

                //Label contentItemTimeDue = new Label(i+5, i+0, userList.get(i).getDue().substring(Integer.parseInt(" ")+1));
                //Tasklist.addCell(contentItemTimeDue);
            }

            TaskBook.write();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {
            if (TaskBook != null) {
                try {
                    TaskBook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



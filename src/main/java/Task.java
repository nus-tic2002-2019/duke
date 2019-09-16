public class Task {
    private static String[] item;
    private static boolean[] status;
    private static int itemCount = 0;
    private static int size = 0;
    public static boolean setTask(String todo) {
        if (size > itemCount) {
            item[itemCount] = todo;
            itemCount++;
            return true;
        }
        return false;
    }

    public static String[] getTask() {
        return item;
    }

    public static boolean[] getStatus() {
        return status;
    }

    public static int getItemCount() {
        return itemCount;
    }

    public static boolean doTask(int itemNumber) {
        if (itemNumber >= 0 && size > itemNumber) {
            status[itemNumber] = true;
            return true;
        }
        return false;
    }

    public static boolean undoTask(int itemNumber) {
        if (itemNumber >= 0 && size > itemNumber) {
            status[itemNumber] = false;
            return true;
        }
        return false;
    }

    public Task(int size) {
        this.item = new String[size];
        this.status = new boolean[size];
        this.size = size;
    }
}

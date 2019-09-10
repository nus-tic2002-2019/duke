public class Task {
    private static String[] item;
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

    public Task(int size) {
        this.item = new String[size];
        this.size = size;
    }
}

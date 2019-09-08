public class Task {
    private static String description;
    private static String[] contents;
    private static int size = 0;

    public Task(String description) {
        this.description = description;
    }

    public static void addTask() {
        if (size == 0) {
            contents = new String[1];
            contents[0] = description;
            size++;
            System.out.println("added: " + description);
        } else if (!checkTask(description)) {
            refillTask(description);
            System.out.println("added: " + description);
        } else {
            System.out.println("added: " + description);
        }
    }

    private  static  void refillTask(String args) {
        String[] result = new String[size+1];
        int count = 0;

        for (String content : contents) {
            result[count] = content;
            count++;
        }
        result[count] = description;
        size++;

        int recount = 0;
        contents = new String[size];
        for (String content : result) {
            contents[recount] = content;
            recount++;
        }
    }

    private static boolean checkTask(String args) {
        for (String content : contents) {
            if (content.equals(args)) {
                return true;
            }
        }
        return false;
    }

    public static void listTask() {
        int i = 1;
        if (size == 0) {
            System.out.println("There's nothing in the list!");
        } else {
            for (String content : contents) {
                System.out.println(Integer.toString(i) + ". " + content);
                i++;
            }
        }
    }

}

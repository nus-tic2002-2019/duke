public class Duke {

    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        Duke d = new Duke();
        System.out.println(d);

        //System.out.println("\tHello! I'm Duke \n\tWhat can I do for you?");
    }
    @Override
    public String toString() {
        return "|"  + "|" + logo;
    }
}

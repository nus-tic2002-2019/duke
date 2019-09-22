public class Message {
    public static void welcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("\t--------------------------------------------------");
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("\t--------------------------------------------------");
    }
    public static void byeMessage(){
        System.out.println("\t--------------------------------------------------");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t--------------------------------------------------");
    }
}

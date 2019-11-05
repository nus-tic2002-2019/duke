public class Parser {

    public String parseInput(String userInput) {
        userInput = userInput.toLowerCase();
        String command = "unknown";
        if (userInput.contains("bye")) {
            command = "bye";
        }
        if (userInput.contains("done")) {
            command = "done";
        }
        if (userInput.contains("list")) {
            command = "list";
        }
        if (userInput.contains("delete")) {
            command = "delete";
        }
        if (userInput.contains("todo")) {
            command = "todo";
        }
        if (userInput.contains("deadline")) {
            command = "deadline";
        }

        if (userInput.contains("event")) {
            command = "event";
        }
        if (userInput.contains("do") && userInput.contains("after")) {
            command = "doAfter";
        }
        return command;
    }


}

public class Parser
{

    public void parseCommand(String input, UI ui, TaskList line, Storage storage)
    {

            try
            {
                String[] arrOfString = input.split(" ");
                if (input.equals("list"))
                {
                    ui.printTaskList(line.getCount(), line);
                }
                else if (arrOfString[0].equals("done"))
                {
                    if(arrOfString.length < 2)
                    {
                        throw new EmptyDescriptionException("Oops. The description of a done cannot be empty");
                    }
                    int index = Integer.parseInt(arrOfString[1]);
                    line.get(index-1).markAsDone();
                    ui.printDone(line, index);
                    storage.save("listData.txt", line);
                }

                else if (arrOfString[0].equals("todo"))
                {
                    if(arrOfString.length < 2)
                    {
                        throw new EmptyDescriptionException("Oops. The description of a todo cannot be empty");
                    }
                    String replaceString = input.replace("todo ", "");
                    line.newTodoTask(replaceString, false);
                    ui.printTodo(line, line.getCount()-1);
                    storage.save("listData.txt", line);

                }
                else if (arrOfString[0].equals("deadline"))
                {
                    if(arrOfString.length < 2)
                    {
                        throw new EmptyDescriptionException("Oops. The description of a deadline cannot be empty");
                    }
                    String replaceString = input.replace("deadline ","");
                    String [] splitBy = replaceString.split(" /by ");
                    if(splitBy.length < 2)
                    {
                        throw new EmptyDescriptionException("Oops. The date of a event cannot be empty");
                    }
                    line.newDeadlineTask(splitBy[0], false, splitBy[1]);
                    ui.printDeadline(line, line.getCount()-1);
                    storage.save("listData.txt", line);
                }

                else if (arrOfString[0].equals("event"))
                {
                    if(arrOfString.length < 2)
                    {
                        throw new EmptyDescriptionException("Oops. The description of a event cannot be empty");
                    }
                    String replaceString = input.replace("event", "");
                    String [] splitAt = replaceString.split(" /at ");
                    if(splitAt.length < 2)
                    {
                        throw new EmptyDescriptionException("Oops. The date of a event cannot be empty");
                    }
                    line.newEventTask(splitAt[0], false, splitAt[1]);
                    ui.printEvent(line, line.getCount()-1);
                    storage.save("listData.txt", line);
                }

                else if (arrOfString[0].equals("delete"))
                {
                    if(arrOfString.length < 2)
                    {
                        throw new EmptyDescriptionException("Oops. The description of a delete cannot be empty");
                    }
                    int index = Integer.parseInt(arrOfString[1]);
                    Task t = line.removeTask(index);
                    ui.printDelete(t, line.getCount()+1, index);
                    storage.save("listData.txt", line);
                }

                else if(!arrOfString[0].equals("bye")) {
                    throw new InvalidCommandException();
                }

            }
            catch (InvalidCommandException e)
            {
                ui.printInvalidCommandMessage();
            }
            catch (EmptyDescriptionException e)
            {
                System.out.println(e.getMessage());
            }

        }


}

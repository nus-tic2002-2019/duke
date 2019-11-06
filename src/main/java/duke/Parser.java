package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser
{
    /***
     * to parse command into dukeapp
     * @param input
     * @param ui
     * @param line
     * @param storage
     */
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
                    String replaceString2 = input.replace("event", "");
                    String [] splitAt = replaceString2.split(" /every ");
                    if(splitAt.length >1)//if there is an every
                    {
                        String recurringFrequency = splitAt[1];
                        String replaceString = input.replace("todo ", "");
                        line.newTodoTask(splitAt[0], false);
                    }
                    else //if there isn't recurring
                    {
                        String replaceString = input.replace("todo ", "");
                        line.newTodoTask(replaceString, false);
                    }
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
                    String[] repeatChunk = splitAt[1].split("/repeat");
                    if (repeatChunk.length == 1)
                    {
                        if (replaceString.contains("/repeat"))
                        {
                            throw new EmptyDescriptionException("Oops. Please place the amount of days between each repeated event. e.g. /repeat");
                        }
                        line.newEventTask(splitAt[0], false, splitAt[1]);
                        ui.printEvent(line, line.getCount() - 1);
                        storage.save("listData.txt", line);
                    }
                    else
                    {
                        String[] timesChunk = repeatChunk[1].split("/times");
                        if (timesChunk.length == 1)
                        {
                            throw new EmptyDescriptionException("Oops. Please place number of times event is to be repeated. e.g. /times");
                        }
                        String daysString = timesChunk[0].trim();
                        int days = Integer.parseInt(daysString); // get number of days
                        String timesString = timesChunk[1].trim();
                        int times = Integer.parseInt(timesString);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
                        LocalDate date = LocalDate.parse(repeatChunk[0].trim(), formatter);
                        for(int i =0; i<times; i++)
                        {
                            line.newEventTask(splitAt[0], false, date);
                             date= date.plusDays(days);
                            ui.printEvent(line, line.getCount() - 1);
                        }
                        storage.save("listData.txt", line);

                    }
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

                else if (arrOfString[0].equals("find"))
                {
                    TaskList results = line.find(input.replace("find", "").trim());
                    ui.printTaskList(results.getCount(), results);
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
            catch(DateTimeParseException e)
            {
                System.out.println("date not recognized. Please input in this format: yyyy-mm-dd ");
            }

        }


}

package me.chercherlyn.duke.util;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import me.chercherlyn.duke.task.Task;

/**
 * Represents a file storage manager for tasks.
 * Can save and load tasks.
 */
public class Storage {
    
    private Path file;
    
    /**
     * Creates Storage instance.
     *
     * @param path path to file with tasks
     */
    public Storage(String path) {
        try {
            String pathSeparator = File.separator;
            path = path
                    .replace("/", pathSeparator)
                    .replace("\\", pathSeparator);
    
            file = new File(path).toPath();
            
            // create parent dirs if not exist
            Path dir = file.getParent();
            if (dir != null && !Files.isDirectory(dir))
                Files.createDirectories(dir);
            
            // create file if not exist
            if (!Files.isRegularFile(file))
                Files.createFile(file);
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    /**
     * Load tasks from file.
     *
     * @return loaded task list
     */
    public TaskList loadTasks() {
        try {
            TaskList taskList = new TaskList();
            List<String> dataList = Files.readAllLines(file, StandardCharsets.UTF_8);
            for (String data : dataList)
                taskList.add(Task.deserialize(data));
            return taskList;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
    
    /**
     * Save tasks to file.
     *
     * @param taskList task list to save
     */
    public void saveTasks(TaskList taskList) {
        try {
            // build data string
            List<Task> tasks = taskList.getTasks();
            List<String> dataList = new ArrayList<>();
            for (Task task : tasks)
                dataList.add(Task.serialize(task));
            String dataListStr = String.join("\n", dataList);
        
            // save to file
            Files.write(file, dataListStr.getBytes(StandardCharsets.UTF_8));
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}

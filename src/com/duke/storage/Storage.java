package com.duke.storage;

import com.duke.task.TaskList;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "duke.txt";

    public final Path path;

    public Storage(){
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePath){
        path= Paths.get(filePath);
    }

    public void save(TaskList taskList){

    }

    public void load(){

    }



}

package backend;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FileDecoder {

    public FileDecoder() {}

    public Task decode(String tasks) {

        String[] args = tasks.split(" \\| ");
        String taskType = args[0];
        Boolean isDone = args[1].contains("X");
        String taskDescription = args[2];
        String taskDate = null;

        // if String supplied is one with a date
        if (args.length == 4) {
            try {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd yyyy");
                Date dateObject = dateFormatter.parse(args[3]);
                dateFormatter.applyPattern("yyyy-MM-dd");
                taskDate = dateFormatter.format(dateObject);
                ;
            } catch (ParseException e) {
                System.out.println(e);
            }
        }

        //generate the right type of task
        Task task = null;
        switch (taskType) {
        case "E":
            task = new Event(taskDescription, taskDate);
            break;
        case "D":
            task = new Deadline(taskDescription, taskDate);
            break;
        case "T":
            task = new Todo(taskDescription);
            break;

        }

        // check if the task has been completed
        if (isDone) {
            task.setDone(true);
        }
        return task;
    }
}

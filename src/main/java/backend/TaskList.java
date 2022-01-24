package backend;

import java.util.ArrayList;
import tasks.*;
import exception.DukeException;

public class TaskList {
    protected static ArrayList<Task> tasks;


    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a list of all tasks beginning with index 1
     */
    public static void list() {
        Ui.list();
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            int index = i + 1;
            System.out.println(index + "." + currentTask.toString());
        }
    }

    /**
     * Marks the task at the supplied integer as completed, based on their ordering in this.list().
     * @param indexMarked index of the completed task
     */
    public static void mark(int indexMarked) {
        try {
            Task currentTask = tasks.get(indexMarked);
            currentTask.setIsDone(true);
            Ui.mark();
            System.out.println(currentTask.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException("task does not exist!"));
        } catch (NumberFormatException e) {
            System.out.println(new DukeException("provide an index instead :)"));
        }
    }

    /**
     * Unmarks the task at the supplied integer as completed, based on their ordering in this.list().
     * @param indexUnmarked index of the task to be unmarked
     */
    public static void unmark(int indexUnmarked) {
        try {
            Task currentTask = tasks.get(indexUnmarked);
            currentTask.setIsDone(false);
            Ui.unmark();
            System.out.println(currentTask.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException("task does not exist!"));
        } catch (NumberFormatException e) {
            System.out.println(new DukeException("provide an index instead :)"));
        }
    }

    /**
     * removes the task at the supplied integer from tasklist, based on their ordering in this.list().
     * @param indexDelete index of the task to be deleted.
     */
    public static void delete(int indexDelete) {
        try {
            Task deletedTask = tasks.remove(indexDelete);
            Ui.delete();
            System.out.println(deletedTask.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException("task does not exist!"));
        } catch (NumberFormatException e) {
            System.out.println(new DukeException("provide an index instead :)"));
        }
    }

    /**
     * creates a Todo task and adds it to the end of the tasklist
     * @param description description of the todo task.
     */
    public static void todo(String description) {
        try {
            if (description.isBlank()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Todo currentTodo = new Todo(description);
            tasks.add(currentTodo);
            Ui.todo();
            System.out.println(currentTodo.toString());

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(new DukeException("The description of a todo cannot be empty"));
        }
    }

    /**
     * creates a deadline task and adds it to the end of the tasklist.
     * @param description description of the deadline task.
     * @param time time of the deadline task.
     */

    public static void deadline(String description, String time) {
        try {
            if (description.isBlank() || time.isBlank()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Deadline currentDeadline = new Deadline(description, time);
            tasks.add(currentDeadline);
            Ui.deadline();
            System.out.println("added: " + currentDeadline.toString());

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(new DukeException("The description of a deadline cannot be empty"));
        }
    }

    /**
     * creates a event task and adds it to the end of the tasklist.
     * @param description description of the event task.
     * @param time time of the event task.
     */
    public static void event(String description, String time) {
        try {
            if (description.isBlank() || time.isBlank()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Event currentEvent = new Event(description, time);
            tasks.add(currentEvent);
            Ui.event();
            System.out.println("added: " + currentEvent.toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(new DukeException("The description of a event cannot be empty"));
        }
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * initialises the tasklist with an arraylist of tasks
     * @param tasks pre saved tasks from data/duke.txt
     */
    public static void initialise(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }
}

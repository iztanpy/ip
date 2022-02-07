package backend;

import java.lang.reflect.InvocationTargetException;

public class InputDecoder {
    private Commands currentCommand = null;

    public InputDecoder() {
    }

    private String[] parseInput(String input) throws IllegalArgumentException {
        String[] commandSections = input.split(" ", 2);
        currentCommand = Commands.valueOf(commandSections[0].toUpperCase());
        return commandSections;
    }

    /**
     * Returns the task equivalent from a task generating string input supplied by user
     *
     * @param input task generating string that follows standard format
     * @return Task object containing information from input string
     * @throws ArrayIndexOutOfBoundsException if only command and no input behind is given
     */
    public String decode(String input) {
        String[] commandSections = null;

        // parse input supplied
        try {
            commandSections = this.parseInput(input);
        } catch (IllegalArgumentException e) {
            return Ui.errorInput();
        }

        //do a certain action based on the parsed input
        switch (currentCommand) {
        case LIST:
            return TaskList.list();

        case MARK:
            int indexMarked;
            try {
                indexMarked = Integer.parseInt(commandSections[1]) - 1;
            } catch (NumberFormatException e){
                return Ui.invalidIndex();
            }
            return TaskList.mark(indexMarked);

        case UNMARK:
            int indexUnmarked;
            try {
                indexUnmarked = Integer.parseInt(commandSections[1]) - 1;
            } catch (NumberFormatException e){
                return Ui.invalidIndex();
            }
            return TaskList.unmark(indexUnmarked);

        case DELETE:
            int indexDelete;
            try {
                indexDelete = Integer.parseInt(commandSections[1]) - 1;
            } catch (NumberFormatException e) {
                return Ui.invalidIndex();
            }
            return TaskList.delete(indexDelete);

        case TODO:
            String todoDescription = commandSections[1];
            return TaskList.addTodo(todoDescription);

        case DEADLINE:
            String deadlineContent = commandSections[1];
            String[] deadlineSegments = deadlineContent.split(" /by ");
            String deadlineDescription = deadlineSegments[0];
            String deadlineTime = deadlineSegments[1];
            return TaskList.addDeadline(deadlineDescription, deadlineTime);

        case EVENT:
            String eventContent = commandSections[1];
            String[] segments = eventContent.split(" /at ");
            String eventDescription = segments[0];
            String eventTime = segments[1];
            return TaskList.addEvent(eventDescription, eventTime);

        case FIND:
            String keyword = commandSections[1];
            return TaskList.find(keyword);

        default:
            return "Sorry I didnt understand you!";
        }
    }
}